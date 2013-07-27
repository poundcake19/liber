package com.marshmallowswisdom.liber.services;

import java.util.ArrayList;
import java.util.List;

public class ArticleForm {
	
	private String name;
	private String content;
	private List<Integer> tags;
	
	public ArticleForm() {
		name = "";
		content = "";
		tags = new ArrayList<Integer>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Integer> getTags() {
		return tags;
	}

	public void setTags(List<Integer> tags) {
		this.tags = tags;
	}

}
