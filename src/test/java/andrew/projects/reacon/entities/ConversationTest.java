package andrew.projects.reacon.entities;


import andrew.projects.reacon.repos.ConversationRepo;
import andrew.projects.reacon.repos.ParticipantRepo;
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
    ParticipantRepo participantRepo;
    @Autowired
    TestEntityManager testEntityManager;
    @Test
    public void CreatingConversationTest()
    {
        Conversation conversation = new Conversation();
        conversation.setConversationType("Group");
        val currentlyInDb = testEntityManager.persist(conversation);
        Assert.assertEquals(conversation,currentlyInDb);
    }
    @Test
    public void AddParticipantsToConversation()
    {
        Participant p = new Participant();
        InitializeParticipant(p);

        Conversation conversation = new Conversation();
        conversation.setConversationType("Group");
        conversation.addParticipant(p);
        val conversationInDb = testEntityManager.persist(conversation);
        Assert.assertEquals(conversation,conversationInDb);
        Assert.assertEquals(conversation.getParticipants().contains(p),true);


    }
    public void InitializeParticipant(Participant p)
    {
        p.setIdUser("1");
        p.setIsBlocked(false);
        p.setTypeOfParticipant("Admin");
        participantRepo.save(p);
        val participantInDb = testEntityManager.persist(p);
        Assert.assertEquals(p,participantInDb);
    }
}
