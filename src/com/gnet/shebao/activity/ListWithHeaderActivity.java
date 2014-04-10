/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Reserved by
 *  G-Net Integrated Service Co.,  Ltd. 
 ****************************************************************************************/
/**
 * @file DetailListActivity.java
 * @author wenhui.li
 * @date 2012-12-9 上午11:26:44 
 * Revision History 
 *     - 2012-12-9: change content for what reason
 ****************************************************************************************/

package com.gnet.shebao.activity;

import java.util.ArrayList;
import java.util.List;
import com.gnet.shebao.R;
import com.gnet.shebao.conn.SheBaoHttpManager;
import com.gnet.shebao.domain.Article;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class ListWithHeaderActivity extends Activity {
    
    private ListView listView = null;
    private TextView header = null;
    private SheBaoHttpManager shebaodata  = null;
    private final int msgFlag = 0x1234;
    private List<Article> data = null;
    private List<String>listTitle = new ArrayList<String>();//显示的数据
    private ArrayAdapter<String> adapter;//适配器
    private ProgressDialog dialog = null;
    private String flag = null;
    private ImageView backBtn = null; 
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_with_header);
        listView = (ListView)findViewById(R.id.detail_listview_with_header);
        shebaodata = new SheBaoHttpManager();
        //此时的data为空
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,listTitle);
        listView.setAdapter(adapter);
        header = (TextView)findViewById(R.id.list_view_header);
        backBtn = (ImageView)findViewById(R.id.back_btn); 
        
        Intent intent = getIntent();
	flag = intent.getStringExtra("Flag");
	if(flag.equals("question"))
	{
	    header.setText(getText(R.string.question));
	}else
	{
	    header.setText(getText(R.string.guide));
	}
	
        //初始化数据
        initData();
        //绑定事件
        registerEvent();
    }
   
    //在主线程中更新界面
    Handler handler = new Handler()
    {
    	public void handleMessage(Message msg)
    	{
    	    switch(msg.what)
    	    {
    	    	case msgFlag:
    	    	ArrayList<String> dataList = msg.getData().getStringArrayList("datalist");
		//添加数据
    	    	listTitle.addAll(dataList);
		adapter.notifyDataSetChanged();
		dialog.dismiss();
    	    	break;
    	    }
    	}
    };
    
    private void initData()
    {
	
	//开始loading
        dialog = ProgressDialog.show(this,"", "正在获取数据请稍候...",true,false);  
        if(flag.equals("question"))
	{
	    getData(1);
	} 
	else
	{
	    getData(2);
	} 
    }
	
    private void getData(int flag)
    {	
	final int cid = flag;   
	//开启一条线程 提供新数据
	    new Thread(new Runnable() 
	    {
		public void run() 
		{
			//动态取得数据
			data = shebaodata.getAllArticle(cid);
			for(int i=0; i<data.size(); i++)
			{
			    listTitle.add(data.get(i).getTitle());
			}
			//发送消息
			Message msg = new Message();
			msg.what = msgFlag;
			msg.getData().putStringArrayList("datalist", (ArrayList<String>)listTitle);
			handler.sendMessage(msg);
		}}).start();
    }
	
    private void registerEvent()
    {
	listView.setOnItemClickListener(new OnItemClickListener()
    	{
    	    @Override
    	    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		    long arg3) 
    	    {
    		int articleId = data.get(arg2).getId();
    		Intent intent = new Intent(ListWithHeaderActivity.this, DetailActivity.class);
    		intent.putExtra("articleId", articleId);
    		startActivity(intent);
    	    }
    	});
	
	 backBtn.setOnClickListener(new View.OnClickListener()
	 {
		@Override
		public void onClick(View v) {
			finish();
		}
	 });
    }

}

