package andrew.projects.reacon.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private LocalDateTime lastTimeOnline;
    private String login;
    private String pass;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private List<Participant> participant = new ArrayList<>();
    public void addParticipant(Participant p) {
        participant.add(p);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private List<Message> messages = new ArrayList<>();
    public void addMessgaes(Message m) {
        messages.add(m);
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @PrePersist
    protected void onCreate() {
       lastTimeOnline = LocalDateTime.now();
    }


}
