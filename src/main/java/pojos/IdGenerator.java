package pojos;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "T_ID_GENERATOR")
@SequenceGenerator(name = "PKey", sequenceName = "T_ID_GENERATOR_SEQ")
public class IdGenerator {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PKey")
    @Column(name = "F_ID")
    private Long id;

    @OneToMany (mappedBy = "commentedObjectId")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<CommentVO> commentVOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CommentVO> getCommentVOList() {
        return commentVOList;
    }

    public void setCommentVOList(List<CommentVO> commentVOList) {
        this.commentVOList = commentVOList;
    }
}
