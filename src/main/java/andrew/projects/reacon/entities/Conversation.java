package andrew.projects.reacon.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idConversation;
    @Column(columnDefinition = "varchar(255) default 'Chat'")
    private String conversationType;
    @NonNull
    @Column(columnDefinition = "varchar(255) default 'Simple group'")
    private String converstaionName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idConversation", referencedColumnName = "idConversation")
    private List<Participant> participants = new ArrayList<>();
    public void addParticipant(Participant p) {
        participants.add(p);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idConversation", referencedColumnName = "idConversation")
    private List<Message> messages = new ArrayList<>();
    public void addMessages(Message m) {
        messages.add(m);
    }
}
