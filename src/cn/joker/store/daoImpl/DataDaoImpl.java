package cn.joker.store.daoImpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.joker.entity.Food;
import cn.joker.entity.FoodInfo;
import cn.joker.entity.FoodPictures;
import cn.joker.entity.StoreInfo;
import cn.joker.entity.StorePictures;
import cn.joker.store.dao.DataDao;

@Repository("dataDaoImpl")
public class DataDaoImpl implements DataDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public StoreInfo getStoreInfoByStoreId(int s_id) {
		
		return (StoreInfo) hibernateTemplate.findByCriteria(DetachedCriteria
				.forClass(StoreInfo.class).add(Restrictions.eq("store.id", s_id))).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StorePictures> getStorePicturesByStoreId(int s_id) {
		
		return (List<StorePictures>) hibernateTemplate.findByCriteria(DetachedCriteria
				.forClass(StorePictures.class).add(Restrictions.eq("store.id", s_id)).add(Restrictions.eq("isDel", 0)));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Food> getFoodAllByStoreId(int s_id) {
		
		return (List<Food>) hibernateTemplate.findByCriteria(DetachedCriteria
				.forClass(Food.class).add(Restrictions.eq("store.id", s_id)).add(Restrictions.eq("isDel",0)));
	}

	@Override
	public FoodInfo getFoodInfoByFoodId(String f_id) {
		@SuppressWarnings("unchecked")
		List<FoodInfo> list = (List<FoodInfo>) hibernateTemplate.findByCriteria(DetachedCriteria
				.forClass(FoodInfo.class).add(Restrictions.eq("food.id", f_id)));
		if(!list.isEmpty())
			return list.get(0);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FoodPictures> getFoodPicturesByFoodId(String f_id) {
		
		return (List<FoodPictures>) hibernateTemplate.findByCriteria(DetachedCriteria
				.forClass(FoodPictures.class).add(Restrictions.eq("food.id", f_id)).add(Restrictions.eq("isDel", 0)));
	}

}
