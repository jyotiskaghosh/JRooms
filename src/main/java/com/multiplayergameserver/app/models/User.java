package com.multiplayergameserver.app.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@Document(collection="users")
public class User {

    @Id
    private String id;

    @NotNull
    @Indexed(unique=true)
    @Size(max=24, message="password must be at most 24 characters long")
    private String username;

    @NotNull
    @Size(min=5, max=24, message="password must be at least 5 and at most 24 characters long")
    private String password;

    private boolean active;

    private List<String> roles;
}
