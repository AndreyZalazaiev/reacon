package andrew.projects.reacon.repos;

import andrew.projects.reacon.entities.Message;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface MessageRepo extends CrudRepository<Message, Integer> {
}
