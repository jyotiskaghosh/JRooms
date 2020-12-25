package com.multiplayergameserver.app.models.messages;

public class WarnMessage implements Message {

    private String body;

    public WarnMessage(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public String getType() {
        return "warning";
    }
}
