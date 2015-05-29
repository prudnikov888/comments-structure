import org.hibernate.cfg.AnnotationConfiguration;
import pojos.CommentVO;
import pojos.IdGenerator;
import pojos.GroupVO;
import pojos.LessonVO;

public class HibConfiguration extends AnnotationConfiguration {

    public HibConfiguration() {
	super();
	this
	    .addAnnotatedClass(GroupVO.class)
	    .addAnnotatedClass(CommentVO.class)
	    .addAnnotatedClass(LessonVO.class)
		.addAnnotatedClass(IdGenerator.class)
	    .setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect")
	    .setProperty("hibernate.connection.url", "jdbc:oracle:thin:@localhost:1521:xe")
	    .setProperty("hibernate.connection.username", "test")
	    .setProperty("hibernate.connection.password", "test")
	    .setProperty("hibernate.connection.autocommit", "true")
	    .setProperty("hibernate.default_schema", "test")
		.setProperty("hibernate.show_sql", "false")
		.setProperty("hibernate.hbm2ddl.auto", "create-drop");
	}
}
