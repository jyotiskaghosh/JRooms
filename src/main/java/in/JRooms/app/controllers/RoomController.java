package in.JRooms.app.controllers;

import in.JRooms.app.models.rooms.RoomGameFactory;
import in.JRooms.app.models.CreateGameMessage;
import in.JRooms.app.models.messages.Message;
import in.JRooms.app.models.rooms.GameRoom;
import in.JRooms.app.models.rooms.AbstractRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@RestController
public class RoomController {

    @Autowired
    private RoomGameFactory gameFactory;

    private final Map<String, AbstractRoom> rooms = new ConcurrentHashMap<>();

    // dispose inactive rooms every 1 min
    @Scheduled(fixedRate = 60000)
    public void cleanRooms() {
        rooms.forEach((id, room) -> {
            if (!room.isActive()) {
                room.dispose();
                rooms.remove(id);
            }
        });
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/new/game")
    public Mono<String> newGame(@RequestBody CreateGameMessage message, Principal principal) {
        String roomId = UUID.randomUUID().toString();
        rooms.put(roomId,
                new GameRoom(
                roomId,
                message.getTitle(),
                principal.getName(),
                gameFactory));
        return Mono.just(roomId);
    }

    @GetMapping("/rooms/games")
    public Map.Entry<String, List<GameRoom>> getGames() {
        return Map.entry("games",
                rooms.values()
                .stream()
                .filter(room -> room instanceof GameRoom)
                .map(room -> (GameRoom) room)
                .collect(Collectors.toList())
        );
    }

    @PreAuthorize("hasAuthority('USER')")
    @MessageMapping("rooms.{roomId}")
    public Flux<Message> process(@DestinationVariable String roomId,
                                     Flux<Message> messages,
                                     Principal principal,
                                     RSocketRequester requester) {
        AbstractRoom room = rooms.get(roomId);
        messages.subscribe(message -> room.process(principal.getName(), requester, message));
        return Flux.from(room);
    }

    // for testing if rsocket connection established or not
    @PreAuthorize("hasAuthority('USER')")
    @MessageMapping("test")
    public Mono<String> test(String message) {
        return Mono.just(message);
    }
}
