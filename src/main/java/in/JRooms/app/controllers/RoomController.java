package in.JRooms.app.controllers;

import in.JRooms.app.models.messages.Message;
import in.JRooms.app.models.rooms.RoomGameFactory;
import in.JRooms.app.models.rooms.GameRoom;
import in.JRooms.app.models.rooms.AbstractRoom;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private RoomGameFactory roomGameFactory;

    private final Map<String, AbstractRoom> rooms = new ConcurrentHashMap<>();

    // clean up inactive rooms every 1 min
    @Scheduled(fixedRate = 60000)
    public void cleanRooms() {
        rooms.forEach((id, room) -> {
            if (!room.isActive()) {
                rooms.remove(id);
                log.info("deleted inactive room with roomId: " + id);
            }
        } );
    }

    @Getter
    private static class CreateGameMessage {
        private String title;
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/new/game")
    public String newGame(@RequestBody CreateGameMessage message, Principal principal) {
        String roomId = UUID.randomUUID().toString();
        rooms.put(roomId,
            new GameRoom(
                roomId,
                message.getTitle(),
                principal.getName(),
                template,
                roomGameFactory)
        );
        log.info("game room created, roomid = " + roomId);
        return roomId;
    }

    @GetMapping("/games")
    public Map.Entry<String, List<GameRoom>> getGames() {
        return Map.entry("games",
                rooms
                .values()
                .stream()
                .filter(room -> room instanceof GameRoom)
                .map(room -> (GameRoom) room)
                .collect(Collectors.toList())
        );
    }

    @PreAuthorize("hasAuthority('USER')")
    @MessageMapping("/{roomId}")
    public void process(@PathVariable String roomId, @RequestBody Message message, Principal principal) {
        rooms.get(roomId).process(principal.getName(), message);
    }
}
