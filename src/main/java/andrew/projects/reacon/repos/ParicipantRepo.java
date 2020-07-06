package andrew.projects.reacon.repos;

import andrew.projects.reacon.entities.Participant;
import org.springframework.data.repository.CrudRepository;

public interface ParicipantRepo extends CrudRepository<Participant,Integer> {
}
