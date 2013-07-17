package com.marshmallowswisdom.liber.web.form;

public class TagForm {
	
	private String name;
	private Integer parent;
	
	public TagForm() {
		name = "";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName( final String name) {
		this.name = name;
	}
	
	public Integer getParent() {
		return parent;
	}
	
	public void setParent( final Integer parent) {
		this.parent = parent;
	}
	
}
