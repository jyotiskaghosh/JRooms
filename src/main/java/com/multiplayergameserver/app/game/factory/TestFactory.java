package com.multiplayergameserver.app.game.factory;

import com.multiplayergameserver.app.game.TestGame;
import com.multiplayergameserver.app.models.game.Game;
import com.multiplayergameserver.app.models.game.GameFactory;
import com.multiplayergameserver.app.models.game.PlayerFactory;
import com.multiplayergameserver.app.models.rooms.GameRoom;
import org.springframework.stereotype.Service;

@Service
public class TestFactory implements GameFactory {

    @Override
    public Game createGame(GameRoom gameRoom, PlayerFactory playerFactory) {
        return new TestGame(gameRoom, playerFactory);
    }
}
