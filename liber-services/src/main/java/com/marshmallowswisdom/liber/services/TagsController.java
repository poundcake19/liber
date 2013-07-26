package com.marshmallowswisdom.liber.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.marshmallowswisdom.liber.domain.Tag;
import com.marshmallowswisdom.liber.persistence.Repository;

@Controller
@RequestMapping("/tags")
public class TagsController {
	
	private static final Logger LOG = Logger.getLogger( TagsController.class );
	
	@RequestMapping( method = RequestMethod.GET )
	@ResponseBody
	public String retrieveTags() {
		final Repository repository = new Repository();
		final List<Tag> tags = repository.retrieveTags();
//		final List<TagWrapper> restfulTags = new ArrayList<TagWrapper>();
//		for( Tag tag : tags ) {
//			final TagWrapper restfulTag = new TagWrapper( tag );
//			restfulTag.addLink( new Link( "self", "/liber-web/tags/" + tag.getId() ) );
//			restfulTags.add( restfulTag );
//		}
//		return restfulTags;
		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure( MapperFeature.DEFAULT_VIEW_INCLUSION, false );
		mapper.addMixInAnnotations( Tag.class, TagMixIn.class );
		String response = "";
		try {
			ObjectWriter writer = mapper.writerWithView( JacksonViews.Flat.class );
			response = writer.writeValueAsString( tags );
		} catch (JsonProcessingException error ) {
			LOG.error( "Error serializing tags", error );
		}
		return response;
	}
	
	@RequestMapping( method = RequestMethod.POST )
	@ResponseBody
	public TagWrapper createTag( @ModelAttribute final TagForm tag ) {
		final Repository repository = new Repository();
		final Integer parentId = tag.getParent();
		Tag domainTag = parentId == null ? new Tag( tag.getName() ) : 
													new Tag( tag.getName(), 
															repository.retrieveTag( parentId ) );
		domainTag = repository.saveTag( domainTag );
		final TagWrapper restfulTag = new TagWrapper( domainTag );
		restfulTag.addLink( new Link( "self", "/liber-web/tags/" + domainTag.getId() ) );
		return restfulTag;
//		final ObjectMapper mapper = new ObjectMapper();
//		mapper.configure( MapperFeature.DEFAULT_VIEW_INCLUSION, false );
//		mapper.addMixInAnnotations( Tag.class, TagMixIn.class );
//		String response = "";
//		try {
//			ObjectWriter writer = mapper.writerWithView( JacksonViews.Flat.class );
//			response = writer.writeValueAsString( domainTag );
//		} catch (JsonProcessingException error ) {
//			LOG.error( "Error serializing tags", error );
//		}
//		return response;
	}

}
