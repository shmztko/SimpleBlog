package jp.co.itfrontier.simpleblog.application.controller;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * <p>
 * コントローラクラスの基底クラスです。<br/>
 * コントローラで使用される共通処理を記述します。
 * </p>
 * @author u102964
 */
public class BaseController implements Serializable {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** 会話スコープ管理用オブジェクト */
	@Inject
	protected Conversation conversation;

	/**
	 * 会話スコープを開始します。
	 */
	protected void beginConversation() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
	}

	/**
	 * 会話スコープを終了します。
	 */
	protected void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	/**
	 * 指定されたページへのリダイレクト用文字列を取得します。
	 * @param path リダイレクト先ページ
	 * @return リダイレクト用文字列
	 */
	protected String redirectTo(String path) {
		return path + "?faces-redirect=true";
	}

	/**
	 * INFOレベルのFacesMessageを追加します。
	 * @param summary メッセージサマリ
	 * @param detail メッセージ詳細
	 */
	protected void addInfoMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	}

	/**
	 * WARNレベルのFacesMessageを追加します。
	 * @param summary メッセージサマリ
	 * @param detail メッセージ詳細
	 */
	protected void addWarnMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_WARN, summary, detail);
	}

	/**
	 * ERRORレベルのFacesMessageを追加します。
	 * @param summary メッセージサマリ
	 * @param detail メッセージ詳細
	 */
	protected void addErrorMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
	}

	/**
	 * FATALレベルのFacesMessageを追加します。
	 * @param summary メッセージサマリ
	 * @param detail メッセージ詳細
	 */

	protected void addFatalMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
	}

	/**
	 * FacesMessageをContextへ追加します。
	 * @param serverity メッセージレベル
	 * @param summary メッセージサマリ
	 * @param detail メッセージ詳細
	 */
	private void addMessage(FacesMessage.Severity serverity, String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(serverity, summary, detail));
	}

}
