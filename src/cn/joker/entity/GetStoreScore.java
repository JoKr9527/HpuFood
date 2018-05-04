package cn.joker.entity;
/*
 * 对店铺的评价实体类
 * get_store_score : gss_id(varchar(40))	score(float,可以为空)	describe_info(varchar(50))	publish_time(datetime)	si_id(varchar(40))	u_id(varchar(40))
 * */

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="get_store_score")
public class GetStoreScore {
	
	private String id;
	private float score;
	private String describe;
	private Date publishTime;
	private StoreInfo storeInfo;
	private User user;
	
	@Id
	@Column(name="gss_id")
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(nullable=true,scale=2)
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	@Column(name="describe_info",length=50)
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	@Column(name="publish_time")
	public Date getPublishTime() {
		return publishTime;
	}
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	@ManyToOne
	@Cascade(value= {CascadeType.ALL})
	@JoinColumn(name="si_id")
	public StoreInfo getStoreInfo() {
		return storeInfo;
	}
	public void setStoreInfo(StoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}
	@ManyToOne
	@Cascade(value= {CascadeType.ALL})
	@JoinColumn(name="u_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
