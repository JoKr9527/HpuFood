package cn.joker.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.joker.entity.GetFoodScore;
import cn.joker.entity.GetStoreScore;
import cn.joker.user.service.UserEvaluateService;

@Controller
@RequestMapping("/user")
public class UserEvaluateController {
	
	@Autowired
	private UserEvaluateService userEvaluateService;	
	public void setUserEvaluateService(UserEvaluateService userEvaluateService) {
		this.userEvaluateService = userEvaluateService;
	}

	@RequestMapping("/goEvaluatedFood.do")
	@ResponseBody
	public List<GetFoodScore> goEvaluatedFood(String u_id) {
		
		if(u_id != null) {
			List<GetFoodScore> evaluatedFoodList = userEvaluateService.getEvaluatedFoodList(u_id);
			return evaluatedFoodList;
		}
		return null;
	}
	@RequestMapping("/goEvaluatedStore.do")
	@ResponseBody
	public List<GetStoreScore> goEvaluatedStore(String u_id) {
		if(u_id != null) {
			List<GetStoreScore> evaluatedStoreList = userEvaluateService.getEvaluatedStoreList(u_id);
			return evaluatedStoreList;
		}
		return null;	
	}
	
}
