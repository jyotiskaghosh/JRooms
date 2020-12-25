package com.multiplayergameserver.app.game.factory;

import com.multiplayergameserver.app.game.GamePlayer;
import com.multiplayergameserver.app.models.game.Player;
import com.multiplayergameserver.app.models.game.PlayerFactory;
import org.springframework.context.annotation.Bean;


public class GamePlayerFactory implements PlayerFactory {

    @Bean
    private PlayerFactory getGamePlayerFactory() {
        return this;
    }

    @Override
    public Player createPlayer(String username) {
        return new GamePlayer(username);
    }
}
