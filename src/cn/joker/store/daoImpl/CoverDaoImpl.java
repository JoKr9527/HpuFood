package cn.joker.store.daoImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.joker.entity.Store;
import cn.joker.entity.StorePictures;
import cn.joker.store.dao.CoverDao;

@Repository("coverDaoImpl")
public class CoverDaoImpl implements CoverDao {
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	public void uploadURI(Store store, String uri) {
		
		StorePictures pictures = new StorePictures();
		pictures.setUrl(uri);
		pictures.setStore(store);
		hibernateTemplate.save(pictures);
	}

	@Override
	public void delPic(StorePictures sp) {
		
		hibernateTemplate.execute(new HibernateCallback<StorePictures>() {

			@Override
			public StorePictures doInHibernate(Session session) throws HibernateException {
				
				@SuppressWarnings("unchecked")
				Query<StorePictures> query = session.createQuery("update StorePictures sp set sp.isDel = 1 where sp.id = ?0");
				query.setParameter(0, sp.getId());
				query.executeUpdate();
				return null;
			}
			
		});
	}

}
