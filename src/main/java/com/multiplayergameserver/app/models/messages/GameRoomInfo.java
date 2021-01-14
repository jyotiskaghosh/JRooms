package com.multiplayergameserver.app.models.messages;

import com.multiplayergameserver.app.game.Player;
import com.multiplayergameserver.app.models.rooms.GameRoom;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
public class GameRoomInfo {
    private String roomId;
    private String title;
    private Set<String> users;
    private boolean active;
    private String host;
    private boolean started;
    private Map<String, PlayerInfo> players;

    @Getter
    public static class PlayerInfo {
        String username;
        PlayerInfo(Player player) {
            this.username = player.getUsername();
        }
    }

    public GameRoomInfo(GameRoom gameRoom) {
        this.roomId = gameRoom.getRoomId();
        this.title = gameRoom.getTitle();
        this.users = gameRoom.getUsers();
        this.active = gameRoom.isActive();
        this.host = gameRoom.getHost();
        this.started = gameRoom.getGame().isStarted();
        this.players = new HashMap<>();
        gameRoom.getGame().getRoomPlayers().forEach((s, roomPlayer) -> players.put(s, new PlayerInfo(roomPlayer)));
    }
}
