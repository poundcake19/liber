package com.marshmallowswisdom.liber.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marshmallowswisdom.liber.domain.Article;
import com.marshmallowswisdom.liber.domain.ArticleVersion;
import com.marshmallowswisdom.liber.domain.Tag;
import com.marshmallowswisdom.liber.persistence.Repository;

@Controller
@RequestMapping("/articles")
public class ArticlesController {
	
	@RequestMapping( method = RequestMethod.POST )
	@ResponseBody
	public RestfulArticle createTag( @ModelAttribute final ArticleForm article ) {
		final Repository repository = new Repository();
		final Set<Tag> tags = new TreeSet<Tag>();
		for( Integer tagId : article.getTags() ) {
			tags.add( repository.retrieveTag( tagId ) );
		}
		Article domainArticle = new Article( article.getName() );
		ArticleVersion version = new ArticleVersion( domainArticle, article.getContent(), tags );
		version = repository.saveArticleVersion( version );
		return new RestfulArticle( domainArticle );
//		final Integer parentId = tag.getParent();
//		Tag domainTag = new Tag( tag.getName(), repository.retrieveTag( parentId ) );
//		domainTag = repository.saveTag( domainTag );
//		return convertTag( domainTag );
	}

}
