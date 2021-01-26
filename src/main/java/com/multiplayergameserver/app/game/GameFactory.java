package com.multiplayergameserver.app.game;

import com.multiplayergameserver.app.models.rooms.GameRoom;
import com.multiplayergameserver.app.models.rooms.RoomGame;

public interface GameFactory {
    RoomGame createGame(GameRoom gameRoom);
}