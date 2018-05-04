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

/*
 *  店家详细信息 si_id(varchar(40))		themain（主营,varchar(100)）	address(varchar(40)) 	getscore(得分，float）
				number_of_people(评价人数，int default 0） 	s_id(int);
 * */
@Entity
@Table(name="store_info")
public class StoreInfo {
	
	private String id;
	private String themain;
	private String address;
	private float getscore;
	private Date time;
	private int numberOfPeople = 0;
	
	private Store store;
	
	public StoreInfo(String themain, String address, float getscore, int numberOfPeople, Store store) {
		super();
		this.themain = themain;
		this.address = address;
		this.getscore = getscore;
		this.numberOfPeople = numberOfPeople;
		this.store = store;
	}
	public StoreInfo() {}
	@Id
	@Column(name="si_id")
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(name="themain",length=100)
	public String getThemain() {
		return themain;
	}
	public void setThemain(String themain) {
		this.themain = themain;
	}
	@Column(name="address",length=40)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="getscore",scale=2)
	public float getGetscore() {
		return getscore;
	}
	public void setGetscore(float getscore) {
		this.getscore = getscore;
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
	@Cascade(value= {CascadeType.SAVE_UPDATE})
	@JoinColumn(name="s_id")
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	
	
}
