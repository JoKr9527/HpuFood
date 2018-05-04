package cn.joker.messagepush.daoImpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.joker.entity.Food;
import cn.joker.entity.Order;
import cn.joker.entity.StoreAdmin;
import cn.joker.messagepush.dao.MessagePushDao;
@Repository
public class MessagePushDaoImpl implements MessagePushDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	@Override
	public String findSaByFo(String f_id) {
		
		Food food = hibernateTemplate.get(Food.class, f_id);
		List<StoreAdmin> list = null;
		if(food != null)
			list = (List<StoreAdmin>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(StoreAdmin.class).add(Restrictions.eq("store.id",food.getStore().getId())));
						
		return  list.isEmpty()?null:list.get(0).getId();
	}

	@Override
	public Food findFood(String f_id) {
		
		return hibernateTemplate.get(Food.class, f_id);
	}

	@Override
	public Order saveNoAccept(Order order) {
		
		hibernateTemplate.save(order);
		return order;
	}

	@Override
	public Order updateIsAc(String fId,String u_id) {
		@SuppressWarnings("unchecked")
		List<Order> list = (List<Order>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Order.class)
				.add(Restrictions.eq("food.id", fId)).add(Restrictions.eq("user.id", u_id)));
		if(!list.isEmpty())
			hibernateTemplate.saveOrUpdate(list.get(0));
		return list.isEmpty()?null:list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrdersByS(String id) {
		StoreAdmin admin = hibernateTemplate.get(StoreAdmin.class, id);
		return (List<Order>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Order.class).add(Restrictions.eq("food.store.id", admin.getStore().getId())));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getOrdersByU(String id) {
		
		return (List<Order>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(Order.class).add(Restrictions.eq("user.id", id)));
	}

}
