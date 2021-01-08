package com.multiplayergameserver.app.models.messages;

import lombok.Data;

@Data
public class WarnMessage implements Message {

    private final String body;

    public String getType() {
        return "warning";
    }
}
