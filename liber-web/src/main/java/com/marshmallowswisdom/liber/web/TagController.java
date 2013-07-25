package com.marshmallowswisdom.liber.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.marshmallowswisdom.liber.domain.Tag;
import com.marshmallowswisdom.liber.persistence.Repository;

@Controller
public class TagController {
	
	@RequestMapping("/tags/{id}")
	public ModelAndView viewTag( @PathVariable final int id ) {
		final Repository repository = new Repository();
		final Tag tag = repository.retrieveTag( id );
		final ModelAndView mav = new ModelAndView( "viewTag" );
		mav.addObject( "tag", tag );
		return mav;
	}

}
