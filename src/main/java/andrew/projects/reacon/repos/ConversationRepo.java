package andrew.projects.reacon.repos;

import andrew.projects.reacon.entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ConversationRepo extends CrudRepository<Conversation,Integer> {
//todo find all conversation for user and test it
}
