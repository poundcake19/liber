package com.marshmallowswisdom.liber.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.marshmallowswisdom.liber.domain.Tag;
import com.marshmallowswisdom.liber.persistence.Repository;

@Controller
public class TagController {
	
	@RequestMapping(value="/createTag", method=RequestMethod.GET)
	public ModelAndView displayCreateTagPage() {
		final ModelAndView mav = new ModelAndView( "createTag" );
		mav.addObject( "tag", new Tag( "" ) );
		return mav;
	}

	@RequestMapping(value="/createTag", method=RequestMethod.POST)
	public ModelAndView saveTag( @ModelAttribute Tag tag ) {
		final Repository repository = new Repository();
		repository.saveTag( tag );
		final ModelAndView mav = new ModelAndView( "saveTag" );
		mav.addObject( "tag", tag );
		return mav;
	}

}
