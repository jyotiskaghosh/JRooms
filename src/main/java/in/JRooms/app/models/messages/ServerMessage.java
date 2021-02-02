package in.JRooms.app.models.messages;

import lombok.Data;

@Data
public class ServerMessage implements Message {
    private final String body;
}
