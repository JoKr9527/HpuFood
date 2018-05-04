package cn.joker.discuss.dao;

import java.util.List;

import cn.joker.entity.SUChat;
import cn.joker.entity.USChat;
import cn.joker.entity.UUChat;

public interface ChatDao {

	void addUserChat(String content, String user_id, String to_id, String relation_id);

	void addStoreChat(String content, int store_id, String to_id, String relation_id);

	List<UUChat> getChatListFromUU(String relation_id, String pageNum);

	List<SUChat> getChatListFromSU(String relation_id, String pageNum);

	List<USChat> getChatListFromUS(String relation_id, String pageNum);

}
