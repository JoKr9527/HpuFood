package cn.joker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/*
 * 用户最喜爱的食物实体类
 * like_food(用户最喜爱的食物) : 	lf_id(varchar(40)) 	score(用户给的评价分数,float)	f_id(varchar(40))	u_id(varchar(40));
 * */
@Entity
@Table(name="like_food")
public class LikeFood {
	
	private String id;
	private float score;
	private Food food;
	private User user;
	
	@Id
	@Column(name="lf_id")
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(scale=2)
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	@OneToOne
	@Cascade(value= {CascadeType.ALL})
	@JoinColumn(name="f_id")
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
	@OneToOne
	@Cascade(value= {CascadeType.ALL})
	@JoinColumn(name="u_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
