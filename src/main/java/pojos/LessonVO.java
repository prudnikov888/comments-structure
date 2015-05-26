package pojos;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "T_LESSON")
public class LessonVO implements Serializable {

    private static final long serialVersionUID = 3L;
    @Id
    @GenericGenerator(name ="gen",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "idGenerator")
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @Column(name = "F_ID")
    private Long id;

    @Column(name = "F_LESSON_NAME")
    private String lessonName;

    @OneToOne
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @PrimaryKeyJoinColumn
    private IdGenerator idGenerator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
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
	if (!(o instanceof LessonVO)) return false;

	LessonVO groupVO = (LessonVO) o;
	return true;
    }
    @Override
    public int hashCode() {
	int result = id != null ? id.hashCode() : 0;
	result = 31 * result + (lessonName != null ? lessonName.hashCode() : 0);
	return result;
    }

    @Override
    public String toString() {
	return "GroupVO{" + "id=" + id + ", groupName=" + lessonName + "}";
    }


}
