package cn.joker.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.joker.entity.GetFoodScore;
import cn.joker.entity.GetStoreScore;
import cn.joker.user.dao.UserEvaluateDao;

@Service("userEvaluateService")
public class UserEvaluateService {

	@Autowired
	private UserEvaluateDao userEvaluateDao;
	public void setUserEvaluateDao(UserEvaluateDao userEvaluateDao) {
		this.userEvaluateDao = userEvaluateDao;
	}


	@Transactional(readOnly=true)
	public List<GetFoodScore> getEvaluatedFoodList(String u_id) {
		
		return userEvaluateDao.findEvaluatedFood(u_id);
	}

	@Transactional(readOnly=true)
	public List<GetStoreScore> getEvaluatedStoreList(String u_id) {
		
		return userEvaluateDao.findEvaluatedStore(u_id);
	}

}
