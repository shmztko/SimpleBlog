package jp.co.itfrontier.simpleblog.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import jp.co.itfrontier.simpleblog.application.service.JpaDaoSupport;
import jp.co.itfrontier.simpleblog.model.Blog;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BlogService extends JpaDaoSupport<Long, Blog>{

	public BlogService() {
		super(Blog.class);
	}
}
