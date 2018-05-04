package cn.joker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

/*
 * 店家实体类	s_id(int) 	name(char(20))   is_del(int default(0))	r_id(int);
 * */

@Entity
@Table(name="store")
public class Store {
	
	public Store() {}
	private int id;
	private String name;
	private int isDel = 0;
	private Restaurant restaurant;
	
	public Store(String name, int isDel, Restaurant restaurant) {
		super();
		this.name = name;
		this.isDel = isDel;
		this.restaurant = restaurant;
	}
	@Id
	@Column(name="s_id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment" ,strategy="increment")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="name",length=20)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="is_del",nullable=false)
	public int getIsDel() {
		return isDel;
	}
	
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	@OneToOne
	@Cascade(value= {CascadeType.ALL})
	@JoinColumn(name="r_id")
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
}
