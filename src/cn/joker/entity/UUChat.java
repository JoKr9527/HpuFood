package cn.joker.entity;
/*
 * 用户与用户交流的实体类
 * uu_chat : uuc_id(varchar(40))	content(varchar(50))	publish_time(datetime)	from_uid(varchar(40))	to_uid(varchar(40))	relation_id(varchar(40),关联自身或者get_food/store_score的主键，不强制约束);
 * */

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
@Entity
@Table(name="uu_chat")
public class UUChat {
	
	private String id;
	private String content;
	private Date time;
	private User from;
	private User to;
	private String relationId;
	
	@Id
	@Column(name="uuc_id")
	@GeneratedValue(generator="uuid")
	@GenericGenerator(name="uuid",strategy="uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Column(length=50)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="publish_time")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@ManyToOne
	@Cascade(value= {CascadeType.ALL})
	@JoinColumn(name="from_uid")
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	@ManyToOne
	@Cascade(value= {CascadeType.ALL})
	@JoinColumn(name="to_uid")
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}
	@Column(name="relation_id",length=40)
	public String getRelationId() {
		return relationId;
	}
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
	

}
