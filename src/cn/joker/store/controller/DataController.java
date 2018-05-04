package cn.joker.store.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.joker.entity.Food;
import cn.joker.entity.FoodInfo;
import cn.joker.entity.FoodPictures;
import cn.joker.entity.StoreInfo;
import cn.joker.entity.StorePictures;
import cn.joker.store.service.DataService;

@Controller
@RequestMapping(value="/store")
public class DataController {
	
	@Autowired
	private DataService dataService;
	public void setDataService(DataService dataService) {
		this.dataService = dataService;
	}
	@RequestMapping("/homepage.do")
	public String getHp(ServletRequest request,String storeId) {
		request.setAttribute("storeId", storeId);
		return "store_homepage";
	}
	@RequestMapping("/info.do")
	@ResponseBody
	public StoreInfo getStoreInfo(ServletRequest request,String s_id) {
		
		StoreInfo storeInfo = dataService.getStoreInfoByStoreId(s_id);	
		return storeInfo;
	}
	@RequestMapping("/pictures.do")
	@ResponseBody
	public List<StorePictures> getStorePictures(ServletRequest request,String s_id) {
			
		List<StorePictures> storePicturesList = dataService.getStorePicturesByStoreId(s_id);
		return storePicturesList;
	}
	
	
	
	@RequestMapping("/showAllFood.do")
	@ResponseBody
	public List<Food> getFoodAll(ServletRequest request,String s_id){
		
		List<Food> foodList = dataService.getFoodAllByStoreId(s_id);
		request.setAttribute("foodList", foodList);
		return foodList;
	}
	@RequestMapping("/showFood.do")
	public String getFoodInfo(ServletRequest request,String f_id) {
		
		FoodInfo foodInfo = dataService.getFoodInfoByFoodId(f_id);
		List<FoodPictures> foodPicturesList = dataService.getFoodPicturesByFoodId(f_id);
		request.setAttribute("foodInfo", foodInfo);
		request.setAttribute("foodPicturesList", foodPicturesList);
		return "food";
	}
	
}
