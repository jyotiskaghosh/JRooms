package in.JRooms.app.models.rooms;

import in.JRooms.app.models.messages.Message;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.util.Set;

public interface RoomGame {
    boolean isStarted();
    Set<String> getPlayerNames();
    void process(String username, RSocketRequester requester, Message message);
}
