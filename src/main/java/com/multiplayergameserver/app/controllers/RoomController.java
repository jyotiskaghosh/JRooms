package com.multiplayergameserver.app.controllers;

import com.multiplayergameserver.app.models.game.GameFactory;
import com.multiplayergameserver.app.models.game.PlayerFactory;
import com.multiplayergameserver.app.models.messages.CreateGameMessage;
import com.multiplayergameserver.app.models.messages.Message;
import com.multiplayergameserver.app.models.rooms.GameRoom;
import com.multiplayergameserver.app.models.rooms.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class RoomController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private GameFactory gameFactory;

    @Autowired
    protected PlayerFactory playerFactory;

    private Map<String, Room> rooms = new HashMap<>();

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/new/game")
    public String newGame(@RequestBody CreateGameMessage message, Principal principal) {
        String roomId = UUID.randomUUID().toString();
        rooms.put(roomId,
            new GameRoom(
                roomId,
                message.getTitle(),
                message.isActive(),
                principal.getName(),
                template,
                gameFactory,
                playerFactory
            ));
        return roomId;
    }

    @GetMapping("/rooms/games")
    public List<String> getGames() {
        return rooms
                .values()
                .stream()
                .filter(room -> room instanceof GameRoom)
                .map(Room::getRoomId)
                .collect(Collectors.toList());
    }

    @GetMapping("/rooms/{roomId}/users")
    public Set<String> getUsers(@PathVariable String roomId) {
        return rooms.get(roomId).getUsers();
    }

    @GetMapping("/rooms/{roomId}/players")
    public List<String> getPlayers(@PathVariable String roomId) {
        if (rooms.get(roomId) instanceof GameRoom)
            return ((GameRoom) rooms.get(roomId)).getPlayers();
        return null;
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/rooms/{roomId}/add/user")
    public void addUser(@PathVariable String roomId, Principal principal) {
        rooms.get(roomId).addUser(principal.getName());
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/rooms/{roomId}/remove/user")
    public void removeUser(@PathVariable String roomId, Principal principal) {
        rooms.get(roomId).removeUser(principal.getName());
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/rooms/{roomId}/add/player")
    public void addPlayer(@PathVariable String roomId, Principal principal) {
        if (rooms.get(roomId) instanceof GameRoom) {
            GameRoom gameRoom = (GameRoom) rooms.get(roomId);
            gameRoom.addPlayer(principal.getName());
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/rooms/{roomId}/remove/player")
    public void removePlayer(@PathVariable String roomId, Principal principal) {
        if (rooms.get(roomId) instanceof GameRoom) {
            GameRoom room = (GameRoom) rooms.get(roomId);
            room.removePlayer(principal.getName());
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/rooms/{roomId}/start")
    public void start(@PathVariable String roomId, Principal principal) {
        if (rooms.get(roomId) instanceof GameRoom) {
            GameRoom gameRoom = (GameRoom) rooms.get(roomId);
            if (gameRoom.getPlayers().stream().anyMatch(player -> player.equals(principal.getName())))
                gameRoom.start();
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/rooms/{roomId}/end")
    public void end(@PathVariable String roomId, Principal principal) {
        if (rooms.get(roomId) instanceof GameRoom) {
            GameRoom gameRoom = (GameRoom) rooms.get(roomId);
            if (gameRoom.getPlayers().stream().anyMatch(player -> player.equals(principal.getName()))) {
                gameRoom.end();
                rooms.remove(roomId);
            }
        }
    }

    @PreAuthorize("hasAuthority('USER')")
    @MessageMapping("/rooms/{roomId}/message")
    public void process(@PathVariable String roomId, @RequestBody Message message, Principal principal) {
        rooms.get(roomId).process(principal.getName(), message);
    }
}
