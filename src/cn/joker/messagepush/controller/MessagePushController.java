package cn.joker.messagepush.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.joker.entity.Food;
import cn.joker.entity.Order;
import cn.joker.entity.StoreAdmin;
import cn.joker.entity.User;
import cn.joker.messagepush.handler.UserWebSocketHandler;
import cn.joker.messagepush.service.MessageService;
import cn.joker.messagepush.utils.OrderUtils;

@Controller
@RequestMapping("/messagePush")
public class MessagePushController {

	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/placeOrder")
	@ResponseBody
	public String placeOrder(HttpSession session,String u_id,String f_id) {
		User user = (User)session.getAttribute("user");
		if(user != null) {
			// ��ѯ��f_id ���������ĵ��̹���Աid
			String sa_id = messageService.findSaByFo(f_id);
			Food food = messageService.findFood(f_id);
			if(f_id == null)
				return "�Ƿ�����";
			// �½�Order ���󣬷���OrderUtils�ľ�̬map�����У����̹���id��Ϊ key
			Order order = new Order();
			order.setFood(food);
			order.setUser(user);
			order.setTime(new Date());
			order = messageService.saveNoAccept(order);
			List<Order> list = OrderUtils.orderMap.get(sa_id);
			if(list != null)
				list.add(order);
			else {
				list = new ArrayList<>();
				list.add(order);
				OrderUtils.orderMap.put(sa_id, list);
			}			
			// �������ݿ�
			
			return "�µ��ɹ������ڶ����б�鿴�̼��Ƿ�ӵ�";
		}		
		return "���ȵ�¼";
	}
	// ��ҽ��ܶ���
	@RequestMapping("/acceptOrder")
	@ResponseBody
	public String acceptOrder(HttpSession session,String f_id,String u_id) {
		
		StoreAdmin admin = (StoreAdmin) session.getAttribute("admin");
		if(admin!=null) {		
			Order order = messageService.updateIsAc(f_id,u_id);
			// �������Ͽ��û��Ƿ����ߣ�����Ϣ���͸�����
			OrderUtils.sendToUser(order);
		}		
		return "���ȵ�¼";
	}
	
	// ����Ѵ�����
	@RequestMapping("/storeOrders")
	@ResponseBody
	public List<Order> storeOrders(HttpSession session){
		StoreAdmin admin = (StoreAdmin) session.getAttribute("StoreAdmin");
		if(admin != null) {
			List<Order> list = messageService.getOrdersByS(admin.getId());
			return list;				
		}
		return null;
	}
	// �û��ѱ�������
	@RequestMapping("/userOrders")
	@ResponseBody
	public List<Order> userOrders(HttpSession session){
		User user = (User) session.getAttribute("user");
		if(user != null) {
			List<Order> list = messageService.getOrdersByU(user.getId());
			return list;				
		}
		return null;
	}
}
