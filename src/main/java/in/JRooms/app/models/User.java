package in.JRooms.app.models;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Document(collection="users")
public class User {

    @Id
    private String id;

    @NotNull
    @Indexed(unique=true)
    @Size(max=24, message="username must be at most 24 characters long")
    private String username;

    @NotNull
    @Size(min=5, message="password must be at least 5 characters long")
    private String password;

    private boolean active = true;

    private Set<String> roles;
}
