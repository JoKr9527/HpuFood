package cn.joker.messagepush.handler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import cn.joker.entity.StoreAdmin;
import cn.joker.entity.User;

public class StoreAdminWebSocketHandler implements WebSocketHandler {

	public volatile static Map<String,WebSocketSession> sessionMap = Collections.synchronizedMap(new HashMap<String,WebSocketSession>());
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		StoreAdmin admin = (StoreAdmin) session.getAttributes().get("admin");
		if(session.getAttributes().get("admin")==null)
			session.sendMessage(new TextMessage("ÇëÏÈµÇÂ¼"));
		else
			sessionMap.put(admin.getId(), session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		
		String body = message.getPayload().toString();
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		
		sessionMap.remove(session.getId());
	}

	@Override
	public boolean supportsPartialMessages() {
		
		return false;
	}


}
