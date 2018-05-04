package cn.joker.entity;

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
 * 食物图片实体类
 * food_pictures ：fp_id(varchar(40))	url(varchar(80))	is_del(int default(0))		f_id(varchar(40));
 * */
@Entity
@Table(name="food_pictures")
public class FoodPictures {
	
	private String id;
	private String url;
	private int isDel = 0;
	private Food food;
	
	@Id
	@Column(name="fp_id")
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="url",length=80)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name="is_del",nullable=false)
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	@ManyToOne
	@Cascade(value= {CascadeType.ALL})
	@JoinColumn(name="f_id")
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}

}
