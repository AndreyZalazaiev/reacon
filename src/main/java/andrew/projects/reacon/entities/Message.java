package andrew.projects.reacon.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMessage;
    private String idUser;
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "mm:hh mm-dd")
    private LocalDateTime sentDate;
    private Integer idConversation;
    private String text;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idMessage", referencedColumnName = "idMessage")
    private List<Attachment> attachments = new ArrayList<>();
    public void addAttachment(Attachment a) {
        attachments.add(a);
    }

    @PrePersist
    protected void onCreate() {
        sentDate = LocalDateTime.now();
    }
}
