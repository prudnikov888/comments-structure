package pojos;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "T_GROUP")
public class GroupVO implements Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GenericGenerator(name ="gen",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "idGenerator")
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @Column(name = "F_ID")
    private Long id;

    @Column(name = "F_GROUP_NAME")
    private String groupName;

    @OneToOne
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    @PrimaryKeyJoinColumn
    private IdGenerator idGenerator;

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
	if (!(o instanceof GroupVO)) return false;

	GroupVO groupVO = (GroupVO) o;
	return true;
    }

    @Override
    public int hashCode() {
	int result = id != null ? id.hashCode() : 0;
	result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
	return result;
    }

    @Override
    public String toString() {
	return "GroupVO{" + "id=" + id + ", groupName=" + groupName + "}";
    }


}
