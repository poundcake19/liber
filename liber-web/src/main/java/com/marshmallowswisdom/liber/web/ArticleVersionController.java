package com.marshmallowswisdom.liber.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.marshmallowswisdom.liber.domain.Article;
import com.marshmallowswisdom.liber.domain.ArticleVersion;
import com.marshmallowswisdom.liber.domain.Tag;
import com.marshmallowswisdom.liber.persistence.Repository;
import com.marshmallowswisdom.liber.web.form.ArticleForm;

@Controller
public class ArticleVersionController {
	
	private static final Logger LOG = Logger.getLogger( ArticleVersionController.class );
	
	@RequestMapping( value = "/createArticle", method = RequestMethod.GET )
	public ModelAndView displayCreateArticlePage() {
		final ModelAndView mav = new ModelAndView( "createArticle" );
		mav.addObject( "article", new ArticleForm() );
		return mav;
	}
	
	@RequestMapping( value = "createArticle", method = RequestMethod.POST )
	public ModelAndView saveArticle( @ModelAttribute ArticleForm articleVersion ) {
		final Repository repository = new Repository();
		final Set<Tag> tags = new HashSet<Tag>();
		final List<Integer> tagIds = articleVersion.getTags();
		for( Integer tagId : tagIds ) {
			if( tagId != null ) {
				tags.add( repository.retrieveTag( tagId ) );
			}
		}
		
		for( Tag tag : tags ) {
			LOG.debug( "Tag: " + tag.getName() );
		}
		final ArticleVersion version = new ArticleVersion( new Article( articleVersion.getName() ), 
															articleVersion.getContent(), 
															tags );
		repository.saveArticleVersion( version );
		
		final ModelAndView mav = new ModelAndView( "saveArticle" );
		mav.addObject( "article", articleVersion );
		return mav;
	}

}
