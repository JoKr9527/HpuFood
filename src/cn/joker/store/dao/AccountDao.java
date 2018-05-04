package cn.joker.store.dao;

import javax.servlet.ServletRequest;

import cn.joker.entity.Restaurant;
import cn.joker.entity.Store;
import cn.joker.entity.StoreAdmin;
import cn.joker.entity.StoreInfo;

public interface AccountDao {
	
	String register(Restaurant r,Store s,StoreInfo si,StoreAdmin admin);

	void getUpdatedInfo(String store_id, ServletRequest request);

	void update(Restaurant restaurant, Store store, StoreInfo storeInfo);

	StoreAdmin login(String name, String password);

}
