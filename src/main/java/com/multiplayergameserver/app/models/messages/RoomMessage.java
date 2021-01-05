package com.multiplayergameserver.app.models.messages;

import lombok.Getter;

@Getter
public class RoomMessage {
    private String roomId;
    private Message message;
}
