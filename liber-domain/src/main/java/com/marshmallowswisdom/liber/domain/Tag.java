package com.marshmallowswisdom.liber.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@OneToMany(mappedBy="parent", cascade=CascadeType.ALL)
	private Set<Tag> childTags;
	@ManyToMany
	@JoinTable( name = "article_version_tag", 
				joinColumns = {@JoinColumn( name = "tag_id", referencedColumnName = "id" )}, 
				inverseJoinColumns = {@JoinColumn( name = "article_version_id", 
													referencedColumnName = "id" ) } )
	private Set<ArticleVersion> articles;
	
	
	@SuppressWarnings("unused")
	private Tag() { /* for JPA */ }
	
	public Tag( final String name ) {
		this.name = name;
	}
	
	public Tag( final String name, final Tag parent ) {
		this.name = name;
		this.parent = parent;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName( final String name ) {
		this.name = name;
	}
	
	public void setParent( final Tag parent ) {
		this.parent = parent;
	}
	
	public Set<Tag> getChildTags() {
		return childTags;
	}
	
	public Set<ArticleVersion> getArticles() {
		return articles;
	}

}
