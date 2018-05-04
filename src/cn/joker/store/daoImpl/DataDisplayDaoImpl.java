package cn.joker.store.daoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.joker.entity.Food;
import cn.joker.entity.FoodInfo;
import cn.joker.entity.FoodPictures;
import cn.joker.entity.Store;
import cn.joker.entity.StoreInfo;
import cn.joker.entity.StorePictures;
import cn.joker.store.dao.DataDisplayDao;

@Repository("dataDisplayDaoImpl")
public class DataDisplayDaoImpl implements DataDisplayDao {
	
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<FoodPictures> getFoodDisplayInfo(String pageNum,String sortRule,String pageCount) {
		return getFoodDisplayInfo(pageNum, Integer.parseInt(pageCount), sortRule);
	}

	@Override
	public List<StorePictures> getStoreDisplayInfo(String pageNum, String sortRule,String pageCount) {
		return getStoreDisplayInfo(pageNum, Integer.parseInt(pageCount), sortRule);
	}
	
	@Override
	public List<Food> searchFood(String name) {
		
		return hibernateTemplate.execute(new HibernateCallback<List<Food>>() {

			@Override
			public List<Food> doInHibernate(Session session) throws HibernateException {
				@SuppressWarnings("unchecked")
				Query<Food> query = session.createQuery("from Food f where f.name like :name and f.isDel = :isDel");
				query.setParameter("name", "%"+name+"%");
				query.setParameter("isDel", 0);
				return query.getResultList();
			}
		});
	}

	@Override
	public List<Store> searchStore(String name) {
		return hibernateTemplate.execute(new HibernateCallback<List<Store>>() {

			@Override
			public List<Store> doInHibernate(Session session) throws HibernateException {
				@SuppressWarnings("unchecked")
				Query<Store> query = session.createQuery("from Store s where s.name like :name and s.isDel = :isDel");
				query.setParameter("name", "%"+name+"%");
				query.setParameter("isDel", 0);
				return query.getResultList();
			}
		});
	}
	
	private List<FoodPictures> getFoodDisplayInfo(String pageNum,int pageCount,String sortRule) {
		List<FoodInfo> foodInfoList = hibernateTemplate.execute(new HibernateCallback<List<FoodInfo>>() {

			@Override
			public List<FoodInfo> doInHibernate(Session session) throws HibernateException {
				@SuppressWarnings("unchecked")
				Query<FoodInfo> query = session.createQuery("from FoodInfo fi order by fi."+sortRule.split("/")[0]+" "+sortRule.split("/")[1]);
				int page = Integer.parseInt(pageNum);
				if(page<0)
					page=0;
				query.setFirstResult(page*9);
				query.setMaxResults(pageCount);
				return query.getResultList();
			}	
		});
		
		List<FoodPictures> lists = new ArrayList<>();
		Iterator<FoodInfo> iterator = foodInfoList.iterator();
		FoodInfo foodInfo = null;
		while(iterator.hasNext()) {
			foodInfo = iterator.next();
			String f_id = foodInfo.getFood().getId();
			lists.add(hibernateTemplate.execute(new HibernateCallback<FoodPictures>() {

						@Override
						public FoodPictures doInHibernate(Session session) throws HibernateException {
							@SuppressWarnings("unchecked")
							Query<FoodPictures> query = session.createQuery("from FoodPictures fp where fp.food.id = ?0 and fp.isDel = ?1");
							query.setParameter(0, f_id);
							query.setParameter(1, 0);
							return query.getResultList().isEmpty()?null:query.getResultList().get(0);
						}	
					})
			);
		}
		
		return lists;
	}
	
	// 首页展示，需传入页数，每页最大显示数，排序规则
	private List<StorePictures> getStoreDisplayInfo(String pageNum, int pageCount,String sortRule) {

		List<StoreInfo> storeInfoList = hibernateTemplate.execute(new HibernateCallback<List<StoreInfo>>() {

			@Override
			public List<StoreInfo> doInHibernate(Session session) throws HibernateException {
				
				@SuppressWarnings("unchecked")
				Query<StoreInfo> query = session.createQuery("from StoreInfo si order by si."+sortRule.split("/")[0]+" "+sortRule.split("/")[1]);
				int page = Integer.parseInt(pageNum);
				if(page<0)
					page=0;
				query.setFirstResult(page*9);
				query.setMaxResults(pageCount);
				return query.getResultList();
			}	
		});		
		List<StorePictures> lists = new ArrayList<>();
		Iterator<StoreInfo> iterator = storeInfoList.iterator();
		StoreInfo storeInfo = null;
		while(iterator.hasNext()) {
			storeInfo = iterator.next();
			int s_id = storeInfo.getStore().getId();
			lists.add(hibernateTemplate.execute(new HibernateCallback<StorePictures>() {
						@Override
						public StorePictures doInHibernate(Session session) throws HibernateException {
							@SuppressWarnings("unchecked")
							Query<StorePictures> query = session.createQuery("from StorePictures sp where sp.store.id = ?0 and sp.isDel = ?1");
							query.setParameter(0, s_id);
							query.setParameter(1, 0);
							return query.getResultList().isEmpty()?null:query.getResultList().get(0);
						}	
					})
			);
		}		
		return lists;
	}
}
