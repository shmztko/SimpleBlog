package jp.co.itfrontier.simpleblog.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import jp.co.itfrontier.simpleblog.application.model.PersistenceEntity;

@Entity(name = "articles")
public class Article extends PersistenceEntity implements Serializable {

	private static final long serialVersionUID = -6350100201945577L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "title", nullable = false, length = 256)
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "posted_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date postedAt;

	@ManyToOne(targetEntity = Blog.class)
	private Blog blog;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPostedAt() {
		return this.postedAt;
	}

	public void setPostedAt(Date postedAt) {
		this.postedAt = postedAt;
	}

	public Blog getBlog() {
		return this.blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
}
