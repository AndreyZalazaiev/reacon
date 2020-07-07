package andrew.projects.reacon.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Data
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer idAttachment;
    @Column(columnDefinition = "varchar(255) default 'File'")
    String contentType;
    String link;
    Integer idMessage;
}
