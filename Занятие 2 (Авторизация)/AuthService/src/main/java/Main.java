import accounts.AccountService;
import accounts.User;
import accounts.UserAlreadyRegistered;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AuthServlet;

public class Main {
    private static AccountService accountService = new AccountService();

    public static void main(String[] args) {
        //registerUsersByLogin("admin", "test");
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.addServlet(new ServletHolder(new AuthServlet(accountService)), "api/auth/");

    }

    private static void registerUsersByLogin(String... logins) {
        for (String login : logins) {
            try {
                accountService.registerNewUser(new User(login));
            }
            catch (UserAlreadyRegistered userAlreadyRegistered) {
                userAlreadyRegistered.printStackTrace();
            }
        }
    }

}
