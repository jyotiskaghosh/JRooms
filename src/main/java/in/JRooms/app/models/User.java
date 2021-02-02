package in.JRooms.app.models;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(unique = true)
    @Size(max=24, message="username must be at most 24 characters long")
    private String username;

    @NotNull
    @Size(min=5, message="password must be at least 5 characters long")
    private String password;
    private boolean active = true;

    private Role role;
}

enum Role {
    USER
}