package jp.co.itfrontier.simpleblog.application.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <p>
 * Entityクラスの基底クラスです。<br/>
 * すべてのEntityに共通するフィールドや処理を記述します。
 * </p>
 * @author u102964
 */
@MappedSuperclass
public abstract class PersistenceEntity {

	//--------------------
	// Entity Fields.

	/** 作成日 */
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	/** 更新日 */
	@Column(name = "modified_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedAt;

	//--------------------
	// Entity Lifecycle Methods.

	/**
	 * 更新直前に実行される処理です。
	 */
	@PreUpdate
	private void preUpdate() {
		setModifiedAt(new Date());
	};

	/**
	 * 保存直前に実行される処理です。
	 */
	@PrePersist
	private void prePersist() {
		setCreatedAt(new Date());
	}

	/**
	 * 保存済みのEntityかどうかを判定します。
	 * @return 保存済みのEntityの場合 True，それ以外の場合False
	 */
	public boolean isNew() {
		return createdAt == null;
	}

	//--------------------
	// Entity Fields Accessors.

	/**
	 * @return 作成日
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt 作成日
	 */
	private void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return 更新日
	 */
	public Date getModifiedAt() {
		return modifiedAt;
	}

	/**
	 * @param modifiedAt 更新日
	 */
	private void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
