package andrew.projects.reacon.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class User {
    @Id
    private String idUser;
    private String fullName;
    @Column(columnDefinition = "varchar(255) default 'https://i.stack.imgur.com/34AD2.jpg'")
    private String userpic;
    private String email;
    private String gender;
    private String locale;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "mm:hh mm-dd")
    private LocalDateTime lastTimeOnline;

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

    @PrePersist
    protected void onCreate() {
        lastTimeOnline = LocalDateTime.now();
    }


}
