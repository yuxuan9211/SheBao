package com.gnet.shebao.domain;

import java.io.Serializable;

/**
 * 文章内容
 * @author leilei.liu
 *
 */
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;
	/** 文章id */
	private int id;
	/** 文章标题 */
	private String title;
	/** 文章内容 */
	private String content;
	/** 文章添加时间 */
	private int adddate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getAdddate() {
		return adddate;
	}
	public void setAdddate(int adddate) {
		this.adddate = adddate;
	}
	
}
