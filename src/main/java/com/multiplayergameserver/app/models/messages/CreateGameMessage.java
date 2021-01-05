package com.multiplayergameserver.app.models.messages;

import lombok.Getter;

@Getter
public class CreateGameMessage {
    private String title;
    private boolean active;
}
