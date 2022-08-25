package by.seledtsova.model;

/**
 * Container for User entity data, getter and setter methods.
 * @see User
 */
public class User {

    private Long userId;
    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private Role role;

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", role=" + role +
            '}';
    }
}
