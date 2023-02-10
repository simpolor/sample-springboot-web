package io.simpolor.websocket.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@ServerEndpoint(value="/chat")
public class ChatServerEndpoint {

    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        if(!sessions.contains(session)) {
            sessions.add(session);
            log.info("[ChatEndpoint.onOpen] Add session: {}", session);
        }else {
            log.info("[ChatEndpoint.onOpen] Already session: {}", session);
        }
    }

    @OnMessage
    public void onMessage(String message) throws Exception{
        for(Session session : sessions) {
            log.info("[ChatEndpoint.onClose] Send message: {}", message);
            session.getBasicRemote().sendText(message);
        }
    }

    @OnClose
    public void onClose(Session session) {
        log.info("[ChatEndpoint.onClose] Close session: {}", session);
        sessions.remove(session);
    }
}
