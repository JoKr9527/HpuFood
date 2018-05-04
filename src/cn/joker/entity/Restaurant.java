package cn.joker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 餐厅或者校外实体类
 * */
@Entity
@Table(name="restaurant")
public class Restaurant {
	
	
	private int id;
	private String name;
	
	
	public Restaurant(String name) {
		super();
		this.name = name;
	}
	public Restaurant() {}
	@Id
	@Column(name="r_id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment",strategy="increment")
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
}
