package com.multiplayergameserver.app.game.support.factory;

import com.multiplayergameserver.app.game.GameFactory;
import com.multiplayergameserver.app.game.support.match.TestGame;
import com.multiplayergameserver.app.models.rooms.RoomGame;
import com.multiplayergameserver.app.models.rooms.GameRoom;
import org.springframework.stereotype.Service;

@Service
public class TestGameFactory implements GameFactory {

    @Override
    public RoomGame createGame(GameRoom gameRoom) {
        return new TestGame(gameRoom);
    }
}
