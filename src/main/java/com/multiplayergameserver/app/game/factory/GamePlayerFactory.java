package com.multiplayergameserver.app.game.factory;

import com.multiplayergameserver.app.game.Player;
import com.multiplayergameserver.app.models.game.AbstractPlayer;
import com.multiplayergameserver.app.models.game.PlayerFactory;
import org.springframework.stereotype.Service;

@Service
public class GamePlayerFactory implements PlayerFactory {

    @Override
    public AbstractPlayer createPlayer(String username) {
        return new Player(username);
    }
}
