package cn.joker.store.service;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.joker.store.dao.AccountDao;
import cn.joker.entity.Restaurant;
import cn.joker.entity.Store;
import cn.joker.entity.StoreAdmin;
import cn.joker.entity.StoreInfo;
@Service(value="accountManageService")
public class AccountManageService {
	
	@Autowired
	private AccountDao accountDaoImpl;
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDaoImpl = accountDao;
	}
	
	@Transactional(readOnly=false)
	public String register(Restaurant r,Store s,StoreInfo si,StoreAdmin admin) {
		
		return accountDaoImpl.register(r, s, si,admin);
	}
	
	@Transactional(readOnly=true)
	public void getUpdatedInfo(String store_id, ServletRequest request) {
		
		accountDaoImpl.getUpdatedInfo(store_id,request);
	}
	
	@Transactional(readOnly=false)
	public void update(Restaurant restaurant, Store store, StoreInfo storeInfo) {
		
		accountDaoImpl.update(restaurant,store,storeInfo);
	}

	public StoreAdmin login(String name, String password) {
		
		return accountDaoImpl.login(name,password);
	}
}
