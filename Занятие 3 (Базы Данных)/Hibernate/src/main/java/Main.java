import accounts.AccountService;
import accounts.User;
import accounts.UserAlreadyRegistered;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AuthInfoEndServlet;
import servlets.SignInServlet;
import servlets.SignUpServlet;

public class Main {
    private static AccountService accountService = new AccountService();

    public static void main(String[] args) throws Exception {
        Server server = createServer(8080);
        server.start();
        System.out.println("Server started");
        server.join();
    }

    private static Server createServer(int port) {
        //registerUsersByLogin("admin", "test");

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        contextHandler.addServlet(new ServletHolder(new AuthInfoEndServlet(accountService)), "/api/auth/");
        contextHandler.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        contextHandler.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("src/main/resources/templates");

        HandlerList handlerList = new HandlerList();
        handlerList.setHandlers(new Handler[]{resourceHandler, contextHandler});

        Server server = new Server(port);
        server.setHandler(handlerList);
        return server;
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
