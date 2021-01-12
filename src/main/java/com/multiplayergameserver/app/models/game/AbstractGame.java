package com.multiplayergameserver.app.models.game;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.multiplayergameserver.app.models.messages.Action;
import com.multiplayergameserver.app.models.rooms.GameRoom;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public abstract class AbstractGame {

    @JsonIgnore
    private final GameRoom gameRoom;
    private boolean started;
    @JsonIgnore
    private final Map<String, AbstractPlayer> players;
    @JsonIgnore
    private final PlayerFactory playerFactory;

    public AbstractGame(GameRoom gameRoom, PlayerFactory playerFactory) {
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

    @JsonProperty("players")
    public Set<String> getPlayersInfo() {
        return players.keySet();
    }

    public void start() { throw new UnsupportedOperationException(); }
    public void process(String username, Action action) { throw new UnsupportedOperationException(); }
    public void end() { throw new UnsupportedOperationException(); }
}
