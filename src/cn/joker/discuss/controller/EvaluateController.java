package cn.joker.discuss.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.joker.discuss.service.EvaluateService;
import cn.joker.entity.GetFoodScore;
import cn.joker.entity.GetStoreScore;
import cn.joker.entity.User;

@Controller
@RequestMapping("/discuss")
public class EvaluateController {
	
	@Autowired
	private EvaluateService evaluateService;
	public void setEvaluateService(EvaluateService evaluateService) {
		this.evaluateService = evaluateService;
	}


	@RequestMapping("/evaluateStore.do")
	public String evaluateStore(HttpSession session,GetStoreScore evaluatedStore,String storeId) {
		
		User user = (User) session.getAttribute("user");
		if(user != null) {
			evaluateService.evaluateStore(evaluatedStore,storeId,user.getId());
			return "redirect:/store/homepage.do?storeId="+storeId;
		}
		return "redirect:/user/goLogin.do";
	}
	
	@RequestMapping("/evaluateFood.do")
	public String evaluateFood(HttpSession session,GetFoodScore evaluatedFood,String f_id) {
		User user = (User) session.getAttribute("user");
		if(user != null) {
			evaluateService.evaluateFood(evaluatedFood,f_id,user.getId());
			return "redirect:/store/showFood.do?f_id="+f_id;
		}
		return "redirect:/user/goLogin.do";
	}
	
	@RequestMapping("/storeEvaluates.do")
	public @ResponseBody List<GetStoreScore> storeEvaluates(@RequestParam String store_id,@RequestParam String pageNum) {
		
		
		return evaluateService.storeEvaluates(store_id,pageNum);
	}
	@RequestMapping("/foodEvaluates.do")
	public @ResponseBody List<GetFoodScore> foodEvaluates(@RequestParam String f_id,@RequestParam String pageNum) {
		
		
		return evaluateService.foodEvaluates(f_id,pageNum);
	}
	
}
