package com.gnet.shebao.domain;

/**
 * 账户详情
 * {
 * "sbzh":"6127220000000784",
 * "name":"\u59d3\u540d",
 * "gender":"\u7537",
 * "cardid":"612722195312010270",
 * "money":"1367.1"
 * }
 * @author leilei.liu
 *
 */
public class Profile {
	
	private String sbzh;
	private String name;
	private String gender;
	private String cardid;
	private String money;
	
	
	public String getSbzh() {
		return sbzh;
	}
	public void setSbzh(String sbzh) {
		this.sbzh = sbzh;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
	
}
