package jp.co.itfrontier.simpleblog.controller;

import java.util.ArrayList;
import java.util.List;

import jp.co.itfrontier.simpleblog.model.Article;
import jp.co.itfrontier.simpleblog.model.Blog;
import jp.co.itfrontier.simpleblog.service.BlogService;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

public class ArticleControllerTest {

	@Mocked
	private BlogService blogService;

	private Blog blog = new Blog();

	@Test
	public final void testIndex() throws Exception {
		ArticleController controller = new ArticleController();
		String nextView = controller.index();
		assertThat(nextView, is("/index"));
	}

	@Test
	public final void testIndex_BlogIsNotNull() throws Exception {
		new Expectations() {{
			blogService.findByKey(blog.getId()); result = blog;
		}};

		ArticleController controller = new ArticleController();
		Deencapsulation.setField(controller, blogService);

		controller.setBlog(blog);
		String nextView = controller.index();

		assertThat(nextView, is("/article/list?faces-redirect=true"));
	}

	@Test
	public final void testList() {
		ArticleController controller = new ArticleController();

		String nextView = controller.index();

		assertThat(nextView, is("/index"));
	}

	@Test
	public final void testList_BlogIsNotNull() {

		new Expectations() {{
			blogService.findByKey(blog.getId()); result = blog;
		}};

		ArticleController controller = new ArticleController();

		Deencapsulation.setField(controller, blogService);
		controller.setBlog(blog);

		String nextView = controller.index();

		assertThat(nextView, is("/article/list?faces-redirect=true"));
	}

	@Test
	public final void testCreate() {
		ArticleController controller = new ArticleController();

		String nextView = controller.create();

		assertThat(nextView, is("/article/create?faces-redirect=true"));
	}

	@Test
	public final void testSave() {

		blog.setArticles(new ArrayList<Article>());

		new Expectations() {{
			blogService.update(blog); result = blog;
		}};

		new Expectations() {{
			blogService.findByKey(blog.getId()); result = blog;
		}};

		ArticleController controller = new ArticleController();
		Deencapsulation.setField(controller, blogService);
		controller.setBlog(blog);
		controller.setArticle(new Article());

		String nextView = controller.save();

		assertThat(nextView, is("/article/list?faces-redirect=true"));
	}

	@Test
	public final void testShow() {
		ArticleController controller = new ArticleController();

		Article article = new Article();

		String nextView = controller.show(article);

		assertThat(nextView, is("/article/show?faces-redirect=true"));
	}

	@Test
	public final void testEdit() {
		ArticleController controller = new ArticleController();

		String nextView = controller.edit();

		assertThat(nextView, is("/article/edit?faces-redirect=true"));
	}

	@Test
	public final void testUpdate() {
		new Expectations() {{
			blogService.update(blog); result = blog;
		}};

		new Expectations() {{
			blogService.findByKey(blog.getId()); result = blog;
		}};

		ArticleController controller = new ArticleController();

		blog.setArticles(new ArrayList<Article>());
		controller.setBlog(blog);
		Deencapsulation.setField(controller, blogService);

		String nextView = controller.update();

		assertThat(nextView, is("/article/list?faces-redirect=true"));
	}

	@Test
	public final void testDelete() {
		new Expectations() {{
			blogService.update(blog); result = blog;
		}};
		new Expectations() {{
			blogService.findByKey(blog.getId()); result = blog;
		}};


		ArticleController controller = new ArticleController();

		Article article = new Article();

		List<Article> articles = new ArrayList<Article>();
		articles.add(article);
		blog.setArticles(articles);
		controller.setBlog(blog);
		Deencapsulation.setField(controller, blogService);

		String nextView = controller.delete(article);

		assertThat(nextView, is("/article/list?faces-redirect=true"));
	}

}
