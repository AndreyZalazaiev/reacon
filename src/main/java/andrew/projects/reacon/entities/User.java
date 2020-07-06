package andrew.projects.reacon.entities;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idUser;
    private String fullName;
    private String email;
    private Date lastTimeOnline;
    private String pass;
    private String login;
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
