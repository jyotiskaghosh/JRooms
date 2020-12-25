package com.multiplayergameserver.app.models.rooms;

import com.multiplayergameserver.app.models.game.Game;
import com.multiplayergameserver.app.models.game.GameFactory;
import com.multiplayergameserver.app.models.messages.Action;
import com.multiplayergameserver.app.models.messages.Message;

import com.multiplayergameserver.app.models.messages.WarnMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;


public class GameRoom extends Room {

    private String host;
    private Game game;

    @Autowired
    private GameFactory gameFactory;

    public GameRoom(String roomId,
                    String title,
                    boolean active,
                    SimpMessagingTemplate template,
                    String host) {
        super(roomId, title, active, template);
        this.host = host;
        this.game = gameFactory.createGame(this.roomSocket);
    }

    public String getHost() {
        return host;
    }

    public void addPlayer(String username) {
        addUser(username);
        game.addPlayer(username);
    }

    public void removePlayer(String username) {
        removeUser(username);
        game.removePlayer(username);
    }

    public void start() {
        game.start();
    }

    public void end() {
        game.end();
    }

    public void process(String username, Message message) {

        if (message instanceof Action)
            game.process(username, (Action) message);
        else
            sendUser(username, new WarnMessage("warning"));
    }
}
