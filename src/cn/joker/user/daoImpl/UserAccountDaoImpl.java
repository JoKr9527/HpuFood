package cn.joker.user.daoImpl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.joker.entity.User;
import cn.joker.user.dao.UserAccountDao;
@Repository("userAccountDao")
public class UserAccountDaoImpl implements UserAccountDao {
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void add(User user) {
		
		hibernateTemplate.save(user);
	}

	@Override
	public User login(User user) {
		@SuppressWarnings("unchecked")
		List<User> userList = (List<User>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(User.class)
				.add(Restrictions.eq("email", user.getEmail()))
				.add(Restrictions.eq("password", user.getPassword()))
				.add(Restrictions.ne("isLogin", 1)));
		if(!userList.isEmpty()) {
			userList.get(0).setIsLogin(1);
		}
		
		return userList.isEmpty()?null:userList.get(0);
	}

	@Override
	public User findById(String u_id) {
		
		return hibernateTemplate.get(User.class, u_id);
	}

	@Override
	public void update(User user) {
		
		hibernateTemplate.saveOrUpdate(user);
	}
	
	
	
}
