package jp.co.itfrontier.simpleblog.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jp.co.itfrontier.simpleblog.application.controller.BaseController;
import jp.co.itfrontier.simpleblog.model.Article;
import jp.co.itfrontier.simpleblog.model.Blog;
import jp.co.itfrontier.simpleblog.service.BlogService;

@Named("blogController")
@ConversationScoped
public class BlogController extends BaseController implements Serializable {

	private static final long serialVersionUID = 2455345450153025668L;

	@Inject
	private BlogService blogService;

	public List<Blog> getAllBlogs() {
		return blogService.findAll();
	}

	public List<Article> getArticles() {
		return blog.getArticles();
	}

	public String list(Blog blog) {
		setBlog(blog);
		return redirectTo("/blog/articles");
	}

	public String create() {
		beginConversation();
		setBlog(new Blog());
		return redirectTo("/blog/create");
	}

	public String save() {
		blogService.save(blog.getId(), blog);
		endConversation();
		return redirectTo("/index");
	}

	public String edit(Blog target) {
		beginConversation();
		setBlog(target);
		return redirectTo("/blog/edit");
	}

	public String update() {
		blogService.update(blog);
		endConversation();
		return redirectTo("/index");
	}

	private Blog blog;

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
}
