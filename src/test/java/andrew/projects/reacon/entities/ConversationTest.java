package andrew.projects.reacon.entities;

import andrew.projects.reacon.enums.ConversationTypes;
import andrew.projects.reacon.enums.ParticipantTypes;
import andrew.projects.reacon.repos.ConversationRepo;
import andrew.projects.reacon.repos.ParicipantRepo;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ConversationTest {
    @Autowired
    ConversationRepo conversationRepo;
    @Autowired
    ParicipantRepo paricipantRepo;
    @Autowired
    TestEntityManager testEntityManager;
    @Test
    public void CreatingConversationTest()
    {
        Conversation conversation = new Conversation();
        conversation.setConversationType(ConversationTypes.Group);
        val currentlyInDb = testEntityManager.persist(conversation);
        Assert.assertEquals(conversation,currentlyInDb);
    }
    @Test
    public void AddParticipantsToConversation()
    {
        Participant p = new Participant();
        p.setIdUser(1);
        p.setIsBlocked(false);
        p.setTypeOfParticipant(ParticipantTypes.Admin);
        paricipantRepo.save(p);
/* i`m weak at testing, later
        Conversation conversation = new Conversation();
        conversation.setConversationType(ConversationTypes.Group);
        conversation.setParticipant(p);
        conversationRepo.save(conversation);
        val currentConversationInDb = testEntityManager.persist(conversation);
        Assert.assertEquals(1,currentConversationInDb);
        */

    }
}
