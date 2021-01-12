package com.multiplayergameserver.app.game.factory;

import com.multiplayergameserver.app.game.TestGame;
import com.multiplayergameserver.app.models.game.AbstractGame;
import com.multiplayergameserver.app.models.game.GameFactory;
import com.multiplayergameserver.app.models.game.PlayerFactory;
import com.multiplayergameserver.app.models.rooms.GameRoom;
import org.springframework.stereotype.Service;

@Service
public class TestGameFactory implements GameFactory {

    @Override
    public AbstractGame createGame(GameRoom gameRoom, PlayerFactory playerFactory) {
        return new TestGame(gameRoom, playerFactory);
    }
}
