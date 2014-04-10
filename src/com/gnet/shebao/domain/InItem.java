package com.gnet.shebao.domain;

/**
 * 单条个人缴费记录
 * {"type":"\u516c\u52a1\u5458","date1":1355043005,"money":"45.02","date2":1355043005}
 * @author leilei.liu
 *
 */
public class InItem {

	private String type;
	private long date1;
	private long date2;
	private String money;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getDate1() {
		return date1;
	}
	public void setDate1(long date1) {
		this.date1 = date1;
	}
	public long getDate2() {
		return date2;
	}
	public void setDate2(long date2) {
		this.date2 = date2;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	
	
}
