package com.marshmallowswisdom.liber.services;

import com.fasterxml.jackson.annotation.JsonView;

public abstract class TagMixIn {
	
	@JsonView( JacksonViews.Flat.class ) public abstract int getId();
	@JsonView( JacksonViews.Flat.class ) public abstract String getName();

}
