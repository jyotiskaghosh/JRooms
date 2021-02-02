package in.JRooms.app.game.support.factory;

import in.JRooms.app.game.support.match.TestGame;
import in.JRooms.app.models.rooms.RoomGame;
import in.JRooms.app.models.rooms.GameRoom;
import in.JRooms.app.models.rooms.RoomGameFactory;
import org.springframework.stereotype.Service;

@Service
public class TestGameFactory implements RoomGameFactory {

    @Override
    public RoomGame createGame(GameRoom gameRoom) {
        return new TestGame(gameRoom);
    }
}
