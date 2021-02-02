package in.JRooms.app.models.rooms;

import in.JRooms.app.models.messages.Message;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.HashSet;
import java.util.Set;

@Data
public abstract class AbstractRoom {
    protected final String roomId;
    protected final String title;
    protected final Set<String> users = new HashSet<>();
    protected boolean active = true;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    protected final SimpMessagingTemplate template;

    public void broadcast(Message message) {
        this.template.convertAndSend("/topic/rooms/" + roomId, message);
    }

    public void sendUser(String username, Message message) {
        this.template.convertAndSendToUser(username, "/topic/rooms/" + roomId, message);
    }

    public void process(String username, Message message) { throw new UnsupportedOperationException(); }
}