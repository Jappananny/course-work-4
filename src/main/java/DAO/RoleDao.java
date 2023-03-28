package DAO;

import model.Role;
import model.User;

public interface RoleDao {
    Long createRole(Role role);

    User getUserWithRolesById(Long id);

    Role getUsersByRole(Role role);

    void deleteRole(Long id);
}
