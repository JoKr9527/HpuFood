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
 * µÍº“Õº∆¨¡¥Ω”
 * store_pictures : sp_id(varchar(40))	url(varchar(80))	is_del(int default(0))		s_id(int);		
 * */
@Entity
@Table(name="store_pictures")
public class StorePictures {
	
	private String id;
	private String url;
	private int isDel = 0;
	private Store store;
	
	@Id
	@Column(name="sp_id")
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(length=80)
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
	@JoinColumn(name="s_id")
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	
	
}
