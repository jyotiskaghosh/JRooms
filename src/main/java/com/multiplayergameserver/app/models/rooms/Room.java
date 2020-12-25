package com.multiplayergameserver.app.models.rooms;

import com.multiplayergameserver.app.models.messages.Message;
import lombok.Getter;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.HashSet;
import java.util.Set;

@Getter
public abstract class Room {
    String roomId;
    String title;
    Set<String> users;
    boolean active;
    RoomSocket roomSocket;

    Room(String roomId, String title, boolean active, SimpMessagingTemplate template) {
        this.roomId = roomId;
        this.title = title;
        this.active = active;
        this.users = new HashSet<>();
        this.roomSocket = new RoomSocket(roomId, template);
    }

    public void send(Message message) {
        roomSocket.send(message);
    }

    public void sendUser(String username, Message message) {
        roomSocket.sendUser(username, message);
    }

    public void addUser(String username) {
        users.add(username);
    }

    public void removeUser(String username) {
        users.remove(username);
    }
}