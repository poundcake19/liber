package com.marshmallowswisdom.liber.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.marshmallowswisdom.liber.web.form.ArticleForm;

@Controller
public class ArticleVersionController {
	
	@RequestMapping( value = "/createArticle", method = RequestMethod.GET )
	public ModelAndView displayCreateArticlePage() {
		final ModelAndView mav = new ModelAndView( "createArticle" );
		mav.addObject( "article", new ArticleForm() );
		return mav;
	}
	
	@RequestMapping( value = "createArticle", method = RequestMethod.POST )
	public ModelAndView saveArticle( @ModelAttribute ArticleForm articleVersion ) {
		final ModelAndView mav = new ModelAndView( "saveArticle" );
		mav.addObject( "article", articleVersion );
		return mav;
	}

}
