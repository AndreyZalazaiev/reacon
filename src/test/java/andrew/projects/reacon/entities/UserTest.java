package andrew.projects.reacon.entities;


import andrew.projects.reacon.entities.User;
import andrew.projects.reacon.repos.UserRepo;
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
public class UserTest {
    @Autowired
    UserRepo userRepo;

    @Autowired
    TestEntityManager testEntityManager;
    @Test
    public  void CreatingUserTest(){
        User usr = new User();
        usr.setIdUser("222");
        usr.setFullName("Petya Petrov");
        usr.setEmail("sdad@mail.ru");
        userRepo.save(usr);
        testEntityManager.detach(usr);
        User currentlyInDb = testEntityManager.persist(usr);
        Assert.assertEquals(currentlyInDb,userRepo.findByIdUser(currentlyInDb.getIdUser()));
    }
}
