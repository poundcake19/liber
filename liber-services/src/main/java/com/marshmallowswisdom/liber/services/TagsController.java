package com.marshmallowswisdom.liber.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marshmallowswisdom.liber.domain.Tag;
import com.marshmallowswisdom.liber.persistence.Repository;

@Controller
@RequestMapping("/tags")
public class TagsController {
	
	@RequestMapping( method = RequestMethod.GET )
	@ResponseBody
	public List<RestfulTag> retrieveTags( @RequestParam final String parent ) {
		final Repository repository = new Repository();
		final List<Tag> tags = 
				( parent == null || parent.isEmpty() ) ? 
						repository.retrieveRootTags() : repository.retrieveTags();
		final List<RestfulTag> restfulTags = new ArrayList<RestfulTag>();
		for( Tag tag : tags ) {
			restfulTags.add( convertTag( tag ) );
		}
		return restfulTags;
	}
	
	@RequestMapping( method = RequestMethod.POST )
	@ResponseBody
	public RestfulTag createTag( @ModelAttribute final TagForm tag ) {
		final Repository repository = new Repository();
		final Integer parentId = tag.getParent();
		Tag domainTag = parentId == null ? new Tag( tag.getName() ) : 
													new Tag( tag.getName(), 
															repository.retrieveTag( parentId ) );
		domainTag = repository.saveTag( domainTag );
		return convertTag( domainTag );
	}
	
	private RestfulTag convertTag( final Tag tag ) {
		final RestfulTag restfulTag = new RestfulTag( tag );
		restfulTag.addLink( new Link( "self", "/liber-services/tags/" + tag.getId() ) );
		restfulTag.addLink( new Link( "view", "/liber-web/tags/" + tag.getId() ) );
		return restfulTag;
	}

}
