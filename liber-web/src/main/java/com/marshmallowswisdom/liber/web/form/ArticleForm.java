package com.marshmallowswisdom.liber.web.form;

public class ArticleForm {
	
	private String name;
	private String content;
	
	public ArticleForm() {
		this.name = "";
		this.content = "";
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

}
