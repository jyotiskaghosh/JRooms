package com.multiplayergameserver.app.models.messages;

import lombok.Getter;

@Getter
public class CreateGameMessage {
    String title;
    boolean active;
}
