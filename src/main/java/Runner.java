import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojos.CommentVO;
import pojos.IdGenerator;
import pojos.GroupVO;
import pojos.LessonVO;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    static SessionFactory sf;
    static Session session;
    static HibConfiguration cfg;

    public static void main(String[] args) {
        cfg = new HibConfiguration();
        sf = cfg.buildSessionFactory();
        GroupVO temp1 = null;
        CommentVO temp2 = null;

        //Fulfilling database:
        // group1, group2, lesson1, lesson;

        GroupVO g1 = new GroupVO();
        g1.setGroupName("Group 1");
        IdGenerator i1 = new IdGenerator();
        g1.setIdGenerator(i1);

        GroupVO g2 = new GroupVO();
        g2.setGroupName("Group 2");
        IdGenerator i2 = new IdGenerator();
        g2.setIdGenerator(i2);

        LessonVO les1 = new LessonVO();
        les1.setLessonName("Lesson 1");
        IdGenerator i3 = new IdGenerator();
        les1.setIdGenerator(i3);

        LessonVO les2 = new LessonVO();
        les2.setLessonName("Lesson 2");
        IdGenerator i4 = new IdGenerator();
        les2.setIdGenerator(i4);



        //Fulfilling database with comments and answers

        CommentVO c1 = new CommentVO();
        c1.setText("Comment1 to Group1");
        IdGenerator i5 = new IdGenerator();
        c1.setIdGenerator(i5);
        List<CommentVO> commentVOList = new ArrayList<CommentVO>();
        i5.setCommentVOList(commentVOList);
        c1.setCommentedObjectId(i1);

        CommentVO c2 = new CommentVO();
        c2.setText("Comment1 to Lesson1");
        IdGenerator i6 = new IdGenerator();
        c2.setIdGenerator(i6);
        c2.setCommentedObjectId(i3);

        CommentVO c1a1 = new CommentVO();
        c1a1.setText("Answer1 to Comment1");
        IdGenerator i7 = new IdGenerator();
        c1a1.setIdGenerator(i7);
        c1a1.setCommentedObjectId(i5);

        CommentVO c1a2 = new CommentVO();
        c1a2.setText("Answer2 to Comment1");
        IdGenerator i8 = new IdGenerator();
        c1a2.setIdGenerator(i8);
        c1a2.setCommentedObjectId(i5);

        session = sf.openSession();
        session.save(g1);
        session.save(g2);
        session.save(les1);
        session.save(les2);
        session.flush();
        session.close();

        /*
        session = sf.openSession();
        session.save(g1);
        session.save(g2);
        session.save(les1);
        session.save(c1a2);
        session.delete(c2);
        session.delete(les1);
        session.flush();
        session.close();
        */

    }
}
