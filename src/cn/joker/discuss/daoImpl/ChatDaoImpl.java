package cn.joker.discuss.daoImpl;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.joker.discuss.dao.ChatDao;
import cn.joker.entity.SUChat;
import cn.joker.entity.StoreAdmin;
import cn.joker.entity.USChat;
import cn.joker.entity.UUChat;
import cn.joker.entity.User;

@Repository("chatDaoImpl")
public class ChatDaoImpl implements ChatDao{
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	public void addUserChat(String content, String user_id, String to_id, String relation_id) {
		
		User user = hibernateTemplate.get(User.class, user_id);
		User to = isUser(to_id);
		if(to!=null) {
			UUChat uu = new UUChat();
			uu.setContent(content);
			uu.setRelationId(relation_id);
			uu.setTime(new Date());
			uu.setFrom(user);
			uu.setTo(to);
			hibernateTemplate.save(uu);
		}else {
			USChat us = new USChat();
			us.setContent(content);
			us.setRelationId(relation_id);
			StoreAdmin storeAdmin = (StoreAdmin) hibernateTemplate.findByCriteria(DetachedCriteria
					.forClass(StoreAdmin.class).add(Restrictions.eq("store.id", Integer.parseInt(to_id)))).get(0);
			us.setTo(storeAdmin);
			us.setFrom(user);
			us.setTime(new Date());
			hibernateTemplate.save(us);
		}
		
	}

	@Override
	public void addStoreChat(String content, int store_id, String to_id, String relation_id) {
		
		StoreAdmin storeAdmin = (StoreAdmin) hibernateTemplate.findByCriteria(DetachedCriteria
				.forClass(StoreAdmin.class).add(Restrictions.eq("store.id", store_id))).get(0);

		SUChat su = new SUChat();
		su.setContent(content);
		su.setFrom(storeAdmin);
		su.setRelationId(relation_id);
		su.setTime(new Date());
		User user = isUser(to_id);
		if(user !=null) {
			su.setTo(user);
			hibernateTemplate.save(su);
		}
		
	}
	
	// 判断 to_id 代表 user 还是  store
	private User isUser(String to_id) {
		return hibernateTemplate.get(User.class,to_id);
	}

	@Override
	public List<UUChat> getChatListFromUU(String relation_id, String pageNum) {
		
		return hibernateTemplate.execute(new HibernateCallback<List<UUChat>>() {

			@Override
			public List<UUChat> doInHibernate(Session session) throws HibernateException {
				@SuppressWarnings("unchecked")
				Query<UUChat> query = session.createQuery("from UUChat uu where uu.relationId = ?0 ");
				query.setParameter(0, relation_id);
				return query.getResultList();
			}});
	}

	@Override
	public List<SUChat> getChatListFromSU(String relation_id, String pageNum) {
		
		return hibernateTemplate.execute(new HibernateCallback<List<SUChat>>() {

			@Override
			public List<SUChat> doInHibernate(Session session) throws HibernateException {
				@SuppressWarnings("unchecked")
				Query<SUChat> query = session.createQuery("from SUChat su where su.relationId = ?0 ");
				query.setParameter(0, relation_id);
				return query.getResultList();
			}});
	}

	@Override
	public List<USChat> getChatListFromUS(String relation_id, String pageNum) {
		
		return hibernateTemplate.execute(new HibernateCallback<List<USChat>>() {

			@Override
			public List<USChat> doInHibernate(Session session) throws HibernateException {
				@SuppressWarnings("unchecked")
				Query<USChat> query = session.createQuery("from USChat us where us.relationId = ?0 ");
				query.setParameter(0, relation_id);
				return query.getResultList();
			}});
	}
	
}
