package cn.joker.discuss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.joker.discuss.dao.ChatDao;
import cn.joker.entity.SUChat;
import cn.joker.entity.USChat;
import cn.joker.entity.UUChat;

@Service("chatService")
public class ChatService {
	
	@Autowired
	private ChatDao chatDaoImpl;
	public void setChatDaoImpl(ChatDao chatDaoImpl) {
		this.chatDaoImpl = chatDaoImpl;
	}
	
	@Transactional(readOnly=false)
	public void fromStoreChat(String content, int store_id, String to_id, String relation_id) {
		chatDaoImpl.addStoreChat(content,store_id,to_id,relation_id);
	}
	@Transactional(readOnly=false)
	public void fromUserChat(String content, String user_id, String to_id, String relation_id) {
		chatDaoImpl.addUserChat(content,user_id,to_id,relation_id);
	}
	@Transactional(readOnly=false)
	public List<UUChat> showChatFromUU(String relation_id, String pageNum) {
		
		return chatDaoImpl.getChatListFromUU(relation_id,pageNum);
	}
	@Transactional(readOnly=false)
	public List<SUChat> showChatFromSU(String relation_id, String pageNum) {
		
		return chatDaoImpl.getChatListFromSU(relation_id,pageNum);
	}
	@Transactional(readOnly=false)
	public List<USChat> showChatFromUS(String relation_id, String pageNum) {
		
		return chatDaoImpl.getChatListFromUS(relation_id,pageNum);
	}
	
}
