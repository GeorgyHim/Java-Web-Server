package chat;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WebSocketChatServlet extends WebSocketServlet {
    private final static int TIMEOUT = 60 * 60 * 24;

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(TIMEOUT);
        factory.setCreator((req, resp) -> new ChatWebSocket());
    }
}
