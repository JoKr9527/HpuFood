package cn.joker.messagepush.dao;

import java.util.List;

import cn.joker.entity.Food;
import cn.joker.entity.Order;

public interface MessagePushDao {

	String findSaByFo(String f_id);

	Food findFood(String f_id);

	Order saveNoAccept(Order order);

	Order updateIsAc(String fId,String uId);

	List<Order> getOrdersByS(String id);

	List<Order> getOrdersByU(String id);

}
