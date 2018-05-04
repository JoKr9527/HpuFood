package cn.joker.discuss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.joker.discuss.dao.EvaluateDao;
import cn.joker.entity.GetFoodScore;
import cn.joker.entity.GetStoreScore;

@Service("evaluateService")
public class EvaluateService {
	
	@Autowired
	private EvaluateDao evaluateDaoImpl;
	public void setEvaluateDaoImpl(EvaluateDao evaluateDaoImpl) {
		this.evaluateDaoImpl = evaluateDaoImpl;
	}
	
	@Transactional(readOnly=false)
	public void evaluateStore(GetStoreScore evaluatedStore, String store_id,String u_id) {
		
		evaluateDaoImpl.addStoreEvaluate(evaluatedStore,store_id,u_id);
	}
	@Transactional(readOnly=false)
	public void evaluateFood(GetFoodScore evaluatedFood, String f_id, String u_id) {
		
		evaluateDaoImpl.addFoodEvaluate(evaluatedFood,f_id,u_id);
	}
	@Transactional(readOnly=false)
	public List<GetStoreScore> storeEvaluates(String store_id, String pageNum) {
		
		return evaluateDaoImpl.getStoreEvaluate(store_id,pageNum);
	}
	@Transactional(readOnly=false)
	public List<GetFoodScore> foodEvaluates(String f_id, String pageNum) {
		
		return evaluateDaoImpl.getFoodEvaluate(f_id,pageNum);
	}
	
}
