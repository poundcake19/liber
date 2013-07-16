package com.marshmallowswisdom.liber.domain;

import java.util.Set;
import java.util.TreeSet;

public class Article {
	
	private String name;
	private Set<ArticleVersion> versions;
	
	public Article( final String name ) {
		this.name = name;
		versions = new TreeSet<ArticleVersion>();
	}
	
	public String getName() {
		return name;
	}
	
	public Set<ArticleVersion> getVersions() {
		return versions;
	}

}
