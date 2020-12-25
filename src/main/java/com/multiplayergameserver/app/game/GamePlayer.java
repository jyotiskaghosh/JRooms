package com.multiplayergameserver.app.game;

import com.multiplayergameserver.app.models.game.Player;
import lombok.Getter;

@Getter
public class GamePlayer extends Player {

    public GamePlayer(String username) {
        super(username);
    }
}
