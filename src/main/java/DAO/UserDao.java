package DAO;

import model.Role;
import model.User;
import org.hibernate.mapping.List;

public interface UserDao {

    Long create(User user);

    java.util.List<User> readAll();

    User getUserById(Long id);

    void deleteUser(Long id);

    void saveOrUpdateUser(User user);
}
