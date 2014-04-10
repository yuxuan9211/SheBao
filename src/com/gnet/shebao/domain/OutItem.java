package com.gnet.shebao.domain;

/**
 * 单条消费记录
 * 
 * {"date":1355044410,"money":350,"type":"\u95e8\u8bca","name":"\u9655\u897f\u7701\u4eba\u6c11\u533b\u9662"}
 * @author leilei.liu
 *
 */
public class OutItem {
	
	private long date;
	private long money;
	private String type;
	private String name;
	
	
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public long getMoney() {
		return money;
	}
	public void setMoney(long money) {
		this.money = money;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
