package cn.joker.discuss.daoImpl;

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

import cn.joker.discuss.dao.EvaluateDao;
import cn.joker.entity.FoodInfo;
import cn.joker.entity.GetFoodScore;
import cn.joker.entity.GetStoreScore;
import cn.joker.entity.StoreInfo;
import cn.joker.entity.User;

@Repository("evaluateDaoImpl")
public class EvaluateDaoImpl implements EvaluateDao {
	
	private int pageCount = 10;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void addStoreEvaluate(GetStoreScore evaluatedStore, String store_id,String u_id) {
		
		evaluatedStore.setPublishTime(new Date());
		StoreInfo storeInfo = (StoreInfo) hibernateTemplate.findByCriteria(DetachedCriteria
				.forClass(StoreInfo.class).add(Restrictions.eq("store.id", Integer.parseInt(store_id)))).get(0);
		if(evaluatedStore.getScore() > 0f) {
			if(evaluatedStore.getScore() >= 5f)
				evaluatedStore.setScore(5f);
			storeInfo.setNumberOfPeople(storeInfo.getNumberOfPeople()+1);
			storeInfo.setGetscore((storeInfo.getGetscore()*(storeInfo.getNumberOfPeople()-1)+evaluatedStore.getScore())/storeInfo.getNumberOfPeople());;
		}
		evaluatedStore.setStoreInfo(storeInfo);
		User user = hibernateTemplate.get(User.class, u_id);
		evaluatedStore.setUser(user);
		hibernateTemplate.save(evaluatedStore);
	}

	@Override
	public void addFoodEvaluate(GetFoodScore evaluatedFood, String f_id, String u_id) {
		
		FoodInfo foodInfo = (FoodInfo) hibernateTemplate.findByCriteria(DetachedCriteria
				.forClass(FoodInfo.class).add(Restrictions.eq("food.id", f_id))).get(0);
		if(evaluatedFood.getScore() > 0f) {
			if(evaluatedFood.getScore()>=5f)
				evaluatedFood.setScore(5f);
			foodInfo.setNumberOfPeople(foodInfo.getNumberOfPeople()+1);
			foodInfo.setScore((foodInfo.getScore()*(foodInfo.getNumberOfPeople()-1)+evaluatedFood.getScore())/foodInfo.getNumberOfPeople());
		}
		evaluatedFood.setFoodInfo(foodInfo);
		User user = hibernateTemplate.get(User.class, u_id);
		evaluatedFood.setPublishTime(new Date());
		evaluatedFood.setUser(user);
		hibernateTemplate.save(evaluatedFood);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<GetStoreScore> getStoreEvaluate(String store_id, String pageNum) {
		
		
		StoreInfo storeInfo = (StoreInfo) hibernateTemplate.findByCriteria(DetachedCriteria
				.forClass(StoreInfo.class).add(Restrictions.eq("store.id", Integer.parseInt(store_id)))).get(0);
		
		return hibernateTemplate.execute(new HibernateCallback<List<GetStoreScore>>() {

			
			@Override
			public List<GetStoreScore> doInHibernate(Session session) throws HibernateException {
				Query<GetStoreScore> query = session.createQuery("from GetStoreScore gss where gss.storeInfo.id = ?0 ");
				query.setParameter(0, storeInfo.getId());
				int page = Integer.parseInt(pageNum);
				if(page<0)
					page=0;
				query.setFirstResult(page*10);
				query.setMaxResults(pageCount);
				return query.getResultList();
			}
			
		});
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<GetFoodScore> getFoodEvaluate(String f_id, String pageNum) {
		
		List<FoodInfo> foodInfoList = (List<FoodInfo>) hibernateTemplate.findByCriteria(DetachedCriteria
				.forClass(FoodInfo.class).add(Restrictions.eq("food.id", f_id)));
		if(foodInfoList!=null) {
			FoodInfo foodInfo = foodInfoList.get(0);
				return hibernateTemplate.execute(new HibernateCallback<List<GetFoodScore>>() {
				@Override
				public List<GetFoodScore> doInHibernate(Session session) throws HibernateException {
					
					Query<GetFoodScore> query = session.createQuery("from GetFoodScore gfs where gfs.foodInfo.id = ?0 ");
					query.setParameter(0, foodInfo.getId());
					int page = Integer.parseInt(pageNum);
					if(page<0)
						page=0;
					query.setFirstResult(page*10);
					query.setMaxResults(pageCount);
					return query.getResultList();
				}	
				});
		}else
			return null;
	}


}
