package andrew.projects.reacon.entities;

import andrew.projects.reacon.enums.ConversationTypes;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idConversation;
    private ConversationTypes conversationType;
    @NonNull
    @Column(columnDefinition = "varchar(255) default 'Simple group'")
    private String converstaionName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idConversation", referencedColumnName = "idConversation")
    private List<Participant> participants;

    public void addParticipant(Participant p) {
        participants.add(p);
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idConversation", referencedColumnName = "idConversation")
    private List<Message> messages;

    public void addMessages(Message m) {
        messages.add(m);
    }
}
