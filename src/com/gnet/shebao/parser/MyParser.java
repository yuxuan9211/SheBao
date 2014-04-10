package com.gnet.shebao.parser;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.gnet.shebao.domain.Article;
import com.gnet.shebao.domain.Content;
import com.gnet.shebao.domain.InItem;
import com.gnet.shebao.domain.OutItem;
import com.gnet.shebao.domain.Profile;
import android.util.Log;

public class MyParser {
	
	/**
	 * 解析文章列表
	 * 
	 * @param json
	 * @return
	 */
	public static List<Article> parseArticleList(String json)
	{
		List<Article> lists = new ArrayList<Article>();
		
		try {
			JSONArray jsonArray = new JSONArray(json);
			int len = jsonArray.length();
			for(int i=0; i<len; i++) {
				
				JSONObject object = jsonArray.getJSONObject(i);
				Article article = parseArticle(object);
				
				lists.add(article);
			}
			return lists;
			
		} catch (JSONException e) {
			Log.e("parseArticleList", e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * 解析文章列表对象
	 * @param jsonObject
	 * @return
	 */
	public static Article  parseArticle(JSONObject jsonObject)
	{
		Article article = new Article();
		
		try {
			article.setCat(jsonObject.getInt("cat"));
			article.setId(Integer.parseInt(jsonObject.getString("id")));
			article.setTitle(jsonObject.getString("title"));
		} catch (JSONException e) {
			Log.e("parseArticle", e.getMessage());
		}
		
		return article;
		
	}
	
	/**
	 * 解析内容对象
	 * 
	 * @param json
	 * @return
	 */
	public static Content parseArticleDetial(String json)
	{
		Content content = new Content();
		
		try {
			JSONObject jsonObject = new JSONObject(json);
			content.setId(jsonObject.getInt("id"));
			content.setTitle(jsonObject.getString("title"));
			content.setContent(jsonObject.getString("content"));
			content.setAdddate(jsonObject.getInt("adddate"));
		
		} catch (JSONException e) {
			Log.e("parseArticleDetial", e.getMessage());
		}
		
		return content;
		
	}
	
	/**
	 * 解析账户详情
	 * {
	 * "sbzh":"6127220000000784",
	 * "name":"\u59d3\u540d",
	 * "gender":"\u7537",
	 * "cardid":"612722195312010270",
	 * "money":"1367.1"
	 * }
	 * @param json
	 * @return
	 */
	public static Profile parseProfile(String json)
	{
		Profile profile = new Profile();
		try {
			JSONObject jsonObject = new JSONObject(json);
			profile.setCardid(jsonObject.getString("cardid"));
			profile.setGender(jsonObject.getString("gender"));
			profile.setMoney(jsonObject.getString("money"));
			profile.setName(jsonObject.getString("name"));
			profile.setSbzh(jsonObject.getString("sbzh"));
		} catch (JSONException e) {
			Log.e("parseProfile", e.getMessage());
		}
		
		return profile;
		
	}
	
	/**
	 * 解析缴费记录列表
	 * 
	 * @param json
	 * @return
	 */
	public static List<InItem> parseInItems(String json)
	{
		List<InItem> list = new ArrayList<InItem>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			
			int len = jsonArray.length();
			
			for(int i=0; i<len; i++) {
				InItem inItem = parseInItem(jsonArray.getJSONObject(i));
				list.add(inItem);
			}
			
		} catch (JSONException e) {
			Log.e("parseInItems", e.getMessage());
		}
		
		return list;
	}
	
	/**
	 * 解析每条缴费记录
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static InItem parseInItem(JSONObject jsonObject)
	{
		InItem inItem = new InItem();
		
		try {
			inItem.setType(jsonObject.getString("type"));
			inItem.setMoney(jsonObject.getString("money"));
			inItem.setDate1(jsonObject.getLong("date1"));
			inItem.setDate2(jsonObject.getLong("date2"));
		} catch (JSONException e) {
			Log.e("parseInItem", e.getMessage());
		}
		return inItem;
		
	}
	
	/**
	 * 解析消费记录列表
	 * 
	 * @param json
	 * @return
	 */
	public static List<OutItem> parseOutItems(String json)
	{
		List<OutItem> list = new ArrayList<OutItem>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			
			int len = jsonArray.length();
			
			for(int i=0; i<len; i++) {
				OutItem outItem = parseOutItem(jsonArray.getJSONObject(i));
				list.add(outItem);
			}
			
		} catch (JSONException e) {
			Log.e("parseOutItems", e.getMessage());
		}
		
		return list;
	}
	
	/**
	 * 解析每条消费记录
	 * 
	 * @param jsonObject
	 * @return
	 */
	public static OutItem parseOutItem(JSONObject jsonObject)
	{
		OutItem outItem = new OutItem();
		
		try {
			outItem.setType(jsonObject.getString("type"));
			outItem.setMoney(jsonObject.getLong("money"));
			outItem.setDate(jsonObject.getLong("date"));
			outItem.setName(jsonObject.getString("name"));
		} catch (JSONException e) {
			Log.e("parseOutItem", e.getMessage());
		}
		return outItem;
		
	}
	
}
