package com.multiplayergameserver.app.models.game;

import lombok.Getter;

@Getter
public abstract class AbstractPlayer {
    private final String username;

    public AbstractPlayer(String username) {
        this.username = username;
    }
}
