package com.gnet.shebao.conn;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import com.gnet.shebao.domain.Article;
import com.gnet.shebao.domain.Content;
import com.gnet.shebao.domain.InItem;
import com.gnet.shebao.domain.OutItem;
import com.gnet.shebao.domain.Profile;
import com.gnet.shebao.parser.MyParser;
import android.util.Log;

public class SheBaoHttpManager {
	

	public SheBaoHttpManager() 
	{

	}
	
	/**
	 * 获取某分类下的所有文章
	 * 
	 * 返回文章对象列表，不包括内容。
	 * 
	 * @param cid 分类id
	 * @return list
	 */
	public List<Article> getAllArticle(int cid)
	{
		
		String url = Api.SHEBAO_API_ARTICLE;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("cid", Integer.toString(cid)));
		
		String json = MyHttpClient.post(url, params);
		
		if(json !=null && !json.startsWith("#")) {//< 返回正确值
			return MyParser.parseArticleList(json);
		} else {
			Log.e("getAllArticle", "json=" + json);
		}
		
		return null;
	}
	
	/**
	 * 获取文章详细
	 * @param id
	 * @return
	 */
	public Content getArticleDetail(int id)
	{
		String url = Api.SHEBAO_API_CONTENT;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("id", Integer.toString(id)));
		
		String json = MyHttpClient.post(url, params);
		
		if(json !=null && !json.startsWith("#")) {//< 返回正确值
			return MyParser.parseArticleDetial(json);
		} else {
			Log.e("getAllArticle", "json=" + json);
		}
		
		return null;
	}
	
	/**
	 * 登录
	 * @param cardid 省份证号
	 * @param name 名
	 * @return
	 */
	public String login(String cardid, String name)
	{
		String url = Api.SHEBAO_API_LOGIN;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("cardid", cardid));
		params.add(new BasicNameValuePair("name", name));
		
		String json = MyHttpClient.post(url, params);
		
		Log.d("http manager", "json=" + json);
		
		if(json !=null && !json.startsWith("#")) {//< 返回正确值
			return json;
		} else {
			Log.e("getAllArticle", "json=" + json);
		}
		
		return null;
	}
	
	/**
	 * 获取某个账号的详情
	 * 
	 * @param sbzh 账号
	 * @return
	 */
	public Profile getProfile(String sbzh)
	{
		String url = Api.SHEBAO_API_INFO;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("sbzh", sbzh));
		
		String json = MyHttpClient.post(url, params);
		
		Log.d("http manager", "json=" + json);
		
		if(json !=null && !json.startsWith("#")) {//< 返回正确值
			return MyParser.parseProfile(json);
		} else {
			Log.e("getAllArticle", "json=" + json);
		}
		
		return null;
	}
	
	/**
	 * 获取个人缴费记录
	 * @param sbzh
	 * @return
	 */
	public List<InItem> getInList(String sbzh)
	{
		String url = Api.SHEBAO_API_IN;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("sbzh", sbzh));
		
		String json = MyHttpClient.post(url, params);
		
		Log.d("http manager", "json=" + json);
		
		if(json !=null && !json.startsWith("#")) {//< 返回正确值
			return MyParser.parseInItems(json);
		} else {
			Log.e("getAllArticle", "json=" + json);
		}
		return null;
	}
	
	/**
	 * 获取个人消费记录
	 * 
	 * @param sbzh
	 * @return
	 */
	public List<OutItem> getOutList(String sbzh)
	{
		String url = Api.SHEBAO_API_OUT;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>(1);
		params.add(new BasicNameValuePair("sbzh", sbzh));
		
		String json = MyHttpClient.post(url, params);
		
		Log.d("http manager", "json=" + json);
		
		if(json !=null && !json.startsWith("#")) {//< 返回正确值
			return MyParser.parseOutItems(json);
		} else {
			Log.e("getAllArticle", "json=" + json);
		}
		return null;
	}
	
}
