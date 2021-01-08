package com.multiplayergameserver.app.models.messages;

import lombok.Data;

@Data
public class CreateGameMessage {
    private final String title;
    private final boolean active;
}
