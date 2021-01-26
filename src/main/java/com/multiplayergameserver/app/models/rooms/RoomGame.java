package com.multiplayergameserver.app.models.rooms;

import com.multiplayergameserver.app.models.messages.GameMessage;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.util.Set;

public interface RoomGame {
    boolean isStarted();
    // must return not null values
    Set<String> getPlayerNames();
    void process(String username, RSocketRequester requester, GameMessage message);
}
