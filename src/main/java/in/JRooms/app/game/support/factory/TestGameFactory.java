package in.JRooms.app.game.support.factory;

import in.JRooms.app.game.GameFactory;
import in.JRooms.app.game.support.match.TestGame;
import in.JRooms.app.models.rooms.RoomGame;
import in.JRooms.app.models.rooms.GameRoom;
import org.springframework.stereotype.Service;

@Service
public class TestGameFactory implements GameFactory {

    @Override
    public RoomGame createGame(GameRoom gameRoom) {
        return new TestGame(gameRoom);
    }
}
