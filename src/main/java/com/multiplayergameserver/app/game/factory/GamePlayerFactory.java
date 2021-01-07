package com.multiplayergameserver.app.game.factory;

import com.multiplayergameserver.app.game.GamePlayer;
import com.multiplayergameserver.app.models.game.Player;
import com.multiplayergameserver.app.models.game.PlayerFactory;
import org.springframework.stereotype.Service;

@Service
public class GamePlayerFactory implements PlayerFactory {

    @Override
    public Player createPlayer(String username) {
        return new GamePlayer(username);
    }
}
