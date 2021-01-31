package in.JRooms.app.models.rooms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import in.JRooms.app.models.messages.Message;

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
                    RoomGameFactory gameFactory
    ) {
        super(roomId, title);
        this.host = host;
        this.game = gameFactory.createGame(this);
        log.info("new room with roomId {} created.", roomId);
    }

    @Override
    public void subscribe(Subscriber<? super Message> subscriber) {
        subscribers.add(subscriber);
    }

    public GameInfo getGameInfo() {
        return new GameInfo(game.isStarted(), game.getPlayerNames());
    }

    @Override
    public void process(String username, RSocketRequester requester, Message message) {
            game.process(username, requester, message);
    }

    @Override
    public void broadcast(Message message) {
        subscribers.forEach(subscriber -> subscriber.onNext(message));
    }

    @Override
    public void dispose() {
        subscribers.forEach(Subscriber::onComplete);
        disposed = true;
        log.info("deleted room with roomId {}.", roomId);
    }
}
