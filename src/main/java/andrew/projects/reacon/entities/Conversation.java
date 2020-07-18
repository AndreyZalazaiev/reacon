package andrew.projects.reacon.entities;

import andrew.projects.reacon.Util.DateFormater;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Conversation  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idConversation;
    @Column(columnDefinition = "varchar(255) default 'Chat'")
    private String conversationType;
    private LocalDateTime lastMessageDate;
    @NonNull
    @Column(columnDefinition = "varchar(255) default 'Simple group'")
    private String conversationName;
    @Column(columnDefinition = "varchar(255) default 'images/chat.png'")
    private String conversationImage;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idConversation", referencedColumnName = "idConversation")
    private List<Participant> participants;
    public void addParticipant(Participant p) {
        participants.add(p);
    }
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idConversation", referencedColumnName = "idConversation")
    private List<Message> messages;
    public void addMessages(Message m) {
        messages.add(m);
    }

    @PreUpdate
    protected void onUpdate() {
        lastMessageDate= LocalDateTime.now();
    }
    @PrePersist
    protected void onCreate(){lastMessageDate= LocalDateTime.now();}

}
