package accounts;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для управления аккаунтами пользователей
 */
public class AccountService {

    /** Перечень зарегистрированных пользователей со связью login-{@link User} */
    private final Map<String, User> registeredUsers;

    /** Перечень авторизованных пользователей со связью sessionId-{@link User} */
    private final Map<String, User> authorizedUsers;

    public AccountService() {
        this.registeredUsers = new HashMap<>();
        this.authorizedUsers = new HashMap<>();
    }


}
