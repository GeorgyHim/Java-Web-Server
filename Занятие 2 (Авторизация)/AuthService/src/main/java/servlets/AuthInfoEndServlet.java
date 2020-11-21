package servlets;

import accounts.AccountService;
import accounts.User;
import accounts.UserAlreadyAuthorized;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthInfoEndServlet extends HttpServlet {

    /** Ссылка на {@link accounts.AccountService} */
    private final AccountService accountService;

    /** Сериализатор в Json */
    private static Gson gson = new Gson();

    public AuthInfoEndServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Метод получения авторизованного пользователя
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setContentType(response);

        String sessionId = request.getSession().getId();
        User user = accountService.getAuthorizedUser(sessionId);
        if (user == null) {
            response.getWriter().println("User not authorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        returnData(response, gson.toJson(user));
    }

    /**
     * Метод авторизации пользователя
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

        User user = accountService.getUserByLogin(login);
        if (user == null || !user.getPassword().equals(password)) {
            response.getWriter().println("Bad login or password");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            accountService.loginUser(request.getSession().getId(), user);
        } catch (UserAlreadyAuthorized userAlreadyAuthorized) {
            response.getWriter().println("User already authorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        returnData(response, gson.toJson(user));
    }

    /**
     * Метод завершения сессии пользователя
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setContentType(response);

        String sessionId = request.getSession().getId();
        User user = accountService.getAuthorizedUser(sessionId);

        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        accountService.logoutUser(sessionId);
        returnData(response, "Goodbye!");
    }

    /**
     * Запись объекта user в response в формате json
     */
    private void returnData(HttpServletResponse response, String data) throws IOException {
        response.getWriter().println(data);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    /**
     * Метод установления нужного типа контента для response
     */
    private void setContentType(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
    }
}
