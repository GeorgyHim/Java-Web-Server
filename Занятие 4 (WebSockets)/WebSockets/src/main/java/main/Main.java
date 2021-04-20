package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        // contextHandler.addServlet(new ServletHolder())

        server.setHandler(contextHandler);
        server.start();
        System.out.println("Server started");
        server.join();
    }
}
