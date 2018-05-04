package cn.joker.store.controller;

import java.util.List;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.joker.entity.FoodPictures;
import cn.joker.entity.StorePictures;
import cn.joker.store.service.DataDisplayService;

@Controller
@RequestMapping("/dataDisplay")
public class DataDisplayController {
	
	@Autowired
	private DataDisplayService dataDisplayService;
	public void setDataDisplayService(DataDisplayService dataDisplayService) {
		this.dataDisplayService = dataDisplayService;
	}
	@RequestMapping("/goFoods.do")
	public String goFoods(ServletRequest request,String sortRule) {
		request.setAttribute("sortRule", sortRule);
		request.setAttribute("isDeAs","Desc".equals(sortRule.split("/")[1])?"½µÐò":"ÉýÐò");
		return "foods";
	}
	
	@RequestMapping("/goStores.do")
	public String goStores(ServletRequest request,String sortRule) {
		request.setAttribute("sortRule", sortRule);
		request.setAttribute("isDeAs", "Desc".equals(sortRule.split("/")[1])?"½µÐò":"ÉýÐò");
		return "stores";
	}
	
	@RequestMapping("/foodDisplay.do")
	@ResponseBody
	public List<FoodPictures> foodDisplay(@RequestParam String pageNum,String sortRule,String pageCount){
		//String sortRule = "score/Desc";
		return dataDisplayService.foodDisplay(pageNum,sortRule,pageCount);
	}
	@RequestMapping("/storeDisplay.do")
	@ResponseBody
	public List<StorePictures> storeDisplay(@RequestParam String pageNum,String sortRule,String pageCount){
		return dataDisplayService.storeDisplay(pageNum,sortRule,pageCount);
	}
	
	@RequestMapping("/searchDisplay.do")
	public String searchDisplay(ServletRequest request,@RequestParam String name){
		
		 request.setAttribute("foodList", dataDisplayService.searchFood(name));
		 request.setAttribute("storeList", dataDisplayService.searchStore(name));
		 return "searchResult";
	}	
}
