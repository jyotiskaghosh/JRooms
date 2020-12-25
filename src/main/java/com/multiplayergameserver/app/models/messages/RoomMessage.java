package com.multiplayergameserver.app.models.messages;

import lombok.Getter;

@Getter
public class RoomMessage {
    String roomId;
    Message message;
}
