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
 * µÍº“–≈œ¢
 * store_admin : sa_id(varchar(40))	name(varchar(10)) 	password(char(18)) 	is_login(int default(0)) 	s_id(int);
 * */
@Entity
@Table(name="store_admin")
public class StoreAdmin {
	
	private String id;
	private String name;
	private String password;
	private int is_login = 0;
	private Store store;
	@Id
	@Column(name="sa_id")
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="name",length=10)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=18)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="is_login")
	public int getIs_login() {
		return is_login;
	}
	public void setIs_login(int is_login) {
		this.is_login = is_login;
	}
	@OneToOne
	@Cascade(value= {CascadeType.ALL})
	@JoinColumn(name="s_id")
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	
	
	
}
