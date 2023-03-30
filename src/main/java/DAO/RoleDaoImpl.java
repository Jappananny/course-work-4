package DAO;

import config.HibernateSessionFactoryUtil;
import model.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    public void deleteRole(Long id) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Role role = session.get(Role.class, id);
            session.remove(role);
            transaction.commit();
        }
    }
    @Override
    public void updateRole(Role role) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(role);
            transaction.commit();
        }
    }
}
