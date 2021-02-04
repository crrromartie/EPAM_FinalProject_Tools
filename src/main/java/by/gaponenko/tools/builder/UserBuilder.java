package by.gaponenko.tools.builder;

import by.gaponenko.tools.entity.User;

/**
 * The class represents user builder.
 *
 * @author Haponenka Ihar
 * @version 1.0
 */
public class UserBuilder {
    private long userId;
    private String login;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private User.Role role;
    private User.Status status;
    private String avatar;

    public long getUserId() {
        return userId;
    }

    public UserBuilder setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public UserBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public User.Role getRole() {
        return role;
    }

    public UserBuilder setRole(User.Role role) {
        this.role = role;
        return this;
    }

    public User.Status getStatus() {
        return status;
    }

    public UserBuilder setStatus(User.Status status) {
        this.status = status;
        return this;
    }

    public String getAvatar() {
        return avatar;
    }

    public UserBuilder setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }
}
