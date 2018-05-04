package cn.joker.messagepush.utils;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.Session;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.joker.entity.Order;
import cn.joker.messagepush.handler.StoreAdminWebSocketHandler;
import cn.joker.messagepush.handler.UserWebSocketHandler;

public class OrderUtils {
	
	public volatile static Map<String,List<Order>> orderMap = Collections.synchronizedMap(new HashMap<String,List<Order>>());
	
	public static void main(String[] args) throws JsonProcessingException, IOException {
		
		// json 转换
		ObjectMapper mapper = new ObjectMapper();
		
		Set<String> set = orderMap.keySet();
		Iterator<String> iterator = set.iterator();
		String saId = null;
		while(iterator.hasNext()) {
			saId = iterator.next();
			WebSocketSession session = getStoreSession(saId);
			if(session != null) {			
				List<Order> list = orderMap.get(saId);
				session.sendMessage(new TextMessage(mapper.writeValueAsString(list)));
				orderMap.remove(saId);
			}else
				continue;
			
		}
	}
	
	// 判断sessionMap 中是否有对应session
	private static WebSocketSession getStoreSession(String saId) {
		return StoreAdminWebSocketHandler.sessionMap.get(saId);	
	}
	
	// 遍历集合看用户是否在线（order.user.id），把消息推送给他。通过店家处理的f_id,找到 orderMap中的order
	public static boolean sendToUser(Order order) {
		
		Map<String,WebSocketSession> sessionMap = UserWebSocketHandler.sessionMap;
		WebSocketSession session = sessionMap.get(order.getUser().getId());
		
		ObjectMapper mapper = new ObjectMapper();
		if(session != null) {
			try {
				session.sendMessage(new TextMessage(mapper.writeValueAsString(order)));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	
}
