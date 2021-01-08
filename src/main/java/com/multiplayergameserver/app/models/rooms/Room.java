package com.multiplayergameserver.app.models.rooms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.multiplayergameserver.app.models.messages.Message;
import lombok.Getter;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.HashSet;
import java.util.Set;

@Getter
public abstract class Room {
    private final String roomId;
    private final String title;
    private final Set<String> users;
    private boolean active;
    @JsonIgnore
    private final SimpMessagingTemplate template;

    Room(String roomId, String title, boolean active, SimpMessagingTemplate template) {
        this.roomId = roomId;
        this.title = title;
        this.active = active;
        this.template = template;
        this.users = new HashSet<>();
    }

    public void send(Message message) {
        this.template.convertAndSend("/topic/rooms/" + roomId, message);
    }

    public void sendUser(String username, Message message) {
        this.template.convertAndSend("/topic/rooms/" + roomId + "/" + username, message);
    }

    public void addUser(String username) {
        users.add(username);
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public void process(String username, Message message) { throw new UnsupportedOperationException(); }
}