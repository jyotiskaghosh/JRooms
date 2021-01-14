package com.multiplayergameserver.app.game.match;

import lombok.Getter;

@Getter
public class GamePlayer implements Player {
    private final String username;
    public GamePlayer(String username) {
        this.username = username;
    }
}
