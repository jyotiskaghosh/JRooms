package com.multiplayergameserver.app.controllers;

import com.multiplayergameserver.app.models.messages.CreateGameMessage;
import com.multiplayergameserver.app.models.messages.RoomMessage;
import com.multiplayergameserver.app.models.rooms.GameRoom;
import com.multiplayergameserver.app.models.rooms.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class RoomController {

    @Autowired
    private SimpMessagingTemplate template;

    private Map<String, GameRoom> gameRooms = new HashMap<>();

    @Secured("user")
    @PostMapping("/new/game/room")
    public String newGame(@RequestBody CreateGameMessage message, Principal principal) {
        String roomId = UUID.randomUUID().toString();
        gameRooms.put(roomId,
            new GameRoom(
                roomId,
                message.getTitle(),
                message.isActive(),
                template,
                principal.getName()
            ));
        return roomId;
    }

    @GetMapping("/rooms/games")
    public List<String> getGames() {
        return gameRooms.values().stream().map(Room::getRoomId).collect(Collectors.toList());
    }

    @GetMapping("/rooms/{roomId}/users")
    public Set<String> getUsers(@RequestBody String roomId) {
        return gameRooms.get(roomId).getUsers();
    }

    @Secured("user")
    @PostMapping("/rooms/{roomId}/add/user")
    public void addUser(@RequestBody String roomId, Principal principal) {
        gameRooms.get(roomId).addUser(principal.getName());
    }

    @Secured("user")
    @PostMapping("/rooms/{roomId}/remove/user")
    public void removeUser(@RequestBody String roomId, Principal principal) {
        gameRooms.get(roomId).removeUser(principal.getName());
    }

    @Secured("user")
    @PostMapping("/rooms/{roomId}/add/player")
    public void addPlayer(@RequestBody String roomId, Principal principal) {
        gameRooms.get(roomId).addPlayer(principal.getName());
    }

    @Secured("user")
    @PostMapping("/rooms/{roomId}/remove/player")
    public void removePlayer(@RequestBody String roomId, Principal principal) {
        gameRooms.get(roomId).removePlayer(principal.getName());
    }

    @Secured("user")
    @PostMapping("/rooms/{roomId}/start")
    public void start(@RequestBody String roomId) {
        gameRooms.get(roomId).start();
    }

    @Secured("user")
    @PostMapping("/rooms/{roomId}/end")
    public void end(@RequestBody String roomId) {
        gameRooms.get(roomId).end();
        gameRooms.remove(roomId);
    }

    @Secured("user")
    @MessageMapping("/rooms/{roomId}/message")
    public void process(@RequestBody RoomMessage roomMessage, Principal principal) {
        gameRooms.get(roomMessage.getRoomId()).process(principal.getName(), roomMessage.getMessage());
    }

}
