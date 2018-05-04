package cn.joker.discuss.dao;

import java.util.List;

import cn.joker.entity.FoodCollect;
import cn.joker.entity.StoreCollect;
import cn.joker.entity.User;

public interface CollectDao {

	void addStoreCollect(String s_id, User user);

	void addFoodCollect(String f_id, User user);

	List<StoreCollect> getStoreCollectList(String id);

	List<FoodCollect> getFoodCollectList(String id);

	boolean isCollectStore(String s_id, String id);

	boolean isCollectFood(String f_id, String id);

}
