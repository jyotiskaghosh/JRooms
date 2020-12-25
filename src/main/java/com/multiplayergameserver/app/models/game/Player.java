package com.multiplayergameserver.app.models.game;

public abstract class Player {
    protected String username;

    public Player(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
