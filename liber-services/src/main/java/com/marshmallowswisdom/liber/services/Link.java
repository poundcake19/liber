package com.marshmallowswisdom.liber.services;

public class Link {
	
	private final String relationship;
	private final String url;
	
	public Link( final String relationship, final String url ) {
		this.relationship = relationship;
		this.url = url;
	}
	
	public String getRelationship() {
		return relationship;
	}
	
	public String getUrl() {
		return url;
	}

}
