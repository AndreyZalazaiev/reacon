package andrew.projects.reacon.repos;

import andrew.projects.reacon.entities.Message;
import andrew.projects.reacon.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;

public interface MessageRepo extends CrudRepository<Message, Integer> {

    @Query("select m from Message m ,Conversation c " +
            "where m.idConversation=:currentChatId " +
            "group by m.idMessage")
    Iterable<Message> allMessagesInChat(@Param("currentChatId") Integer id);//todo test
}
