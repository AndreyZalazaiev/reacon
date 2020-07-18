package andrew.projects.reacon.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParticipant;
    private String idUser;
    private Integer idConversation;
    @Column(columnDefinition = "varchar(255) default 'User'")
    private String typeOfParticipant;
    @Column(columnDefinition = "boolean default 0")
    private Boolean isBlocked;
}
