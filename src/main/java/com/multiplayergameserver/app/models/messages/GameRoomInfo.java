package com.multiplayergameserver.app.models.messages;

import com.multiplayergameserver.app.game.match.Player;
import com.multiplayergameserver.app.models.rooms.GameRoom;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class GameRoomInfo {
    private String roomId;
    private String title;
    private Set<String> users;
    private boolean active;
    private String host;
    private boolean started;
    private Set<String> players;

    public GameRoomInfo(GameRoom gameRoom) {
        this.roomId = gameRoom.getRoomId();
        this.title = gameRoom.getTitle();
        this.users = gameRoom.getUsers();
        this.active = gameRoom.isActive();
        this.host = gameRoom.getHost();
        this.started = gameRoom.getGame().isStarted();
        this.players = gameRoom.getGame().getPlayers().stream().map(Player::getUsername).collect(Collectors.toSet());
    }
}
