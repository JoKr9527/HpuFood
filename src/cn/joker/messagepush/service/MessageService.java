package cn.joker.messagepush.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.joker.entity.Food;
import cn.joker.entity.Order;
import cn.joker.messagepush.dao.MessagePushDao;

@Service
public class MessageService {

	@Autowired
	private MessagePushDao messagePushDaoImpl;
	
	@Transactional(readOnly=true)
	public String findSaByFo(String f_id) {
			
		return messagePushDaoImpl.findSaByFo(f_id);
	}
	@Transactional(readOnly=true)
	public Food findFood(String f_id) {
		
		return messagePushDaoImpl.findFood(f_id);
	}
	@Transactional(readOnly=false)
	public Order saveNoAccept(Order order) {
		
		return messagePushDaoImpl.saveNoAccept(order);
	}
	@Transactional(readOnly=false)
	public Order updateIsAc(String fId,String uId) {
		
		return messagePushDaoImpl.updateIsAc(fId,uId);
	}
	@Transactional(readOnly=true)
	public List<Order> getOrdersByS(String id) {
		
		return messagePushDaoImpl.getOrdersByS(id);
	}
	@Transactional(readOnly=true)
	public List<Order> getOrdersByU(String id) {
		
		return messagePushDaoImpl.getOrdersByU(id);
	}
	
	
}
