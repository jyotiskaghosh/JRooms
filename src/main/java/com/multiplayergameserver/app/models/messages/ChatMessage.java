package com.multiplayergameserver.app.models.messages;

import lombok.Getter;

@Getter
public class ChatMessage implements Message {

    private final String sender;
    private final String body;

    public ChatMessage(String sender, String body) {
        this.sender = sender;
        this.body = body;
    }

    public String getType() {
        return "chat";
    }
}