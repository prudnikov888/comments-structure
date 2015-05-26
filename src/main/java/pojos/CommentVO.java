package pojos;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "T_COMMENT")
public class CommentVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GenericGenerator(name ="gen",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "idGenerator")
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @Column(name = "F_ID")
    private Long id;

    @OneToOne
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @PrimaryKeyJoinColumn
    private IdGenerator idGenerator;

    @Column(name = "F_TEXT")
    private String text;

    //field for id of the object, that is being commented
    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @JoinColumn (name = "F_COMMENTED_OBJECT_ID")
    private IdGenerator commentedObjectId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public IdGenerator getCommentedObjectId() {
        return commentedObjectId;
    }

    public void setCommentedObjectId(IdGenerator commentedObjectId) {
        this.commentedObjectId = commentedObjectId;
    }

    public IdGenerator getIdGenerator() {
        return idGenerator;
    }

    public void setIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentVO commentVO = (CommentVO) o;

        if (id != null ? !id.equals(commentVO.id) : commentVO.id != null) return false;
        if (text != null ? !text.equals(commentVO.text) : commentVO.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }



}
