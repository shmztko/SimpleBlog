package jp.co.itfrontier.simpleblog.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jp.co.itfrontier.simpleblog.application.controller.BaseController;
import jp.co.itfrontier.simpleblog.model.Article;
import jp.co.itfrontier.simpleblog.model.Blog;
import jp.co.itfrontier.simpleblog.service.BlogService;

@Named("articleController")
@SessionScoped
public class ArticleController extends BaseController implements Serializable {

	private static final long serialVersionUID = 2455345450153025668L;

	@Inject
	private BlogService blogService;

	public String index() {
		if (getBlog() == null) {
			return "/index";
		} else {
			return list(getBlog());
		}
	}

	public String list(Blog blog) {
		Blog persistedBlog = blogService.findByKey(blog.getId());
		setBlog(persistedBlog);
		return redirectTo("/article/list");
	}

	public String create() {
		setArticle(new Article());
		return redirectTo("/article/create");
	}

	public String save() {
		Blog blog = getBlog();
		article.setBlog(blog);
		article.setPostedAt(new Date());
		blog.getArticles().add(article);
		blogService.update(blog);
		return list(blog);
	}

	public String show(Article target) {
		setArticle(target);
		return redirectTo("/article/show");
	}


	public String edit() {
		return redirectTo("/article/edit");
	}

	public String update() {
		Blog blog = getBlog();
		blog.getArticles().add(article);
		blogService.update(blog);
		return list(blog);
	}

	public String delete(Article target) {
		Blog blog = getBlog();
		blog.getArticles().remove(target);
		blogService.update(blog);
		return list(blog);
	}

	private Article article;

	private Blog blog;

	public Article getArticle() {
		return this.article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<Article> getArticles() {
		return getBlog().getArticles();
	}

	public Blog getBlog() {
		return this.blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}


}
