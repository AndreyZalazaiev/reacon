package andrew.projects.reacon.entities;

import andrew.projects.reacon.enums.ParticipantTypes;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParticipant;
    private Integer idUser;
    private Integer idConversation;
    private ParticipantTypes typeOfParticipant;
    private Boolean isBlocked;
}
