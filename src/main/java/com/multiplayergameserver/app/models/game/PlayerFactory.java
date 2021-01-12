package com.multiplayergameserver.app.models.game;

public interface PlayerFactory {
    AbstractPlayer createPlayer(String username);
}
