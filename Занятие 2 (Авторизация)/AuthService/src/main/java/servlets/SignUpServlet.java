package servlets;

import accounts.AccountService;
import accounts.User;
import accounts.UserAlreadyRegistered;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends AccountServlet {

    public SignUpServlet(AccountService accountService) {
        super(accountService);
    }

    /**
     * Метод регистрации пользователя
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setContentType(response);

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        User user = new User(login, password);
        try {
            accountService.registerNewUser(user);
        } catch (UserAlreadyRegistered userAlreadyRegistered) {
            userAlreadyRegistered.printStackTrace();
        }
    }
}
