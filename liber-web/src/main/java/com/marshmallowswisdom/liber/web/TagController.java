package com.marshmallowswisdom.liber.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.marshmallowswisdom.liber.domain.Tag;
import com.marshmallowswisdom.liber.persistence.Repository;
import com.marshmallowswisdom.liber.web.form.TagForm;

@Controller
public class TagController {

	@RequestMapping(value="/createTag", method=RequestMethod.POST)
	public ModelAndView saveTag( @ModelAttribute TagForm tag ) {
		final Repository repository = new Repository();
		final Integer parentId = tag.getParent();
		final Tag domainTag = parentId == null ? new Tag( tag.getName() ) : 
													new Tag( tag.getName(), 
															repository.retrieveTag( parentId ) );
		repository.saveTag( domainTag );
		final ModelAndView mav = new ModelAndView( "saveTag" );
		mav.addObject( "tag", tag );
		return mav;
	}
	
	@RequestMapping("/tags/{id}")
	public ModelAndView viewTag( @PathVariable final int id ) {
		final Repository repository = new Repository();
		final Tag tag = repository.retrieveTag( id );
		final ModelAndView mav = new ModelAndView( "viewTag" );
		mav.addObject( "tag", tag );
		return mav;
	}

}
