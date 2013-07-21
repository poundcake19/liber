package com.marshmallowswisdom.liber.web.form;

import java.util.List;

import org.springframework.util.AutoPopulatingList;

public class ArticleForm {
	
	private String name;
	private String content;
	private List<String> tags;
	
	public ArticleForm() {
		this.name = "";
		this.content = "";
		tags = new AutoPopulatingList<String>( String.class );
	}
	
	public ArticleForm( final String name, final String content ) {
		this.name = name;
		this.content = content;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName( final String name ) {
		this.name = name;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent( final String content ) {
		this.content = content;
	}
	
	public List<String> getTags() {
		return tags;
	}
	
	public void setTags( final List<String> tags ) {
		this.tags = tags;
	}

}
