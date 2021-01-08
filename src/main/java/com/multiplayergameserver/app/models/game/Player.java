package com.multiplayergameserver.app.models.game;

import lombok.Getter;

@Getter
public abstract class Player {
    private final String username;

    public Player(String username) {
        this.username = username;
    }
}
