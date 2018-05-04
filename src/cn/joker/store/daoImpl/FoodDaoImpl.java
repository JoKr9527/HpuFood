package cn.joker.store.daoImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.joker.entity.Food;
import cn.joker.entity.FoodInfo;
import cn.joker.entity.FoodPictures;
import cn.joker.entity.Store;
import cn.joker.store.dao.FoodDao;

@Repository("foodDaoImpl")
public class FoodDaoImpl implements FoodDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Override
	public void upload(Food food, Store store, FoodPictures picture) {
		food.setStore(store);
		picture.setFood(food);
		FoodInfo foodInfo = new FoodInfo();
		foodInfo.setTime(new Date());
		foodInfo.setFood(food);
		hibernateTemplate.save(food);
		hibernateTemplate.save(picture);
		hibernateTemplate.save(foodInfo);
	}
	
	@Override
	public Food getFoodInfo(String f_id) {
		Food food = hibernateTemplate.get(Food.class,f_id);
		return food;
	}

	@Override
	public List<FoodPictures> getPicturesList(String f_id) {
		
		Food food = new Food();
		food.setId(f_id);
		@SuppressWarnings("unchecked")
		List<FoodPictures> list = (List<FoodPictures>) hibernateTemplate.findByCriteria(DetachedCriteria
				.forClass(FoodPictures.class).add(Restrictions.eq("food", food)).add(Restrictions.eq("isDel", 0)));
		return list;
	}

	@Override
	public void updateFood(Food food, String url) {
		
		hibernateTemplate.update(food);
		if(url!= null) {
			FoodPictures picture = new FoodPictures();
			picture.setUrl(url);
			picture.setFood(food);
			hibernateTemplate.save(picture);
		}
		
	}
	public void del(String f_id) {
		hibernateTemplate.execute(new HibernateCallback<Food>() {

			@Override
			public Food doInHibernate(Session session) throws HibernateException {
				@SuppressWarnings("unchecked")
				Query<Food> query = session.createQuery("update Food f set f.isDel = 1 where f.id = ?0");
				query.setParameter(0, f_id);
				query.executeUpdate();
				return null;
			}
			
		});
	}
	public void delFoodPictures(String fp_id) {
		hibernateTemplate.execute(new HibernateCallback<FoodPictures>() {

			@Override
			public FoodPictures doInHibernate(Session session) throws HibernateException {
				@SuppressWarnings("unchecked")
				Query<FoodPictures> query = session.createQuery("update FoodPictures fp set fp.isDel = 1 where fp.id = ?0");
				query.setParameter(0, fp_id);
				query.executeUpdate();
				return null;
			}
			
		});
	}

}
