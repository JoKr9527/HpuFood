package cn.joker.user.dao;

import cn.joker.entity.User;

public interface UserAccountDao {

	void add(User user);

	User login(User user);

	User findById(String u_id);

	void update(User user);

}
