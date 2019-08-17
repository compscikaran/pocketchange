package com.mvc.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.models.pack.Category;

@Entity
@Table(name = "expense")
public class Expense implements Serializable{
	private static final long serialVersionUID = -7988799579036225137L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int expid;
	
	@Column
	private String title;
	
	@Column
	private float amount;
	
	@Column
	@Temporal(TemporalType.DATE)
	private Date stamp;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Category category;
	
	public int getExpid() {
		return expid;
	}
	public void setExpid(int expid) {
		this.expid = expid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Date getStamp() {
		return stamp;
	}
	public void setStamp(Date stamp) {
		this.stamp = stamp;
	}

}
