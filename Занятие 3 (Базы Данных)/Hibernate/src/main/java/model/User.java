package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Пользователь
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    /** Идентификатор пользователя */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** Логин пользователя*/
    @Column(name = "login", unique = true, updatable = false)
    private String login;

    /** Пароль */
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login) {
        this.login = login;
        this.password = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
