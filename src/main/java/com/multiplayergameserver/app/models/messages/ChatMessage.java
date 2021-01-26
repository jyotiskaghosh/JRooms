package com.multiplayergameserver.app.models.messages;

import lombok.Data;

@Data
public class ChatMessage implements Message {
    private final String sender;
    private final String body;
}
