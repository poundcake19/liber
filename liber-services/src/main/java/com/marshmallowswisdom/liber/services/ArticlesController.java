package com.marshmallowswisdom.liber.services;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.marshmallowswisdom.liber.domain.Article;
import com.marshmallowswisdom.liber.domain.ArticleVersion;
import com.marshmallowswisdom.liber.domain.ContentFieldValue;
import com.marshmallowswisdom.liber.domain.Tag;
import com.marshmallowswisdom.liber.persistence.Repository;

@Controller
@RequestMapping("/articles")
public class ArticlesController {
	
	private static final Logger LOG = Logger.getLogger( ArticlesController.class );
	
	@RequestMapping( value = "/{id}", method = RequestMethod.GET )
	@ResponseBody
	public RestfulArticle retrieveArticle( @PathVariable final int id ) {
		final Repository repository = new Repository();
		final Article article = repository.retrieveArticle( id );
		return new RestfulArticle( article );
	}
	
	@RequestMapping( method = RequestMethod.POST )
	@ResponseBody
	public RestfulArticle createArticle( @RequestBody final ArticleForm article ) {
		final Repository repository = new Repository();
		final Set<ContentFieldValue> fieldValues = new HashSet<ContentFieldValue>();
		for( ContentFieldValueForm field : article.getFields() ) {
			fieldValues.add( new ContentFieldValue( repository.retrieveField( field.getId() ), 
																		field.getValue() ) );
		}
		final Set<Tag> tags = new HashSet<Tag>();
		for( String path : article.getTags() ) {
			if( path != null && !path.isEmpty() ) {
				tags.add( repository.retrieveTagByPath( path ) );
			}
		}
		Article domainArticle = new Article( article.getName() );
		ArticleVersion version = new ArticleVersion( domainArticle, 
														article.getContent(), 
														fieldValues, 
														tags );
		domainArticle = repository.saveNewArticle( domainArticle, version );
		return new RestfulArticle( domainArticle );
	}
	
	@RequestMapping( value = "/{id}", method = RequestMethod.POST )
	@ResponseBody
	public RestfulArticle editArticle( @PathVariable final int id, @RequestBody final ArticleForm article ) {
		final Repository repository = new Repository();
		final Set<ContentFieldValue> fieldValues = new HashSet<ContentFieldValue>();
		for( ContentFieldValueForm field : article.getFields() ) {
			fieldValues.add( new ContentFieldValue( repository.retrieveField( field.getId() ), 
																		field.getValue() ) );
		}
		final Set<Tag> tags = new HashSet<Tag>();
		for( String path : article.getTags() ) {
			if( path != null && !path.isEmpty() ) {
				tags.add( repository.retrieveTagByPath( path ) );
			}
		}
		Article domainArticle = repository.retrieveArticle(id);
		domainArticle.setName( article.getName() );
		domainArticle.setLatestVersion(new ArticleVersion( domainArticle, 
				article.getContent(), 
				fieldValues, 
				tags ));
		domainArticle = repository.editArticle( domainArticle );	
		return new RestfulArticle( domainArticle );
	}
	
	@RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
	@ResponseBody
	public String deleteArticle( @PathVariable final int id ) {
		final Repository repository = new Repository();
		repository.deleteArticle( id );
		return "success";
	}
	
	@ExceptionHandler( Exception.class )
	public void handleException( final Exception error ) {
		LOG.error( "Error processing request", error );
	}

}
