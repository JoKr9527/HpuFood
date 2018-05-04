package cn.joker.store.dao;

import java.util.List;

import cn.joker.entity.Food;
import cn.joker.entity.FoodInfo;
import cn.joker.entity.FoodPictures;
import cn.joker.entity.StoreInfo;
import cn.joker.entity.StorePictures;

public interface DataDao {

	StoreInfo getStoreInfoByStoreId(int s_id);

	List<StorePictures> getStorePicturesByStoreId(int s_id);

	List<Food> getFoodAllByStoreId(int s_id);

	FoodInfo getFoodInfoByFoodId(String f_id);

	List<FoodPictures> getFoodPicturesByFoodId(String f_id);

}
