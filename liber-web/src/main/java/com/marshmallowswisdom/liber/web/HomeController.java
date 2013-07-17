package com.marshmallowswisdom.liber.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.marshmallowswisdom.liber.persistence.Repository;

@Controller
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping( method = RequestMethod.GET )
	public ModelAndView getHomePage() {
		final Repository repository = new Repository();
		final ModelAndView mav = new ModelAndView( "home" );
		mav.addObject( "articles", repository.getArticles() );
		mav.addObject( "tags", repository.getTags() );
		return mav;
	}

}
