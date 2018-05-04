package cn.joker.store.dao;

import cn.joker.entity.Store;
import cn.joker.entity.StorePictures;

public interface CoverDao {
	void uploadURI(Store store,String uri);

	void delPic(StorePictures sp);
}
