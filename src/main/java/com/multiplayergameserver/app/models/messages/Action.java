package com.multiplayergameserver.app.models.messages;

public abstract class Action implements Message {

    public String getType() {
        return "action";
    }
}
