/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Reserved by
 *  G-Net Integrated Service Co.,  Ltd. 
 ****************************************************************************************/
/**
 * @file HttpManager.java
 * @author wenhui.li
 * @date 2012-12-9 下午1:44:48 
 * Revision History 
 *     - 2012-12-9: change content for what reason
 ****************************************************************************************/

package com.gnet.shebao.network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class HttpManager {

    private String TAG = HttpManager.class.getSimpleName();///< 网络操作类
    /**
	 * @brief 处理Http请求
	 * @param httpURL http请求的URL
	 * @param reqJson 做为请求参数的JSON字符串
	 * @return 返回作为请求结果的JSOn字符串
	 */
	public String  processHttpRequest(String httpURL, String reqJson)
	{  
		String respJson = null;
	    try
	    {  
	    	//建立连接  
	    	URL url = new URL(httpURL);  
	        HttpURLConnection httpConn = (HttpURLConnection)url.openConnection();  
	           
	        //设置连接属性  
	        httpConn.setDoOutput(true);//使用 URL 连接进行输出  
	        httpConn.setDoInput(true);//使用 URL 连接进行输入  
	        httpConn.setUseCaches(false);//忽略缓存  
	        httpConn.setRequestMethod("POST");//设置URL请求方法  
	           
	        //设置请求属性  
	        //获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致  
	        byte[] requestStringBytes = reqJson.getBytes("UTF-8");  
	        httpConn.setRequestProperty("Content-length", "" + requestStringBytes.length);  
	        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  
	        httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接  
	        httpConn.setRequestProperty("Charset", "UTF-8");  
	            
	       //建立输出流，并写入数据  
	        DataOutputStream out = new DataOutputStream(httpConn.getOutputStream());
		    out.write(requestStringBytes);  
		    out.flush();
		    out.close();   
	       //获得响应状态  
	       int responseCode = httpConn.getResponseCode();  
	       if(HttpURLConnection.HTTP_OK == responseCode)//连接成功 
	       { 
	    	   //当正确响应时处理数据  
	           StringBuffer sb = new StringBuffer();  
	           String readLine;  
	           BufferedReader responseReader;  
	           //处理响应流，必须与服务器响应流输出的编码一致  
	           responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));  
	           while ((readLine = responseReader.readLine()) != null) 
	           {  
	        	   sb.append(readLine).append("\n");  
	           }  
	          responseReader.close();  
	          respJson = sb.toString();
	        }else
	        {
	        	//如果连接失败，则返回错误码
	        	respJson = "" + 110;
	        }
	   }catch(Exception ex)
	   {  
		   ex.printStackTrace();
		   Log.e(TAG, "processHttpRequest error " + ex.getMessage());
	   }
	   return respJson;
}
	
}

