package DAO;

import config.HibernateSessionFactoryUtil;
import model.Role;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.mapping.List;
import org.hibernate.query.Query;

public class RoleDaoImpl implements RoleDao{
    @Override
    public Long createRole(Role role) {
        Long result;
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            result = (Long)session.save(role);
            transaction.commit();
        }
        return result;
    }
    @Override
    public User getUserWithRolesById(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("select u from User u left join fetch u.roles where u.id = :id", User.class);
            query.setParameter("id", id);
            return query.uniqueResult();
        }
    }
    @Override
    public Role getUsersByRole(Role role) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<User> query = session.createQuery("select u from User u join u.roles r where r.id = :id", User.class);
            query.setParameter("id", role.getRoleName());
            transaction.commit();

            return (Role) query.list();
        }
    }
    @Override
    public void deleteRole(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Role role = session.get(Role.class, id);
            session.remove(role);
            transaction.commit();
        }
    }
}
