package cn.joker.store.daoImpl;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.joker.store.dao.AccountDao;
import cn.joker.entity.Restaurant;
import cn.joker.entity.Store;
import cn.joker.entity.StoreAdmin;
import cn.joker.entity.StoreInfo;
@Repository("accountDaoImpl")
public class AccountDaoImpl  implements AccountDao{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public String register(Restaurant r, Store s, StoreInfo si,StoreAdmin admin) {
		
		hibernateTemplate.save(r);
		hibernateTemplate.save(s);
		si.setTime(new Date());
		hibernateTemplate.save(si);
		hibernateTemplate.save(admin);
		
		return String.valueOf(s.getId());
	}

	@Override
	public void getUpdatedInfo(String store_id, ServletRequest request) {
		
		Store store = hibernateTemplate.get(Store.class, Integer.parseInt(store_id));
		Restaurant restaurant = hibernateTemplate.get(Restaurant.class, store.getRestaurant().getId());
		StoreInfo storeInfo = (StoreInfo) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(StoreInfo.class).add(Restrictions.eq("store",store))).get(0);
		request.setAttribute("store", store);
		request.setAttribute("restaurant", restaurant);
		request.setAttribute("storeInfo", storeInfo);
	}

	@Override
	public void update(Restaurant restaurant, Store store, StoreInfo storeInfo) {
		
		Store oldStore = hibernateTemplate.get(Store.class, store.getId());
		
		
		oldStore.setName(store.getName());
		oldStore.getRestaurant().setName(restaurant.getName());
	
		hibernateTemplate.execute(new HibernateCallback<StoreInfo>() {

			public StoreInfo doInHibernate(Session session) throws HibernateException {
				
				session.createQuery("update StoreInfo si set si.themain='"+storeInfo.getThemain()+"',si.address='"
				+storeInfo.getAddress()+"' where si.store.id = '"+store.getId()+"'").executeUpdate();
				return null;
			}});
	}

	@Override
	public StoreAdmin login(String name, String password) {
		@SuppressWarnings("unchecked")
		List<StoreAdmin> list =  (List<StoreAdmin>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(StoreAdmin.class).add(Restrictions.eq("name",name)).add(Restrictions.eq("password", password)));
		StoreAdmin admin = null;
		if(!list.isEmpty())
			admin = list.get(0);
		
		return admin;
	}

}
