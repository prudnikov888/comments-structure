import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojos.CommentVO;
import pojos.IdGenerator;
import pojos.GroupVO;
import pojos.LessonVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Runner {
    static SessionFactory sf;
    static Session session;
    static HibConfiguration cfg;

    public static void main(String[] args) {
        cfg = new HibConfiguration();
        sf = cfg.buildSessionFactory();

        //creating and saving new group
        GroupVO g1 = new GroupVO();
        g1.setGroupName("Group1");
        g1.setIdGenerator(new IdGenerator());
        session = sf.openSession();
        session.save(g1);
        session.flush();
        session.close();

        //creating and saving new lesson
        LessonVO les1 = new LessonVO();
        les1.setLessonName("Lesson1");
        les1.setIdGenerator(new IdGenerator());
        session = sf.openSession();
        session.save(les1);
        session.flush();
        session.close();

        CommentModel m = new CommentModel();

        //creating and saving comments to Group1
        m.commentedObjectId = g1.getId();
        m.text = "Comment1 to Group1";
        addComment(m);
        m.text = "Comment2 to Group1";
        addComment(m);

        //creating and saving comments to Lesson1
        m.commentedObjectId = les1.getId();
        m.text = "Comment1 to Lesson1";
        addComment(m);
        m.text = "Comment2 to Lesson1";
        addComment(m);

        //creating and saving answers to Comment1 to Group1
        session = sf.openSession();
        IdGenerator gen = (IdGenerator) session.get(IdGenerator.class, g1.getId());
        session.close();
        m.commentedObjectId = gen.getCommentVOList().get(0).getId();
        m.text = "Answer1 to Comment1 to Group1";
        addComment(m);
        m.text = "Answer2 to Comment1 to Group1";
        addComment(m);
        m.text = "Answer3 to Comment1 to Group1";
        addComment(m);

        //creating and saving answers to Comment1 to Lesson1
        session = sf.openSession();
        gen = (IdGenerator) session.get(IdGenerator.class, les1.getId());
        session.close();
        m.commentedObjectId = gen.getCommentVOList().get(0).getId();
        m.text = "Answer1 to Comment1 to Lesson1";
        addComment(m);
        m.text = "Answer2 to Comment1 to Lesson1";
        addComment(m);

        //getting comments and answers for Group1
        List<CommentModel> mList = getComments(g1.getId());
        for (CommentModel c : mList) {
            System.out.println(c.text);
            List<CommentModel> aList = getComments(c.id);
            for (CommentModel a : aList) {
                System.out.println(a.text);
            }
        }
        //getting comments and answers for Lesson1
        System.out.println("");
        mList = getComments(les1.getId());
        for (CommentModel c : mList) {
            System.out.println(c.text);
            List<CommentModel> aList = getComments(c.id);
            for (CommentModel a : aList) {
                System.out.println(a.text);
            }
        }
    }

    //DTO object for transferring comments
    static class CommentModel {
        Long id;
        String text;
        Long commentedObjectId;

        @Override
        public String toString() {
            return this.text;
        }

        public CommentModel() {
        }
        public CommentModel(CommentVO c) {
            this.id = c.getIdGenerator().getId();
            this.text = c.getText();
            this.commentedObjectId = c.getCommentedObjectId().getId();
        }
    }

    //transform CommentModel to CommentVO object
    public static CommentVO modelToComment(CommentModel commentModel) {
        CommentVO c = new CommentVO();
        c.setText(commentModel.text);
        c.setIdGenerator(new IdGenerator());
        session = sf.openSession();
        IdGenerator gen = (IdGenerator) session.get(IdGenerator.class, commentModel.commentedObjectId);
        c.setCommentedObjectId(gen);
        session.close();
        return c;
    }

    //addComment method
    public static void addComment(CommentModel commentModel) {
        CommentVO c = modelToComment(commentModel);
        IdGenerator commentedObjectId = c.getCommentedObjectId();
        List<CommentVO> cList;
        if (commentedObjectId.getCommentVOList() != null) {
            cList = commentedObjectId.getCommentVOList();
            cList.add(c);
        } else {
            cList = new ArrayList<CommentVO>();
            cList.add(c);
        }
        commentedObjectId.setCommentVOList(cList);
        c.setCommentedObjectId(commentedObjectId);
        session = sf.openSession();
        session.save(c);
        session.flush();
        session.close();
    }

    //get comments by id of the commented object
    public static List<CommentModel> getComments(long commentedObjectId) {
        List<CommentModel> mList = new ArrayList<CommentModel>();
        String str = "select commentVOList from IdGenerator gen where gen.id = :commentedObjectId";
        session = sf.openSession();
        Query query = session.createQuery(str);
        query.setParameter("commentedObjectId", commentedObjectId);
        List<CommentVO> commentVOList = (List<CommentVO>) query.list();
        session.close();
        for (CommentVO c : commentVOList) {
            mList.add(new CommentModel(c));
        }
        return mList;
    }
}
