package cn.joker.discuss.dao;

import java.util.List;

import cn.joker.entity.GetFoodScore;
import cn.joker.entity.GetStoreScore;

public interface EvaluateDao {

	
	void addStoreEvaluate(GetStoreScore evaluatedStore, String store_id, String u_id);

	void addFoodEvaluate(GetFoodScore evaluatedFood, String f_id, String u_id);

	List<GetStoreScore> getStoreEvaluate(String store_id, String pageNum);

	List<GetFoodScore> getFoodEvaluate(String f_id, String pageNum);

}
