package andrew.projects.reacon.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Getter
@Setter
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
