package cn.joker.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.joker.entity.Food;
import cn.joker.entity.FoodPictures;
import cn.joker.entity.Store;
import cn.joker.entity.StorePictures;
import cn.joker.store.dao.DataDisplayDao;

@Service("dataDisplayService")
public class DataDisplayService {
	
	@Autowired
	private DataDisplayDao dataDisplayDaoImpl;
	public void setDataDisplayDaoImpl(DataDisplayDao dataDisplayDaoImpl) {
		this.dataDisplayDaoImpl = dataDisplayDaoImpl;
	}
	@Transactional(readOnly=true)
	public List<FoodPictures> foodDisplay(String pageNum,String sortRule,String pageCount) {
		
		return dataDisplayDaoImpl.getFoodDisplayInfo(pageNum,sortRule,pageCount);
	}
	@Transactional(readOnly=true)
	public List<StorePictures> storeDisplay(String pageNum,String sortRule,String pageCount) {
		
		return dataDisplayDaoImpl.getStoreDisplayInfo(pageNum,sortRule,pageCount);
	}
	
	@Transactional(readOnly=true)
	public List<Food> searchFood(String name) {
		
		return dataDisplayDaoImpl.searchFood(name);
	}
	@Transactional(readOnly=true)
	public List<Store> searchStore(String name) {
		
		return dataDisplayDaoImpl.searchStore(name);
	}
	
}
