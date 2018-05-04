package cn.joker.entity;
/*
 * 食品详细数据实体类
 * food_info : fi_id(varchar(40)) 	getscore(float) 	number_of_people(int default 0) 	f_id(varchar(40));
 * */

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
@Table(name="food_info")
public class FoodInfo {
	
	private String id;
	private float score;
	private int numberOfPeople = 0;
	private Date time;
	private Food food;
	
	@Id
	@Column(name="fi_id")
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="getscore",scale=2)
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	@Column(name="number_of_people",nullable=false)
	public int getNumberOfPeople() {
		return numberOfPeople;
	}
	public void setNumberOfPeople(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	@Column(name="publish_time")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
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
	
	
}
