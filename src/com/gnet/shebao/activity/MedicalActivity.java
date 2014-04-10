/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Reserved by
 *  G-Net Integrated Service Co.,  Ltd. 
 ****************************************************************************************/
/**
 * @file NoticeActivity.java
 * @author wenhui.li
 * @date 2012-11-25 下午2:43:29 
 * Revision History 
 *     - 2012-11-25: change content for what reason
 ****************************************************************************************/

package com.gnet.shebao.activity;

import com.gnet.shebao.R;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class MedicalActivity extends ActivityGroup   {                 
     private ImageView backBtn = null; 
     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    // TODO Auto-generated method stub
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.medical);	
	    initView();
	    registerEvent();
	}
	
	private void initView()
	{
	   TabHost tabHost= (TabHost) findViewById(R.id.tabhost);  
	   tabHost.setup();  
	   tabHost.setup(this.getLocalActivityManager());
	   TabWidget tabWidget = tabHost.getTabWidget();
	   Intent intentForPolicy = new Intent(this,ListNoHeaderActivity.class);
	   intentForPolicy.putExtra("Flag", "Medical");
	   Intent intentForNotice= new Intent(this,ListNoHeaderActivity.class);
	   intentForNotice.putExtra("Flag", "Hospital");
	   tabHost.addTab(tabHost.newTabSpec("listview_Medical").setIndicator("定点药店").setContent(intentForPolicy));
	   tabHost.addTab(tabHost.newTabSpec("listview_Hospital").setIndicator("定点医院").setContent(intentForNotice));
	   for (int i =0; i < tabWidget.getChildCount(); i++) 
	   {  
	       tabWidget.getChildAt(i).getLayoutParams().height = 100;  
	       //修改显示字体大小
	       TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
	       tv.setTextSize(20);
	       tv.setTextColor(this.getResources().getColorStateList(android.R.color.white));
	   }
	   backBtn = (ImageView)findViewById(R.id.back_btn); 
	}
	
	 private void registerEvent()
	 {
		backBtn.setOnClickListener(new View.OnClickListener()
		{
		    @Override
		    public void onClick(View v) 
		    {
		    	finish();
		    }});
	 }
}


