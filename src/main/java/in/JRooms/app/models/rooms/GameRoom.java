package in.JRooms.app.models.rooms;

import com.fasterxml.jackson.annotation.JsonIgnore;

import in.JRooms.app.models.messages.Message;
import lombok.Getter;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Set;

@Getter
public class GameRoom extends AbstractRoom {

    private final String host;
    @JsonIgnore
    private final RoomGame game;

    @Getter
    private static class GameInfo {
        boolean started;
        Set<String> players;
        GameInfo(GameRoom gameRoom) {
            started = gameRoom.getGame().isStarted();
            players = gameRoom.getGame().getPlayerNames();
        }
    }

    public GameRoom(String roomId,
                    String title,
                    String host,
                    SimpMessagingTemplate template,
                    RoomGameFactory roomGameFactory
    ) {
        super(roomId, title, template);
        this.host = host;
        this.game = roomGameFactory.createGame(this);
    }

    public GameInfo getGameInfo() {
        return new GameInfo(this);
    }

    @Override
    public void process(String username, Message message) {
            game.process(username, message);
    }
}
