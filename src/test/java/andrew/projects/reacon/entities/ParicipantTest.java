package andrew.projects.reacon.entities;

import andrew.projects.reacon.repos.ParticipantRepo;
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
public class ParicipantTest {
    @Autowired
    ParticipantRepo participantRepo;

    @Autowired
    TestEntityManager testEntityManager;
    @Test
    public void creatingParticipantTest()
    {
        Participant p = new Participant();
        p.setIdUser("1");
        p.setIsBlocked(false);
        p.setTypeOfParticipant("Admin");
        participantRepo.save(p);
        Participant currentlyInDb=testEntityManager.persist(p);
        Assert.assertEquals(p,currentlyInDb);

    }
}
