package cn.joker.discuss.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.joker.discuss.service.ChatService;
import cn.joker.entity.SUChat;
import cn.joker.entity.StoreAdmin;
import cn.joker.entity.USChat;
import cn.joker.entity.UUChat;
import cn.joker.entity.User;

@Controller
@RequestMapping("/discuss")
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}
	
	@RequestMapping(value="/chat.do",produces = "application/json; charset=utf-8")
	public @ResponseBody String chat(HttpSession session,String content,String to_id,String relation_id) {
		StoreAdmin admin = (StoreAdmin) session.getAttribute("admin");
		User user = (User)session.getAttribute("user");
		if(admin != null) {
			chatService.fromStoreChat(content,admin.getStore().getId(),to_id,relation_id);
			return "{\"message\":\"成功发送信息\"}";
		}
			
		if(user != null) {
			chatService.fromUserChat(content,user.getId(),to_id,relation_id);
			return "{\"message\":\"成功发送信息\"}";
		}
		return "{\"message\":\"发送信息失败,请先登录\"}";
	}
	
	@RequestMapping("/showChatFromUU.do")
	public @ResponseBody List<UUChat> showChatFromUU(@RequestParam String relation_id,@RequestParam(required=false) String pageNum) {
		
		return chatService.showChatFromUU(relation_id,pageNum);
	}
	@RequestMapping("/showChatFromSU.do")
	public @ResponseBody List<SUChat> showChatFromSU(@RequestParam String relation_id,@RequestParam(required=false) String pageNum) {
		
		return chatService.showChatFromSU(relation_id,pageNum);
	}
	@RequestMapping("/showChatFromUS.do")
	public @ResponseBody List<USChat> showChatFromUS(@RequestParam String relation_id,@RequestParam(required=false) String pageNum) {
		
		return chatService.showChatFromUS(relation_id,pageNum);
	}
}
