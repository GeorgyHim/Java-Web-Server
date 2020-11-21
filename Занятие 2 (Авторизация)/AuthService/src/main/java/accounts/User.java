package accounts;

/**
 * Пользователь
 */
public class User {

    /** Логин пользователя*/
    private final String login;

    /** Пароль */
    private String password;

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
}
