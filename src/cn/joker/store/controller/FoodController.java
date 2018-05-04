package cn.joker.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import cn.joker.entity.Food;
import cn.joker.entity.FoodPictures;
import cn.joker.entity.StoreAdmin;
import cn.joker.store.service.FoodService;

@Controller
@RequestMapping("/store")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@RequestMapping("/goFood.do")
	public String goStoreFood() {
		return "store_food";
	}
	@RequestMapping("/goUpdateFood.do")
	public String goUpdateFood(ServletRequest request,String f_id) {
		
		Food food = foodService.getFoodInfo(f_id);
		List<FoodPictures> picturesList = foodService.getPicturesList(f_id);
		request.setAttribute("food", food);
		request.setAttribute("picturesList", picturesList);
		return "store_food_update";
	}
	
	@RequestMapping("/food.do")
	public String storeFood(HttpSession session,ServletRequest request,Food food,@RequestPart(required=false) MultipartFile picture,UriComponentsBuilder uriBuilder) {
		
		StoreAdmin admin = (StoreAdmin) session.getAttribute("admin");
		if(admin != null) {
			if (!picture.isEmpty()) {
				String fileName = System.currentTimeMillis() + ".jpg";
				String url = uriBuilder.path("/images/" + fileName).toUriString();

				String location = session.getServletContext().getRealPath("/images");

				try {
					picture.transferTo(new File(location + "\\" + fileName));
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				foodService.upload(food, admin.getStore(), url);

			}
			return "redirect:homepage.do?storeId="+admin.getStore().getId();
		}
		return "redirect:goLogin.do";
		
	}

	@SuppressWarnings("unused")
	@RequestMapping("/updateFood.do")
	public String updateFood(HttpSession session,Food food,@RequestPart(required=false) MultipartFile picture,UriComponentsBuilder uriBuilder) {
		
		String url = null;
		boolean hasFile = !picture.isEmpty();
		StoreAdmin admin = (StoreAdmin)session.getAttribute("admin");
		if(admin != null) {
			food.setStore(admin.getStore());
			if(hasFile) {
				String fileName = System.currentTimeMillis()+".jpg";
				url = uriBuilder.path("/images/"+fileName).toUriString();
				
				String location = session.getServletContext().getRealPath("/images");
				
				try {
					picture.transferTo(new File(location+"\\"+fileName));
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			foodService.updateFood(food,hasFile?url:null);
			return "redirect:homepage.do?storeId="+admin.getStore().getId();
		}
		return "redirect:goLogin.do";
	}
	@RequestMapping("/delFood.do")
	public String delFood(HttpSession session,String f_id) {
		StoreAdmin admin = (StoreAdmin) session.getAttribute("admin");
		if(admin != null) {
			foodService.delFood(f_id);
			return "redirect:homepage.do?storeId="+admin.getStore().getId();
		}
		return "redirect:goLogin.do";
	}
	@RequestMapping("/delFoodPictures.do")
	public String delFoodPictures(HttpSession session,String fp_id) {
		StoreAdmin admin = (StoreAdmin) session.getAttribute("admin");
		if(admin != null) {
			foodService.delFoodPicture(fp_id);
			return "redirect:homepage.do?storeId="+admin.getStore().getId();
		}
		return "redirect:goLogin.do";
	}
}
