package in.JRooms.app.repositories;

import in.JRooms.app.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);
}
