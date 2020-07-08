package andrew.projects.reacon.entities;

import andrew.projects.reacon.repos.MessageRepo;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MessageTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    MessageRepo messageRepo;
    @Test
    public void  CreatingMessageTest()
    {
        Message msg = new Message();
        msg.setIdUser(1);
        msg.setIdConversation(1);
        msg.setText("Hey this is a test msg");
        msg.setAttachments(null);
        messageRepo.save(msg);
        val msgInDb = testEntityManager.persist(msg);
        Assert.assertEquals(msgInDb,msg);
    }
    @Test
    public void AutomaticallySettingDateOnMessage()
    {
        Message msg = new Message();
        msg.setIdUser(1);
        msg.setIdConversation(1);
        msg.setText("Hey this is a test msg");
        msg.setAttachments(null);
        messageRepo.save(msg);
        val msgInDb = testEntityManager.persist(msg);
        Assert.assertFalse(msgInDb.getSentDate()==null);
        Assert.assertEquals(msgInDb.getSentDate(),msg.getSentDate());

    }
}
