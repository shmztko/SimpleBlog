package jp.co.itfrontier.simpleblog.controller;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

public class ArticleControllerTest {

	@Inject
	private ArticleController testTarget;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		Properties p = new Properties();
        p.put("movieDatabase", "new://Resource?type=DataSource");
        p.put("movieDatabase.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("movieDatabase.JdbcUrl", "jdbc:hsqldb:mem:moviedb");
		EJBContainer.createEJBContainer(p).getContext().bind("inject", this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testIndex() {
		assertThat(testTarget, is(not((nullValue()))));
	}

	@Test
	public final void testList() {
		fail("Not yet implemented");
	}

	@Test
	public final void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	public final void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public final void testShow() {
		fail("Not yet implemented");
	}

	@Test
	public final void testEdit() {
		fail("Not yet implemented");
	}

	@Test
	public final void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public final void testDelete() {
		fail("Not yet implemented");
	}

}
