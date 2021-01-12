package com.multiplayergameserver.app.game;

import com.multiplayergameserver.app.models.game.AbstractGame;
import com.multiplayergameserver.app.models.game.PlayerFactory;
import com.multiplayergameserver.app.models.messages.WarnMessage;
import com.multiplayergameserver.app.models.rooms.GameRoom;

public class TestGame extends AbstractGame {

    private static final int MAX_PLAYERS = 2;

    public TestGame(GameRoom gameRoom, PlayerFactory playerFactory) {
        super(gameRoom, playerFactory);
    }

    @Override
    public void addPlayer(String username) {
        if (super.getPlayers().size() >= MAX_PLAYERS)
        {
            super.getGameRoom().sendUser(username, new WarnMessage("game at maximum player capacity"));
            return;
        }
        super.getPlayers().put(username, super.getPlayerFactory().createPlayer(username));
    }
}
