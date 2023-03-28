import DAO.RoleDao;
import DAO.RoleDaoImpl;
import DAO.UserDao;
import DAO.UserDaoImpl;
import model.Role;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        RoleDao roleDao = new RoleDaoImpl();

        Role role1 = new Role("Role1");
        Role role2 = new Role("Role2");

        User user1 = new User("User1", "Login1", "Password1");
        User user2 = new User("User2", "Login2", "Password2");
        User user3 = new User("User3", "Login3", "Password3");

        Long idRole1 = roleDao.createRole(role1);
        Long idRole2 = roleDao.createRole(role2);
        //Не знаю почему не добавляется роль, возможно надо делать метод в Impl на добавление её в таблицу
        user1.addUserToRole(role2);
        user1.addUserToRole(role1);
        user2.addUserToRole(role1);
        user3.addUserToRole(role1);

        user1.setRoles(List.of(role1, role2));
        user2.setRoles(List.of(role1));
        user3.setRoles(List.of(role1));

        Long id1 = userDao.create(user1);
        Long id2 = userDao.create(user2);
        Long id3 = userDao.create(user3);


        System.out.println("ПОЛУЧЕНИЕ СПИСКА ПОЛЬЗОВАТЕЛЕЙ ИЗ БД (БЕЗ РОЛЕЙ)");
        System.out.println(userDao.readAll());
        System.out.println("ПОЛУЧАТЬ КОНКРЕТНОГО ПОЛЬЗОВАТЕЛЯ (С ЕГО РОЛЯМИ) ИЗ БД");
        System.out.println(roleDao.getUserWithRolesById(id1) + " " + userDao.getUserById(id1).getRoles());
        System.out.println("ПОЛУЧИТЬ СПИСОК ПОЛЬЗОВАТЕЛЕЙ ПО КОНКРЕТНОЙ РОЛИ");
        role1 = roleDao.getUsersByRole(role1);
        role1.getUsers().forEach(System.out::println);


        userDao.deleteUser(id1);
    }
}
