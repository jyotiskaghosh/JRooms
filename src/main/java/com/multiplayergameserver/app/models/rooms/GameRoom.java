package com.multiplayergameserver.app.models.rooms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.multiplayergameserver.app.game.GameFactory;

import com.multiplayergameserver.app.models.messages.GameMessage;
import com.multiplayergameserver.app.models.messages.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.util.Set;

@Slf4j
@Getter
public class GameRoom extends AbstractRoom {

    private final String host;
    @JsonIgnore
    private final RoomGame game;

    @AllArgsConstructor
    @Getter
    private static class GameInfo {
        boolean started;
        Set<String> players;
    }

    public GameRoom(String roomId,
                    String title,
                    String host,
                    GameFactory gameFactory
    ) {
        super(roomId, title);
        this.host = host;
        this.game = gameFactory.createGame(this);
        log.info("new room with roomId {} created.", roomId);
    }

    public GameInfo getGameInfo() {
        return new GameInfo(active, game.getPlayerNames());
    }

    @Override
    public void subscribe(Subscriber<? super Message> subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void process(String username, RSocketRequester requester, Message message) {
        if (message instanceof GameMessage)
            game.process(username, requester, (GameMessage) message);
    }

    @Override
    public void broadcastAll(Message message) {
        subscribers.forEach(subscriber -> subscriber.onNext(message));
    }

    @Override
    public void dispose() {
        subscribers.forEach(Subscriber::onComplete);
        disposed = true;
        log.info("deleted room with roomId {}.", roomId);
    }
}
