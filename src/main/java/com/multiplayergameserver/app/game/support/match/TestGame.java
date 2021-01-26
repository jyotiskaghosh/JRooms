package com.multiplayergameserver.app.game.support.match;

import com.multiplayergameserver.app.models.messages.GameMessage;
import com.multiplayergameserver.app.models.rooms.GameRoom;
import com.multiplayergameserver.app.models.rooms.RoomGame;
import lombok.Data;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.util.Set;

@Data
public class TestGame implements RoomGame {

    private final GameRoom gameRoom;
    private boolean started = true;

    @Override
    public Set<String> getPlayerNames() {
        return null;
    }

    @Override
    public void process(String username, RSocketRequester requester, GameMessage message) {
        throw new UnsupportedOperationException();
    }
}
