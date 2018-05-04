package cn.joker.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.joker.entity.Food;
import cn.joker.entity.FoodPictures;
import cn.joker.entity.Store;
import cn.joker.store.dao.FoodDao;

@Service("foodService")
public class FoodService {

	@Autowired
	private FoodDao foodDaoImpl;
	
	@Transactional(readOnly=false)
	public void upload(Food food, Store s, String url) {

		FoodPictures fp = new FoodPictures();
		fp.setUrl(url);
		
		foodDaoImpl.upload(food, s, fp);
	}

	@Transactional(readOnly=true)
	public Food getFoodInfo(String f_id) {
		
		return foodDaoImpl.getFoodInfo(f_id);
	}
	@Transactional(readOnly=true)
	public List<FoodPictures> getPicturesList(String f_id) {
		
		return foodDaoImpl.getPicturesList(f_id);
	}

	@Transactional(readOnly=false)
	public void updateFood(Food food, String url) {
		
		foodDaoImpl.updateFood(food,url);
	}
	@Transactional(readOnly=false)
	public void delFood(String f_id) {
		
		foodDaoImpl.del(f_id);
	}
	@Transactional(readOnly=false)
	public void delFoodPicture(String fp_id) {
		
		foodDaoImpl.delFoodPictures(fp_id);
	}

}
