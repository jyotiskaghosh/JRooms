package com.multiplayergameserver.app.game;

import com.multiplayergameserver.app.models.game.Game;
import com.multiplayergameserver.app.models.messages.WarnMessage;
import com.multiplayergameserver.app.models.rooms.RoomSocket;


public class TestGame extends Game {

    private static final int MAX_PLAYERS = 2;

    public TestGame(RoomSocket roomSocket) {
        super(roomSocket);
    }

    @Override
    public void addPlayer(String username) {
        if (players.size() >= MAX_PLAYERS)
        {
            roomSocket.sendUser(username, new WarnMessage("game at maximum player capacity"));
            return;
        }
        players.put(username, playerFactory.createPlayer(username));
    }
}
