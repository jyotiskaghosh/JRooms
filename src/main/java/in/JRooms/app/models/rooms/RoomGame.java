package in.JRooms.app.models.rooms;

import in.JRooms.app.models.messages.Message;

import java.util.Set;

public interface RoomGame {
    boolean isStarted();
    Set<String> getPlayerNames();
    void process(String username, Message message);
}
