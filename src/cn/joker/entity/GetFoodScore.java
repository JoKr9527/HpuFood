package cn.joker.entity;

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

/*
 * 对食物的评价实体类
 * get_food_score : gfs_id(varchar(40))	score(float,可以为空)	describe_info(varchar(50))	publish_time(datetime)	fi_id(varchar(40))	u_id(varchar(40))
 * */
@Entity
@Table(name="get_food_score")
public class GetFoodScore {
	
	private String id;
	private float score;
	private String describe;
	private Date publishTime;
	private FoodInfo foodInfo;
	private User user;
	
	@Id
	@Column(name="gfs_id")
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
	@JoinColumn(name="fi_id")
	public FoodInfo getFoodInfo() {
		return foodInfo;
	}
	public void setFoodInfo(FoodInfo foodInfo) {
		this.foodInfo = foodInfo;
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
