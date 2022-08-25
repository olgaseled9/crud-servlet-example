package by.seledtsova.dto;

import by.seledtsova.model.RoleValue;

/**
 * Container for User representing data, getter and setter methods.
 * @see UserDto
 */
public class UserDto {

    private Long userId;
    private String firstname;
    private String lastname;
    private String login;
    private String password;
    private RoleValue role;

    public UserDto() {
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

    public RoleValue getRole() {
        return role;
    }

    public void setRole(RoleValue role) {
        this.role = role;
    }

    /**
     * Checks if an object is new.
     * @return true, if object is new
     */
    public boolean isNew() {
        return this.getUserId() == null;
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
