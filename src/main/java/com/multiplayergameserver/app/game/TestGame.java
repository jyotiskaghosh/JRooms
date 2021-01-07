package com.multiplayergameserver.app.game;

import com.multiplayergameserver.app.models.game.Game;
import com.multiplayergameserver.app.models.game.PlayerFactory;
import com.multiplayergameserver.app.models.messages.WarnMessage;
import com.multiplayergameserver.app.models.rooms.GameRoom;

public class TestGame extends Game {

    private static final int MAX_PLAYERS = 2;

    public TestGame(GameRoom gameRoom, PlayerFactory playerFactory) {
        super(gameRoom, playerFactory);
    }

    @Override
    public void addPlayer(String username) {
        if (players.size() >= MAX_PLAYERS)
        {
            super.gameRoom.sendUser(username, new WarnMessage("game at maximum player capacity"));
            return;
        }
        players.put(username, playerFactory.createPlayer(username));
    }
}
