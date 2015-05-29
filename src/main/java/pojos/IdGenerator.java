package pojos;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "T_ID_GENERATOR")
@SequenceGenerator(name = "PKey", sequenceName = "T_ID_GENERATOR_SEQ")
public class IdGenerator implements Serializable {
    private static final long serialVersionUID = 4L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PKey")
    @Column(name = "F_ID")
    private Long id;

    @OneToMany (fetch = FetchType.EAGER,mappedBy = "commentedObjectId")
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

    //Rewrite later
    @Override
    public boolean equals(Object o) {

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        return result;
    }

}
