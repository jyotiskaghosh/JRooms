package in.JRooms.app.game.support.match;

import in.JRooms.app.models.messages.Message;
import in.JRooms.app.models.rooms.GameRoom;
import in.JRooms.app.models.rooms.RoomGame;
import lombok.Data;

import java.util.Set;

@Data
public class TestGame implements RoomGame {

    private final GameRoom gameRoom;
    private boolean started;

    @Override
    public Set<String> getPlayerNames() {
        return null;
    }

    @Override
    public void process(String username, Message message) {
        gameRoom.broadcast(message);
    }
}
