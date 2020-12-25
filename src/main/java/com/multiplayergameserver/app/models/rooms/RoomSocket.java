package com.multiplayergameserver.app.models.rooms;

import com.multiplayergameserver.app.models.messages.Message;

import org.springframework.messaging.simp.SimpMessagingTemplate;

public class RoomSocket {

    private String roomId;
    private SimpMessagingTemplate template;

    public RoomSocket(String roomId, SimpMessagingTemplate template) {
        this.roomId = roomId;
        this.template = template;
    }

    // send a message to a room
    public void send(Message message) {
        this.template.convertAndSend("/topic/rooms/" + roomId, message);
    }

    // send a message to a specific user in a room
    public void sendUser(String username, Message message) {
        this.template.convertAndSend("/topic/rooms/" + roomId + "/" + username, message);
    }
}
