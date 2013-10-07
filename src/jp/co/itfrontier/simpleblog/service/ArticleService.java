package jp.co.itfrontier.simpleblog.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import jp.co.itfrontier.simpleblog.application.service.JpaDaoSupport;
import jp.co.itfrontier.simpleblog.model.Article;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ArticleService extends JpaDaoSupport<Long, Article>{

	public ArticleService() {
		super(Article.class);
	}
}
