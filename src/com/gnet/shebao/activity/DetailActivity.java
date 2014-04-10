/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Reserved by
 *  G-Net Integrated Service Co.,  Ltd. 
 ****************************************************************************************/
/**
 * @file DetailActivity.java
 * @author wenhui.li
 * @date 2012-12-9 下午1:39:30 
 * Revision History 
 *     - 2012-12-9: change content for what reason
 ****************************************************************************************/

package com.gnet.shebao.activity;

import java.io.Serializable;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.gnet.shebao.R;
import com.gnet.shebao.conn.SheBaoHttpManager;
import com.gnet.shebao.domain.Content;

public class DetailActivity extends Activity {
    
    private TextView titleView = null;
    private TextView timeView = null;
    private TextView detailView = null;
    private ImageView backBtn = null; //返回按钮
    private SheBaoHttpManager shebaodata  = null;
    private final int msgFlag = 0x1234;
    private Content content = null;
    private ProgressDialog dialog = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        shebaodata = new SheBaoHttpManager();
        initView();
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
		//添加数据
    	    	content = (Content) msg.getData().getSerializable("content");
		if(content != null)
		{
		    titleView.setText(content.getTitle());
		    timeView.setText(""+ content.getAdddate());
		    detailView.setText(content.getContent());
		}
		dialog.dismiss();
    	    	break;
    	    }
    	}
    };
    
    public void initView()
    {
	titleView = (TextView)findViewById(R.id.detail_title);
	timeView = (TextView)findViewById(R.id.detail_time);
	detailView = (TextView)findViewById(R.id.detail_text);
	Intent intent = getIntent();
	int articleId = intent.getIntExtra("articleId", 0);
	if(content != null)
	{
	    titleView.setText(content.getTitle());
	    timeView.setText(content.getAdddate());
	    detailView.setText(content.getContent());
	} else
	{
	    //开始loading
	    dialog = ProgressDialog.show(this,"", "正在获取数据请稍候...",true,false);  
	    getData(articleId);
	}
	backBtn = (ImageView)findViewById(R.id.back_btn); 
	
    }
    
    private void getData(int articleId)
    {	
	final int id = articleId;   
	//开启一条线程 提供新数据
	    new Thread(new Runnable() 
	    {
		public void run() 
		{
			//动态取得数据
		        Content content = shebaodata.getArticleDetail(id);
			//发送消息
			Message msg = new Message();
			msg.what = msgFlag;
			msg.getData().putSerializable("content", (Serializable)content);
			handler.sendMessage(msg);
		}}).start();
    }
    
    private void registerEvent()
    {
	//点击触发"返回上一页"事件
		 backBtn.setOnClickListener(new View.OnClickListener()
		 {
			@Override
			public void onClick(View v) {
				finish();
			}
		 });
    }
}
