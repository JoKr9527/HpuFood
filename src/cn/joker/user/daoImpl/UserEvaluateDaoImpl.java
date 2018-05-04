package cn.joker.user.daoImpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.joker.entity.GetFoodScore;
import cn.joker.entity.GetStoreScore;
import cn.joker.user.dao.UserEvaluateDao;

@Repository("userEvaluateDao")
public class UserEvaluateDaoImpl implements UserEvaluateDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public List<GetFoodScore> findEvaluatedFood(String u_id) {
		@SuppressWarnings("unchecked")
		List<GetFoodScore> foodList = (List<GetFoodScore>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(GetFoodScore.class)
				.add(Restrictions.eq("user.id", u_id)));
		return foodList;
	}

	@Override
	public List<GetStoreScore> findEvaluatedStore(String u_id) {
		@SuppressWarnings("unchecked")
		List<GetStoreScore> storeList = (List<GetStoreScore>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(GetStoreScore.class)
				.add(Restrictions.eq("user.id", u_id)));
		return storeList;
	}

}
