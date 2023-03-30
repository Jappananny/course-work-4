package model;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "user_password")
    private String userPassword;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns=  @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns= @JoinColumn(name="role_id", referencedColumnName = "id") )
    private List<Role> roles;

    public User() {
    }

    public User(String userName, String userLogin, String userPassword) {
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public User(Long id, String userName, String userLogin, String userPassword) {
        this.id = id;
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
    }

    public User(Long id, String userName, String userLogin, String userPassword, List<Role> roles) {
        this.id = id;
        this.userName = userName;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void addUserToRole(Role role) {
        if (roles != null && !roles.contains(role)) {
            roles.add(role);
            role.getUsers().add(this);
        }
    }
    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(userName, user.userName) && Objects.equals(userLogin, user.userLogin) && Objects.equals(userPassword, user.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userLogin, userPassword);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}