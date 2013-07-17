package com.marshmallowswisdom.liber.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.marshmallowswisdom.liber.domain.ArticleVersion;

public class Repository {
	
	public ArticleVersion saveArticleVersion( ArticleVersion articleVersion ) {
		final EntityManagerFactory factory = Persistence.createEntityManagerFactory( "liber" );
		final EntityManager entityManager = factory.createEntityManager();
		final EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		articleVersion = entityManager.merge( articleVersion );
		transaction.commit();
		entityManager.close();
		return articleVersion;
	}

}
