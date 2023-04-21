package com.hauschildt.websockets.chat;

import com.hauschildt.websockets.MyDecoder;
import com.hauschildt.websockets.MyEncoder;
import com.hauschildt.websockets.MyJson;


import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(
        value = "/chat/chatEndpoint",
        encoders = {MyEncoder.class},
        decoders = {MyDecoder.class}
)
public class ChatEndpoint {
    private static final Set<Session> subscribers = Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void onOpen(Session session) {
        subscribers.add(session);
    }
    @OnClose
    public void onClose(Session session) {
        subscribers.remove(session);
    }
    @OnMessage
    public void onMessage(MyJson json, Session session) throws EncodeException, IOException {
        for(Session subscriber: subscribers) {
            if(!subscriber.equals(session)) {
                subscriber.getBasicRemote().sendObject(json);
            }
        }
    }
    @OnError
    public void onError(Throwable throwable) {
        System.out.println("Chat Endpoint Error: " + throwable.getMessage());
    }
}
