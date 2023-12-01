package servlets;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat")
public class ChatWebSocket {
    private static Set<Session> sessions = Collections.newSetFromMap(new ConcurrentHashMap<Session, Boolean>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        for (Session s : sessions) {
            if (s.isOpen() && !s.equals(session)) {
                s.getAsyncRemote().sendText(message);
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
    	System.out.println("error with socket");
    }
}
