package com.multiplayergameserver.app.models.rooms;

import com.multiplayergameserver.app.models.messages.Message;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.Disposable;

import java.util.HashSet;
import java.util.Set;

@Data
public abstract class AbstractRoom implements Publisher<Message>, Disposable {
    protected final String roomId;
    protected final String title;
    protected boolean active = true;
    protected boolean disposed = false;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    protected final Set<Subscriber<? super Message>> subscribers = new HashSet<>();

    public void process(String username, RSocketRequester requester, Message message) {
        throw new UnsupportedOperationException();
    }
    public void broadcastAll(Message message) { throw new UnsupportedOperationException(); }
}