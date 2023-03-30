package DAO;

import model.Role;
import model.User;

public interface RoleDao {
    Long createRole(Role role);

    void deleteRole(Long id);

    void updateRole(Role role);
}
