package cn.joker.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.joker.entity.Food;
import cn.joker.entity.FoodInfo;
import cn.joker.entity.FoodPictures;
import cn.joker.entity.StoreInfo;
import cn.joker.entity.StorePictures;
import cn.joker.store.dao.DataDao;

@Service("dataService")
public class DataService {

	@Autowired
	private DataDao dataDaoImpl;
	public void setDataDaoImpl(DataDao dataDaoImpl) {
		this.dataDaoImpl = dataDaoImpl;
	}
	
	@Transactional(readOnly=true)
	public StoreInfo getStoreInfoByStoreId(String s_id) {
		
		return dataDaoImpl.getStoreInfoByStoreId(Integer.valueOf(s_id));
	}
	@Transactional(readOnly=true)
	public List<StorePictures> getStorePicturesByStoreId(String s_id) {
		
		return dataDaoImpl.getStorePicturesByStoreId(Integer.valueOf(s_id));
	}
	@Transactional(readOnly=true)
	public List<Food> getFoodAllByStoreId(String s_id) {
		
		return dataDaoImpl.getFoodAllByStoreId(Integer.valueOf(s_id));
	}
	@Transactional(readOnly=true)
	public FoodInfo getFoodInfoByFoodId(String f_id) {
		
		return dataDaoImpl.getFoodInfoByFoodId(f_id);
	}
	@Transactional(readOnly=true)
	public List<FoodPictures> getFoodPicturesByFoodId(String f_id) {
		
		return dataDaoImpl.getFoodPicturesByFoodId(f_id);
	}

}
