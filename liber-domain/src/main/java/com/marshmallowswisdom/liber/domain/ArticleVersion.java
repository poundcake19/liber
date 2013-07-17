package com.marshmallowswisdom.liber.domain;

public class ArticleVersion {
	
	private Article article;
	private String content;
	
	public ArticleVersion( final Article article, final String content ) {
		this.article = article;
		this.content = content;
	}
	
	public String getArticleName() {
		return article.getName();
	}
	
	public void setArticleName( final String name ) {
		article.setName( name );
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent( final String content ) {
		this.content = content;
	}

}
