package chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;

@WebSocket
public class ChatWebSocket {
    private Session session;

    @OnWebSocketConnect
    private void onOpen(Session session) {
        this.session = session;
    }

    @OnWebSocketMessage
    private void onMessage(String message) {
        echo(message);
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {}

    private void echo(String msg) {
        try {
            session.getRemote().sendString(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
