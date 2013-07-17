package com.marshmallowswisdom.liber.web;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.marshmallowswisdom.liber.domain.Article;
import com.marshmallowswisdom.liber.domain.ArticleVersion;
import com.marshmallowswisdom.liber.persistence.Repository;
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
		ArticleVersion version = new ArticleVersion( new Article( articleVersion.getName() ), 
														articleVersion.getContent() );
		
		final Repository repository = new Repository();
		repository.saveArticleVersion( version );
		
		final ModelAndView mav = new ModelAndView( "saveArticle" );
		mav.addObject( "article", articleVersion );
		return mav;
	}

}
