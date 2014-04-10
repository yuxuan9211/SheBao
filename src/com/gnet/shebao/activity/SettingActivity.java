/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Reserved by
 *  G-Net Integrated Service Co.,  Ltd. 
 ****************************************************************************************/
/**
 * @file SettingActivity.java
 * @author wenhui.li
 * @date 2012-11-25 下午2:44:07 
 * Revision History 
 *     - 2012-11-25: change content for what reason
 ****************************************************************************************/

package com.gnet.shebao.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.gnet.shebao.R;

public class SettingActivity  extends Activity {
    
    private TextView login = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        initView();
        registerEvent();
    }
    
    
    private void initView()
    {
	login = (TextView)findViewById(R.id.setting_login);
    }
    
    private void registerEvent()
    {
	login.setOnClickListener(new OnClickListener()
	{
	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
		startActivity(intent);
	    }
	});
    }
}


