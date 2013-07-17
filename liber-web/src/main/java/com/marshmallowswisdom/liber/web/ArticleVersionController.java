package com.marshmallowswisdom.liber.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleVersionController {
	
	@RequestMapping( value = "/createArticle", method = RequestMethod.GET )
	public ModelAndView displayCreateArticlePage() {
		final ModelAndView mav = new ModelAndView( "createArticle" );
		return mav;
	}
	
	@RequestMapping( value = "createArticle", method = RequestMethod.POST )
	public ModelAndView saveArticle( @RequestParam final String content ) {
		final ModelAndView mav = new ModelAndView( "saveArticle" );
		mav.addObject( "content", content );
		return mav;
	}

}
