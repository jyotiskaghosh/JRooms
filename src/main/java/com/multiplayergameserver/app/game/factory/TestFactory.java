package com.multiplayergameserver.app.game.factory;

import com.multiplayergameserver.app.game.TestGame;
import com.multiplayergameserver.app.models.game.Game;
import com.multiplayergameserver.app.models.game.GameFactory;
import com.multiplayergameserver.app.models.rooms.RoomSocket;
import org.springframework.context.annotation.Bean;

public class TestFactory implements GameFactory {

    @Bean
    private GameFactory getGamePlayerFactory() {
        return this;
    }

    @Override
    public Game createGame(RoomSocket roomSocket) {
        return new TestGame(roomSocket);
    }
}
