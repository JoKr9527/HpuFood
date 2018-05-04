package cn.joker.store.dao;

import java.util.List;

import cn.joker.entity.Food;
import cn.joker.entity.FoodPictures;
import cn.joker.entity.Store;

public interface FoodDao {
	void upload(Food food,Store store,FoodPictures picture) ;

	Food getFoodInfo(String f_id);

	List<FoodPictures> getPicturesList(String f_id);

	void updateFood(Food food, String url);

	void del(String f_id);

	void delFoodPictures(String fp_id);
}
