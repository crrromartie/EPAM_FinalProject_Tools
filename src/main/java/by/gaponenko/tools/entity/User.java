package by.gaponenko.tools.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class User extends Entity {
    public enum Role {
        ADMIN(1),
        CLIENT(2),
        GUEST(3);

        private final int roleId;

        Role(int roleId) {
            this.roleId = roleId;
        }

        public int getRoleId() {
            return roleId;
        }

        private static final Map<Integer, Role> ROLES_MAP = new HashMap<>();

        static {
            for (Role role : values()) {
                ROLES_MAP.put(role.getRoleId(), role);
            }
        }

        public static Role getRoleById(int roleId) {
            return ROLES_MAP.get(roleId);
        }
    }

    public enum Status {
        ACTIVE(1),
        UNCONFIRMED(2),
        BLOCKED(3);

        private final int statusId;

        Status(int statusId) {
            this.statusId = statusId;
        }

        public int getStatusId() {
            return statusId;
        }

        private static final Map<Integer, Status> STATUSES_MAP = new HashMap<>();

        static {
            for (Status status : values()) {
                STATUSES_MAP.put(status.getStatusId(), status);
            }
        }

        public static Status getStatusById(int statusId) {
            return STATUSES_MAP.get(statusId);
        }
    }

    private long userId;
    private String login;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Role role;
    private Status status;
    private String avatar;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (userId != user.userId) {
            return false;
        }
        if (login != null ? !login.equals(user.login) : user.login != null) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) {
            return false;
        }
        if (email != null ? !email.equals(user.email) : user.email != null) {
            return false;
        }
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) {
            return false;
        }
        if (role != user.role) {
            return false;
        }
        if (status != user.status) {
            return false;
        }
        return avatar != null ? avatar.equals(user.avatar) : user.avatar == null;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(userId);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("login=" + login)
                .add("name=" + name)
                .add("surname=" + surname)
                .add("email=" + email)
                .add("phone=" + phone)
                .add("role=" + role)
                .add("status=" + status)
                .toString();
    }
}
