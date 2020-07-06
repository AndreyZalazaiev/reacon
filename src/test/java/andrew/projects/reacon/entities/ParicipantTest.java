package andrew.projects.reacon.entities;

import andrew.projects.reacon.enums.ParticipantTypes;
import andrew.projects.reacon.repos.ParicipantRepo;
import andrew.projects.reacon.repos.UserRepo;
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
    ParicipantRepo paricipantRepo;

    @Autowired
    TestEntityManager testEntityManager;
    @Test
    public void participatingTest()
    {
        Participant p = new Participant();
        p.setIdUser(1);
        p.setIsBlocked(false);
        p.setTypeOfParticipant(ParticipantTypes.Admin);
        paricipantRepo.save(p);
        Participant currentlyInDb=testEntityManager.persist(p);
        Assert.assertEquals(p,currentlyInDb);

    }
}
