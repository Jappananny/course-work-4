package DAO;

import config.HibernateSessionFactoryUtil;
import model.Role;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityExistsException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public Long create(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Long result = (Long) session.save(user);
                transaction.commit();
                return result;
            } catch (EntityExistsException e) {
                transaction.rollback();
                throw new RuntimeException("User with id " + user.getId() + " already exists!", e);
            }
        }
    }
    @Override
    public java.util.List<User> readAll() {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            java.util.List<User> user = session.createQuery("from User", User.class).list();
            transaction.commit();
            return user;
        }
    }
    @Override
    public User getUserWithRolesById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("from User u left join fetch u.roles where u.id = :id", User.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }
    @Override
    public List<User> getUsersByRole(Role role) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User u JOIN fetch u.roles r WHERE r = :role", User.class);
            query.setParameter("role", role);
            return query.getResultList();
        }
    }
    @Override
    public User getUserById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }
    @Override
    public void deleteUser(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
        }
    }
    @Override
    public void saveOrUpdateUser(User user) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(user);
            session.getTransaction().commit();
        }
    }
}
