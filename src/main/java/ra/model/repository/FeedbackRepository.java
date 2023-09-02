package ra.model.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ra.model.entity.Feedback;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FeedbackRepository implements IFeedbackRepository {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.config.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Feedback> findAll() {
        List<Feedback> list = new ArrayList<>();
        TypedQuery<Feedback> typedQuery = entityManager.createQuery("select f from Feedback as f", Feedback.class);
        list = typedQuery.getResultList();
        return list;
    }

    @Override
    public void save(Feedback feedback) {
        Session session = null;
        Transaction transaction = null;
        try {
            // khởi tạo phiên
            session = sessionFactory.openSession();
            // bắt đầu 1 giao dịch
            transaction = session.beginTransaction();
            if (feedback.getId() == null) {
                session.save(feedback);
            } else {
                Feedback old = findById(feedback.getId());
                old.copy(feedback);
                session.saveOrUpdate(old);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.isActive();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Feedback findById(Long id) {
        TypedQuery<Feedback> typedQuery = entityManager.createQuery("select f from Feedback as f where f.id =:id", Feedback.class);
        typedQuery.setParameter("id", id);
        // lấy về 1 đối tượng
        Feedback f = typedQuery.getSingleResult();
        return f;
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(findById(id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.isActive();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
