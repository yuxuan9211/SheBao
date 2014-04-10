package com.gnet.shebao.conn;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import android.util.Log;

public class MyHttpClient {

	public static String post(String url, List<NameValuePair> data)
	{
		String result = "#NULL";
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);

		try {

			httppost.setEntity(new UrlEncodedFormEntity(data));

			HttpResponse response;
			response = httpclient.execute(httppost);
			
			String reDataString = "";
			InputStream inputStream = response.getEntity().getContent();
			if (inputStream != null) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int len = 0;
				byte[] mydata = new byte[4096];
				while ((len = inputStream.read(mydata)) != -1) {
					baos.write(mydata, 0, len);
				}
				byte[] datas = baos.toByteArray();
				
				reDataString = new String(datas, "UTF-8");
			}
			
			int statu = response.getStatusLine().getStatusCode();
			if(statu == HttpStatus.SC_OK) {//< 请求成功
				result = reDataString;
			} else {
				result = "#[" + statu + "]" + reDataString;
			}
			
			
			
		} catch (ClientProtocolException e) {
			Log.d("post", e.getMessage());
		} catch (IOException e) {
			Log.d("post", e.getMessage());
		}
		
		return result;
		
	}
	
}
