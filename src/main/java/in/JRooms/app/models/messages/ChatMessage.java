package in.JRooms.app.models.messages;

import lombok.Data;

@Data
public class ChatMessage implements Message {
    private String sender = "";
    private final String body;
}
