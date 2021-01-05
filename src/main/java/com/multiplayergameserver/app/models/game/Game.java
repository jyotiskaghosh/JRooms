package com.multiplayergameserver.app.models.game;

import com.multiplayergameserver.app.models.messages.Action;
import com.multiplayergameserver.app.models.rooms.RoomSocket;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public abstract class Game {

    protected final RoomSocket roomSocket;
    protected final Map<String, Player> players;

    @Autowired
    protected PlayerFactory playerFactory;

    public Game(RoomSocket roomSocket) {
        this.roomSocket = roomSocket;
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

    public void start() { throw new UnsupportedOperationException(); }
    public void process(String username, Action action) {}
    public void end() { throw new UnsupportedOperationException(); }
}
