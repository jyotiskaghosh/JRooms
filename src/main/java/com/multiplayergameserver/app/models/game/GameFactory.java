package com.multiplayergameserver.app.models.game;

import com.multiplayergameserver.app.models.rooms.RoomSocket;

public interface GameFactory {
    Game createGame(RoomSocket roomSocket);
}