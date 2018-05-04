package cn.joker.discuss.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.joker.discuss.service.CollectService;
import cn.joker.entity.FoodCollect;
import cn.joker.entity.StoreCollect;
import cn.joker.entity.User;

@Controller
@ResponseBody
@RequestMapping("/discuss")
public class CollectionController {
	
	@Autowired
	private CollectService collectService;
	public void setCollectService(CollectService collectService) {
		this.collectService = collectService;
	}
	@RequestMapping(value="/storeCollect.do",produces = "application/json; charset=utf-8")
	public @ResponseBody String addStoreCollect(HttpSession session,String s_id) {
		
		User user = (User) session.getAttribute("user");
		if(user != null) {
			collectService.addStoreCollect(s_id,user);
			return "{\"message\":\"收藏成功,请在个人中心查看\"}";
		}else {
			return "{\"message\":\"收藏失败，请先登录\"}";
		}
			
	}
	@RequestMapping(value="/foodCollect.do",produces = "application/json; charset=utf-8")
	public @ResponseBody String addFoodCollect(HttpSession session,String f_id) {
		User user = (User) session.getAttribute("user");
		if(user != null) {
			collectService.addFoodCollect(f_id,user);
			return "{\"message\":\"收藏成功,请在个人中心查看\"}";
		}else {
			return "{\"message\":\"收藏失败，请先登录\"}";
		}
	}
	@RequestMapping(value="/storeCollectList.do",produces = "application/json; charset=utf-8")
	public @ResponseBody List<StoreCollect> storeCollectList(HttpSession session){
		User user = (User) session.getAttribute("user");
		if(user != null) {
			List<StoreCollect> collect = collectService.getStoreCollectList(user.getId());
			return collect;
		}else {
			return null;
		}
	}
	@RequestMapping(value="/foodCollectList.do",produces = "application/json; charset=utf-8")
	public @ResponseBody List<FoodCollect> foodCollectList(HttpSession session){
		User user = (User) session.getAttribute("user");
		if(user != null) {
			List<FoodCollect> collect = collectService.getFoodCollectList(user.getId());
			return collect;
		}else {
			return null;
		}
	}
	
	// 判断是否收藏过
	@RequestMapping(value="/isCollectStore.do",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String isCollectStore(HttpSession session,String s_id) {
		User user = (User) session.getAttribute("user");
		if(user != null ) {
			boolean isCollect = collectService.isCollectStore(s_id,user.getId());
			
			return isCollect?"{\"message\":\"已收藏\"}":"{\"message\":\"收藏\"}";
		}else {
			return "{\"message\":\"收藏\"}";
		}
	}
	@RequestMapping(value="/isCollectFood.do",produces = "application/json; charset=utf-8")
	@ResponseBody
	public String isCollectFood(HttpSession session,String f_id) {
		User user = (User) session.getAttribute("user");
		if(user != null ) {
			boolean isCollect = collectService.isCollectFood(f_id,user.getId());
			
			return isCollect?"{\"message\":\"已收藏\"}":"{\"message\":\"收藏\"}";
		}else {
			return "{\"message\":\"收藏\"}";
		}
	}
}
