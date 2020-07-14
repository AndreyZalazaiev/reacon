package andrew.projects.reacon.repos;

import andrew.projects.reacon.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserRepo extends JpaRepository<User, String> {
    @Query
    Optional<User> findByIdUser(String idUser);
}
