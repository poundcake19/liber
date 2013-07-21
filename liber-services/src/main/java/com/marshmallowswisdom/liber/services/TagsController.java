package com.marshmallowswisdom.liber.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping
	@ResponseBody
	public String retrieveTags() {
		final Repository repository = new Repository();
		final List<Tag> tags = repository.retrieveTags();
//		return tags;
		ObjectMapper mapper = new ObjectMapper();
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

}
