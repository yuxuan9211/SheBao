/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Reserved by
 *  G-Net Integrated Service Co.,  Ltd. 
 ****************************************************************************************/
/**
 * @file LoginActivity.java
 * @author wenhui.li
 * @date 2012-11-25 下午5:30:24 
 * Revision History 
 *     - 2012-11-25: change content for what reason
 ****************************************************************************************/

package com.gnet.shebao.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.gnet.shebao.R;

public class LoginActivity extends Activity {
    
    private ImageView backBtn = null; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initView();
        registerEvent();
    }
    
    
    private void initView()
    {
	backBtn = (ImageView)findViewById(R.id.back_btn);
    }
    
    private void registerEvent()
    {
	backBtn.setOnClickListener(new OnClickListener()
	{
	    @Override
	    public void onClick(View v) {
		finish();
	    }
	});
    }
}