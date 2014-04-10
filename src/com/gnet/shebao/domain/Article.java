package com.gnet.shebao.domain;

/**
 * 文章分类
 * @author leilei.liu
 *
 */
public class Article {
	
	/** 分类id */
	private int cat;
	/** 文章id */
	private int id;
	/** 文章标题 */
	private String title;
	
	public int getCat() {
		return cat;
	}
	public void setCat(int cat) {
		this.cat = cat;
	}
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

}
