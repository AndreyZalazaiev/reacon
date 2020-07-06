package andrew.projects.reacon.repos;

import andrew.projects.reacon.entities.Conversation;
import andrew.projects.reacon.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ConversationRepo extends CrudRepository<Conversation, Integer> {
    @Query(value = "Select c.id_conversation,c.converstaion_name,c.conversation_type From conversation c, participant,user\n" +
            "WHERE c.id_conversation=participant.id_conversation\n" +
            "and user.id_user = participant.id_user\n" +
            "and user.id_user=:currentUser",
            nativeQuery = true)
    Iterable<Conversation> findAllChatsForUserById(@Param("currentUser") Integer currentUser);

    @Query("From User u\n" +
            "Where u.idUser in (\n" +
            "Select p.idUser from Participant p\n" +
            "where p.idConversation=:idConversationForChecking)")
    Iterable<User> findAllUsersInChat(@Param("idConversationForChecking") Integer id);

}
