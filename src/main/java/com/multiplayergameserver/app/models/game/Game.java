package com.multiplayergameserver.app.models.game;

import com.multiplayergameserver.app.models.messages.Action;
import com.multiplayergameserver.app.models.rooms.GameRoom;

import java.util.*;

public abstract class Game {

    protected final GameRoom gameRoom;
    protected final Map<String, Player> players;
    protected final PlayerFactory playerFactory;

    public Game(GameRoom gameRoom, PlayerFactory playerFactory) {
        this.gameRoom = gameRoom;
        this.playerFactory = playerFactory;
        this.players = new HashMap<>();
    }

    public void addPlayer(String username) {
        players.put(username, playerFactory.createPlayer(username));
    }

    public void removePlayer(String username) {
        players.remove(username);
        if (players.size() == 0)
            end();
    }

    public List<String> getPlayers() {
        return new ArrayList<>(players.keySet());
    }

    public void start() { throw new UnsupportedOperationException(); }
    public void process(String username, Action action) { throw new UnsupportedOperationException(); }
    public void end() { throw new UnsupportedOperationException(); }
}
