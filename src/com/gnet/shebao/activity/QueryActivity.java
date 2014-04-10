/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Reserved by
 *  G-Net Integrated Service Co.,  Ltd. 
 ****************************************************************************************/
/**
 * @file TransactActivity.java
 * @author wenhui.li
 * @date 2012-11-25 下午2:43:52 
 * Revision History 
 *     - 2012-11-25: change content for what reason
 ****************************************************************************************/

package com.gnet.shebao.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gnet.shebao.R;

public class QueryActivity  extends Activity {
    private EditText account = null;
    private Spinner type = null;
    private Button submitBtn = null;
    //private Button clearBtn = null;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query);
        initView();
        registerEvent();
    }
    
    
    private void initView()
    {
	 account = (EditText)findViewById(R.id.account_input);
	 type = (Spinner) findViewById(R.id.query_type_spinner);
	 ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	             this, R.array.types, android.R.layout.simple_spinner_item);
	 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 type.setAdapter(adapter);
	 type.setOnItemSelectedListener(new OnItemSelectedListener() {
	    @Override
	    public void onItemSelected(AdapterView<?> arg0, View arg1,
		    int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	    }

	    @Override
	    public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	    }
	    
	});
	 
	 submitBtn = (Button)findViewById(R.id.query_submit_btn);
	 //clearBtn = (Button)findViewById(R.id.query_clear_btn);
    }
    
    private void registerEvent()
    {
       submitBtn.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			String accountNo = account.getText().toString();
			if(accountNo == null || accountNo.equals(""))
			{
				Toast.makeText(QueryActivity.this, "查询时社保账号不能为空!", Toast.LENGTH_SHORT);
			}
		}
    	   
       });
       
       submitBtn.setOnClickListener(new OnClickListener(){
   		@Override
   		public void onClick(View v) {
   			account.setText("");
   			
   		}
        });
    }
}

