package andrew.projects.reacon.repos;

import andrew.projects.reacon.entities.Conversation;
import andrew.projects.reacon.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface ConversationRepo extends CrudRepository<Conversation, Integer> {
    @Query(value = "Select c.id_conversation,c.conversation_name,c.conversation_type,c.conversation_image,c.last_message_date " +
            "From conversation c, participant,user\n" +
            "WHERE c.id_conversation=participant.id_conversation\n" +
            "and user.id_user = participant.id_user\n" +
            "and user.id_user=:currentUser",
            nativeQuery = true)
    Iterable<Conversation> findAllChatsForUserById(@Param("currentUser") String currentUser);

    @Query("From User u\n" +
            "Where u.idUser in (\n" +
            "Select p.idUser from Participant p\n" +
            "where p.idConversation=:idConversation)")
    Iterable<User> findAllUsersInChat(@Param("idConversation") Integer id);
  /*  @Query("FROM Message m\n" +
            "Where m.idConversation=:idConversation\n" +
            "and m.sentDate = \n" +
            "(Select max(Message.sentDate) From Message)")
    Iterable<User> findLastMessageInChat(@Param("idConversation") Integer id);*/

}
