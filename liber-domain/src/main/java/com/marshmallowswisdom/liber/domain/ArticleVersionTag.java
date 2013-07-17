package com.marshmallowswisdom.liber.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="article_version_tag")
public class ArticleVersionTag {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="article_version_id", referencedColumnName="id")
	private ArticleVersion article;
	@ManyToOne
	@JoinColumn(name="tag_id", referencedColumnName="id")
	private Tag tag;
	
	@SuppressWarnings("unused")
	private ArticleVersionTag() { /* for JPA */ }
	
	public ArticleVersionTag( final ArticleVersion article, final Tag tag ) {
		this.article = article;
		this.tag = tag;
	}
	
	public ArticleVersion getArticleVersion() {
		return article;
	}
	
	public Tag getTag() {
		return tag;
	}

}
