package com.multiplayergameserver.app.models.messages;

import lombok.Getter;

@Getter
public class ChatMessage implements Message {

    private String sender;
    private String body;

    public ChatMessage(String sender, String body, String roomId) {
        this.sender = sender;
        this.body = body;
    }

    public String getType() {
        return "chat";
    }
}