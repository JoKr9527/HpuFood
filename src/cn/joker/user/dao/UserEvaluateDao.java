package cn.joker.user.dao;

import java.util.List;

import cn.joker.entity.GetFoodScore;
import cn.joker.entity.GetStoreScore;

public interface UserEvaluateDao {

	List<GetFoodScore> findEvaluatedFood(String u_id);

	List<GetStoreScore> findEvaluatedStore(String u_id);

}
