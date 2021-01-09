package com.multiplayergameserver.app.models.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.multiplayergameserver.app.models.messages.Action;
import com.multiplayergameserver.app.models.rooms.GameRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public abstract class Game {

    @JsonIgnore
    private final GameRoom gameRoom;
    private boolean started;
    private final Map<String, Player> players;
    @JsonIgnore
    private final PlayerFactory playerFactory;

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

    public void start() { throw new UnsupportedOperationException(); }
    public void process(String username, Action action) { throw new UnsupportedOperationException(); }
    public void end() { throw new UnsupportedOperationException(); }
}
