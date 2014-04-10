/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Reserved by
 *  G-Net Integrated Service Co.,  Ltd. 
 ****************************************************************************************/
/**
 * @file GuideActivity.java
 * @author wenhui.li
 * @date 2012-11-25 下午2:36:05 
 * Revision History 
 *     - 2012-11-25: change content for what reason
 ****************************************************************************************/

package com.gnet.shebao.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;

import com.gnet.shebao.R;
import com.gnet.shebao.adapter.IntroduceAdapter;
import com.gnet.shebao.common.DetialGallery;
import com.gnet.shebao.common.PageIndicatorView;

public class GuideActivity extends Activity {
    
    private DetialGallery mGallery;
    private Button questionBtn = null;
    private Button guideBtn = null;
    private Button medicalBtn = null;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        initView();
        registerEvent();
    }
    
    private void initView()
    {
	mGallery = (DetialGallery)findViewById(R.id.gallery);
	
	final PageIndicatorView mPageView = (PageIndicatorView)findViewById(R.id.pageView);
        mPageView.setTotalPage(3);
        // Set the adapter to our custom adapter (below)
        mGallery.setAdapter(new IntroduceAdapter(this));
        mGallery.setOnItemSelectedListener(new OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
            	  mPageView.setCurrentPage(arg2);
              }
              @Override
              public void onNothingSelected(AdapterView<?> arg0) {
              }
            });
        mGallery.setOnItemClickListener(new OnItemClickListener() {
            @SuppressWarnings("rawtypes")
	    public void onItemClick(AdapterView parent, View v, int position, long id) {
                mPageView.setCurrentPage(position);
            }
        });
        registerForContextMenu(mGallery);
        
        questionBtn = (Button)findViewById(R.id.question_btn);
        guideBtn = (Button)findViewById(R.id.guide_btn);
        medicalBtn = (Button)findViewById(R.id.medical_query_btn);
        
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return true;
    }
    
    private void registerEvent()
    {
    	questionBtn.setOnClickListener(new OnClickListener()
    	{
	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(GuideActivity.this, ListWithHeaderActivity.class);
		intent.putExtra("Flag", "question");
		startActivity(intent);
	    }
       });
	
	 guideBtn.setOnClickListener(new OnClickListener()
	 {
	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(GuideActivity.this, ListWithHeaderActivity.class);
		intent.putExtra("Flag", "guide");
		startActivity(intent);
	    }
	   });
	
	 medicalBtn.setOnClickListener(new OnClickListener()
	  {
	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(GuideActivity.this, MedicalActivity.class);
		startActivity(intent);
	    }
	});
    }
}


