package jp.co.itfrontier.simpleblog.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import jp.co.itfrontier.simpleblog.application.model.PersistenceEntity;

@Entity(name = "blogs")
public class Blog extends PersistenceEntity implements Serializable {

	private static final long serialVersionUID = 567827414375204067L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, length = 256)
	private String name;

	@OrderBy("postedAt DESC")
	@OneToMany(targetEntity = Article.class, mappedBy = "blog", cascade = CascadeType.ALL)
	private List<Article> articles;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Article> getArticles() {
		return this.articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
}
