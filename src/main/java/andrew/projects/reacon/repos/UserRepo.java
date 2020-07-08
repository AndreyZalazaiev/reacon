package andrew.projects.reacon.repos;

import andrew.projects.reacon.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Integer> {
    Optional<User> findByLogin(String login);
}
