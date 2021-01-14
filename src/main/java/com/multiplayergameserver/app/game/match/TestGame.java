package com.multiplayergameserver.app.game.match;

import com.multiplayergameserver.app.models.messages.Action;
import com.multiplayergameserver.app.models.messages.WarnMessage;
import com.multiplayergameserver.app.models.rooms.GameRoom;
import com.multiplayergameserver.app.models.rooms.RoomGame;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class TestGame implements Game, RoomGame {

    private static final int MAX_PLAYERS = 2;
    private final GameRoom gameRoom;
    private final Map<String, Player> players;
    private boolean started;

    public TestGame(GameRoom gameRoom) {
        this.gameRoom = gameRoom;
        this.players = new HashMap<>();
    }

    public void addPlayer(String username) {
        if (players.size() >= MAX_PLAYERS)
        {
            this.getGameRoom().sendUser(username, new WarnMessage("game at maximum player capacity"));
            return;
        }
        players.put(username, new GamePlayer(username));

    }

    @Override
    public void removePlayer(String username) {

    }

    @Override
    public void start() {

    }

    @Override
    public void process(String username, Action action) {

    }

    @Override
    public void end() {

    }
}
