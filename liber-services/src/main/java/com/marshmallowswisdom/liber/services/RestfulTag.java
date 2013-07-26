package com.marshmallowswisdom.liber.services;

import java.util.ArrayList;
import java.util.List;

import com.marshmallowswisdom.liber.domain.Tag;

public class RestfulTag {
	
	private final Tag tag;
	private final List<Link> links;
	
	public RestfulTag( final Tag tag ) {
		this.tag = tag;
		links = new ArrayList<Link>();
	}
	
	public void addLink( final Link link ) {
		links.add( link );
	}
	
	public List<Link> getLinks() {
		return links;
	}
	
	public int getId() {
		return tag.getId();
	}
	
	public String getName() {
		return tag.getName();
	}
	
	public String getPath() {
		return tag.getPath();
	}

}
