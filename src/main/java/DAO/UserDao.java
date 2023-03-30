package DAO;

import model.Role;
import model.User;

import java.util.List;

public interface UserDao {

    Long create(User user);

    java.util.List<User> readAll();

    User getUserWithRolesById(Long id);

    User getUserById(Long id);

    List<User> getUsersByRole(Role role);

    void deleteUser(Long id);

    void saveOrUpdateUser(User user);
}
