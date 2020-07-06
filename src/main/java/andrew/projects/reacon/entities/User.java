package andrew.projects.reacon.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;
    private String email;
    private String fullName;
    @Column(columnDefinition = "date default curdate() ")
    private Date lastTimeOnline;
    private String login;
    private String pass;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private List<Participant> participant;

    public void addParticipant(Participant p) {
        participant.add(p);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private List<Message> messages;
    public void addMessgaes(Message m) {
        messages.add(m);
    }



}
