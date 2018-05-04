package cn.joker.entity;

import java.util.Date;

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
@Entity
@Table(name="order")
public class Order {
	
	private String id;
	private Food food;
	private User user;
	private Date time;
	private int isAc = 0;
	
	@Column(name="is_ac")
	public int getIsAc() {
		return isAc;
	}
	public void setIsAc(int isAc) {
		this.isAc = isAc;
	}
	@Id
	@Column(name="o_id")
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	@Column(name="time")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
