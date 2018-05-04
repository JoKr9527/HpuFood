package cn.joker.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.joker.entity.User;
import cn.joker.user.dao.UserAccountDao;

@Service("userManageService")
public class UserManageService {

	@Autowired
	private UserAccountDao userAccountDao;
	public void setUserAccountDao(UserAccountDao userAccountDao) {
		this.userAccountDao = userAccountDao;
	}

	@Transactional(readOnly=true)
	public User login(User user) {
		return userAccountDao.login(user);
	}
	@Transactional(readOnly=false)
	public void register(User user) {
		
		userAccountDao.add(user);
	}
	@Transactional(readOnly=true)
	public User getUserInfo(String u_id) {
		
		return userAccountDao.findById(u_id);
	}

	@Transactional(readOnly=false)
	public void update(User user) {
		
		userAccountDao.update(user);
	}

}
