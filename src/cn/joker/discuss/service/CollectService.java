package cn.joker.discuss.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.joker.discuss.dao.CollectDao;
import cn.joker.entity.FoodCollect;
import cn.joker.entity.StoreCollect;
import cn.joker.entity.User;

@Service
public class CollectService {

	@Autowired
	private CollectDao collectDaoImpl;

	public void setCollectDaoImpl(CollectDao collectDaoImpl) {
		this.collectDaoImpl = collectDaoImpl;
	}
	@Transactional(readOnly=false)
	public void addStoreCollect(String s_id, User user) {
		
		collectDaoImpl.addStoreCollect(s_id,user);
	}
	@Transactional(readOnly=false)
	public void addFoodCollect(String f_id, User user) {
		
		collectDaoImpl.addFoodCollect(f_id,user);
	}
	@Transactional(readOnly=true)
	public List<StoreCollect> getStoreCollectList(String id) {
		
		return collectDaoImpl.getStoreCollectList(id);
	}
	@Transactional(readOnly=true)
	public List<FoodCollect> getFoodCollectList(String id) {
		
		return collectDaoImpl.getFoodCollectList(id);
	}
	@Transactional(readOnly=true)
	public boolean isCollectStore(String s_id, String id) {
		
		return collectDaoImpl.isCollectStore(s_id,id);
	}
	@Transactional(readOnly=true)
	public boolean isCollectFood(String f_id, String id) {
		
		return collectDaoImpl.isCollectFood(f_id,id);
	}
	
}
