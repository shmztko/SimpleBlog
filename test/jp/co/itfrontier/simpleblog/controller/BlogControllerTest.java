package jp.co.itfrontier.simpleblog.controller;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.Conversation;

import jp.co.itfrontier.simpleblog.model.Article;
import jp.co.itfrontier.simpleblog.model.Blog;
import jp.co.itfrontier.simpleblog.service.BlogService;
import mockit.Deencapsulation;
import mockit.Expectations;
import mockit.Mocked;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

public class BlogControllerTest {

	@Mocked
	private BlogService blogService;

	@Mocked
	private Conversation conversation;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetAllBlogs() {
		final List<Blog> blogs = Arrays.asList(new Blog(), new Blog());
		new Expectations() {{
			blogService.findAll(); result = blogs;
		}};

		BlogController controller = new BlogController();

		Deencapsulation.setField(controller, blogService);

		List<Blog> result = controller.getAllBlogs();

		assertThat(result, is(not(nullValue())));
		assertThat(result.isEmpty(), is(false));
		assertThat(result.size(), is(equalTo(2)));
	}

	@Test
	public final void testGetArticles() {
		BlogController controller = new BlogController();

		Blog blog = new Blog();
		blog.setArticles(Arrays.asList(new Article(), new Article()));

		controller.setBlog(blog);

		List<Article> result = controller.getArticles();

		assertThat(result, is(not(nullValue())));
		assertThat(result.isEmpty(), is(false));
		assertThat(result.size(), is(equalTo(2)));

	}

	@Test
	public final void testList() {
		BlogController controller = new BlogController();


		Blog blog = new Blog();
		String newView = controller.list(blog);

		assertThat(newView, is(equalTo("/blog/articles?faces-redirect=true")));
	}

	@Test
	public final void testCreate() {
		new Expectations() {{
			conversation.isTransient(); result = true;
			conversation.begin();
		}};

		BlogController controller = new BlogController();
		Deencapsulation.setField(controller, conversation);

		String newView = controller.create();

		assertThat(newView, is(equalTo("/blog/create?faces-redirect=true")));
	}

	@Test
	public final void testSave() {
		final Blog blog = new Blog();
		new Expectations() {{
			blogService.save(blog.getId(), blog); result = blog;
			conversation.isTransient(); result = false;
			conversation.end();
		}};

		BlogController controller = new BlogController();
		controller.setBlog(blog);
		Deencapsulation.setField(controller, blogService);
		Deencapsulation.setField(controller, conversation);

		String newView = controller.save();

		assertThat(newView, is(equalTo("/index?faces-redirect=true")));
	}

	@Test
	public final void testEdit() {
		new Expectations() {{
			conversation.isTransient(); result = true;
			conversation.begin();
		}};

		BlogController controller = new BlogController();
		Deencapsulation.setField(controller, conversation);

		Blog blog = new Blog();
		String newView = controller.edit(blog);

		assertThat(newView, is(equalTo("/blog/edit?faces-redirect=true")));
	}

	@Test
	public final void testUpdate() {
		final Blog blog = new Blog();
		new Expectations() {{
			blogService.update(blog); result = blog;
			conversation.isTransient(); result = false;
			conversation.end();
		}};

		BlogController controller = new BlogController();
		controller.setBlog(blog);
		Deencapsulation.setField(controller, blogService);
		Deencapsulation.setField(controller, conversation);

		String newView = controller.update();

		assertThat(newView, is(equalTo("/index?faces-redirect=true")));
	}

}
