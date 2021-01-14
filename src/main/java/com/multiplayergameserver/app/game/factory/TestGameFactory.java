package com.multiplayergameserver.app.game.factory;

import com.multiplayergameserver.app.game.TestGame;
import com.multiplayergameserver.app.models.rooms.RoomGame;
import com.multiplayergameserver.app.repositories.GameFactory;
import com.multiplayergameserver.app.models.rooms.GameRoom;
import org.springframework.stereotype.Service;

@Service
public class TestGameFactory implements GameFactory {

    @Override
    public RoomGame createGame(GameRoom gameRoom) {
        return new TestGame(gameRoom);
    }
}
