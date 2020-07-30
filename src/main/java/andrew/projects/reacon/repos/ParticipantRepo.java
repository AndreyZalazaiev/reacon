package andrew.projects.reacon.repos;

import andrew.projects.reacon.entities.Message;
import andrew.projects.reacon.entities.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface ParticipantRepo extends JpaRepository<Participant,Integer> {
    @Query("select p from Participant p " +
            "WHERE  p.idConversation=:idConversation " +
            "group by p.idUser")
    Iterable<Participant> allParticipantsInChat(@Param("idConversation") Integer id);

    @Query
    @Transactional
    Participant deleteByIdConversationAndIdUser(@Param("idConversation") Integer idConversation,@Param("idUser") String idUser);


}
