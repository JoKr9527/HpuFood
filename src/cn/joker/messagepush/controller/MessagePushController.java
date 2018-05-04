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
			// 查询到f_id 所关联到的店铺管理员id
			String sa_id = messageService.findSaByFo(f_id);
			Food food = messageService.findFood(f_id);
			if(f_id == null)
				return "非法操作";
			// 新建Order 对象，放入OrderUtils的静态map集合中，店铺管理id作为 key
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
			// 存入数据库
			
			return "下单成功，请在订单列表查看商家是否接单";
		}		
		return "请先登录";
	}
	// 店家接受订单
	@RequestMapping("/acceptOrder")
	@ResponseBody
	public String acceptOrder(HttpSession session,String f_id,String u_id) {
		
		StoreAdmin admin = (StoreAdmin) session.getAttribute("admin");
		if(admin!=null) {		
			Order order = messageService.updateIsAc(f_id,u_id);
			// 遍历集合看用户是否在线，把消息推送给他。
			OrderUtils.sendToUser(order);
		}		
		return "请先登录";
	}
	
	// 店家已处理订单
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
	// 用户已被处理订单
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
