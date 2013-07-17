package com.marshmallowswisdom.liber.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tag")
public class Tag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@ManyToOne
	@JoinColumn(name="parent_tag_id", referencedColumnName="id")
	private Tag parent;
	
	@SuppressWarnings("unused")
	private Tag() { /* for JPA */ }
	
	public Tag( final String name ) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName( final String name ) {
		this.name = name;
	}

}
