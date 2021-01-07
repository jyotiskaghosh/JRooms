package com.multiplayergameserver.app.models.game;

import com.multiplayergameserver.app.models.rooms.GameRoom;

public interface GameFactory {
    Game createGame(GameRoom gameRoom, PlayerFactory playerFactory);
}