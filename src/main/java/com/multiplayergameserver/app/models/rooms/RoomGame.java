package com.multiplayergameserver.app.models.rooms;

import com.multiplayergameserver.app.game.match.Player;
import com.multiplayergameserver.app.models.messages.Action;

import java.util.Set;

public interface RoomGame {
    GameRoom getGameRoom();
    boolean isStarted();
    Set<Player> getPlayers();
    void addPlayer(String username);
    void removePlayer(String username);
    void start();
    void process(String username, Action action);
    void end();
}
