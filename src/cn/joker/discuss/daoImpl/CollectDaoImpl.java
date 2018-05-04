package cn.joker.discuss.daoImpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.joker.discuss.dao.CollectDao;
import cn.joker.entity.Food;
import cn.joker.entity.FoodCollect;
import cn.joker.entity.Store;
import cn.joker.entity.StoreCollect;
import cn.joker.entity.User;
@Repository
public class CollectDaoImpl implements CollectDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Override
	public void addStoreCollect(String s_id, User user) {
		Store store = hibernateTemplate.get(Store.class, Integer.parseInt(s_id));
		StoreCollect storeCo = new StoreCollect();
		storeCo.setStore(store);
		storeCo.setUser(user);
		hibernateTemplate.save(storeCo);
	}
	@Override
	public void addFoodCollect(String f_id, User user) {
		
		Food food = hibernateTemplate.get(Food.class, f_id);
		FoodCollect foodCo = new FoodCollect();
		foodCo.setFood(food);
		foodCo.setUser(user);
		hibernateTemplate.save(foodCo);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<StoreCollect> getStoreCollectList(String id) {
		
		return (List<StoreCollect>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(StoreCollect.class).add(Restrictions.eq("user.id", id)));
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<FoodCollect> getFoodCollectList(String id) {
		
		return (List<FoodCollect>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(FoodCollect.class).add(Restrictions.eq("user.id", id)));
	}
	@Override
	public boolean isCollectStore(String s_id, String id) {
		@SuppressWarnings("unchecked")
		List<StoreCollect> storeCollect = (List<StoreCollect>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(StoreCollect.class)
				.add(Restrictions.eq("user.id", id)).add(Restrictions.eq("store.id", Integer.parseInt(s_id))));
		if(storeCollect.isEmpty())
			return false;
		else
			return true;
	}
	@Override
	public boolean isCollectFood(String f_id, String id) {
		@SuppressWarnings("unchecked")
		List<FoodCollect> foodCollect = (List<FoodCollect>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(FoodCollect.class)
				.add(Restrictions.eq("user.id", id)).add(Restrictions.eq("food.id", f_id)));
		if(foodCollect.isEmpty())
			return false;
		else
			return true;
	}

}
