package cn.joker.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.joker.entity.Store;
import cn.joker.entity.StorePictures;
import cn.joker.store.dao.CoverDao;

@Service("coverService")
public class CoverService {
	
	@Autowired
	CoverDao coverDaoImpl;
	
	public void setCoverDao(CoverDao coverDao) {
		this.coverDaoImpl = coverDao;
	}


	@Transactional(readOnly=false)
	public void uploadURI(Store store,String uri) {
		 coverDaoImpl.uploadURI(store, uri);
	}

	@Transactional(readOnly=false)
	public void delPicture(StorePictures sp) {
		
		coverDaoImpl.delPic(sp);
	}
}
