package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/*@entity used to create table in database*/


@Entity
public class Category {
	
	/* to make id as a primary key */
	
	@Id 
	
	/* for auto update id automatically */	

	@GeneratedValue(strategy = GenerationType.AUTO)
	
	/* @column -- name for column in database */
	
	@Column(name = "category_id")
	private int id;
	
	/* unique=true --it not accept dublicate names */
	
	@Column(name = "category_name" ,unique=true,nullable=false)
	private String name;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
}
