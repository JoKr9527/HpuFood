package cn.joker.store.dao;

import java.util.List;

import cn.joker.entity.Food;
import cn.joker.entity.FoodPictures;
import cn.joker.entity.Store;
import cn.joker.entity.StorePictures;

public interface DataDisplayDao {

	List<FoodPictures> getFoodDisplayInfo(String pageNum,String store,String pageCount);

	List<Food> searchFood(String name);

	List<Store> searchStore(String name);

	List<StorePictures> getStoreDisplayInfo(String pageNum, String sortRule,String pageCount);

}
