package com.gnet.shebao.activity;

import com.gnet.shebao.R;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;


public class SheBaoActivity extends TabActivity {   
	private Context mcontext = SheBaoActivity.this;               
	private TabHost tabHost;                                                                       
	private TabHost.TabSpec spec;                               
	private RadioGroup radioGroup;                             
	private Intent intent;                                     
	private RadioButton guideRB;                                                                                        
	
	/**
	 * @brief 加载页面元素并注册监听事件
	 * @param savedInstanceState Bundle对象
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//初始化数据
		initData();
		//添加Activity
		addActivity();
		//注册事件
		registerEvents();
	}
	
	/**
	 * @brief  初始化数据
	 * @todo  
	 * 
	 *         -# 使用getResources()获取Resources对象；
	 *         -# 使用getTabHost()获取TabHost对象；
	 *         -# 使用findViewById()获取页面元素；
	 */
	private void initData() {
	   //初始化TabHost对象
	    tabHost = getTabHost();
	    //初始化radioGroup
	    radioGroup = (RadioGroup)this.findViewById(R.id.mainRG);
	    guideRB = (RadioButton) this.findViewById(R.id.radio_guide);
	    
	}
	
	/**
	 * @brief  添加Activity并设置当前显示页面
	 */
	private void addActivity() {
	    // Initialize a TabSpec for each tab and add it to the TabHost
	    intent = new Intent().setClass(this, GuideActivity.class);
	    spec = tabHost.newTabSpec("guide").setIndicator("guide").setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, NoticeActivity.class);
	    spec = tabHost.newTabSpec("notice").setIndicator("notice").setContent(intent);
	    tabHost.addTab(spec);
	    
	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, QueryActivity.class);
	    spec = tabHost.newTabSpec("query").setIndicator("query").setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, SettingActivity.class);
	    spec = tabHost.newTabSpec("setting").setIndicator("setting").setContent(intent);
	    tabHost.addTab(spec);
	    tabHost.setCurrentTabByTag("guide");
	    guideRB.setChecked(true);
	    
		
	}
	
	/**
	 * @brief  注册切换事件
	 * @todo   根据radioGroup对应的checkedId显示不同的Activity
	 */
	private void registerEvents() {
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(RadioGroup group, int checkedId) {
       	 switch(checkedId){
	           	 case R.id.radio_guide://首页
		        	spec = tabHost.newTabSpec("guide").setIndicator("guide").setContent(intent);
	           		tabHost.setCurrentTabByTag("guide");
	           		 break;
	           	 case R.id.radio_notice://公告
	           		tabHost.setCurrentTabByTag("notice");
	           		 break;
	           	case R.id.radio_transact://查询
	           		tabHost.setCurrentTabByTag("query");
	           		 break;
	           	 case R.id.radio_setting://设置
	           		tabHost.setCurrentTabByTag("setting");
	           		 break;
        
       	 	}
    	}
    }
    );	
}
	
/**
 * @brief 弹出退出客户端提示对话框
 */
	public void showExitDailog() {
		AlertDialog.Builder builder = new Builder(mcontext);
		 builder.setTitle(getResources().getString(R.string.notify))
		.setMessage(getResources().getString(R.string.exitmessage))
		.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		}).setPositiveButton(getResources().getString(R.string.exit), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				System.exit(0);
			}
		}).show();
	}
	
	/**
	 * @brief 监听物理返回键事件
	 * @param event 按键点击事件
	 * @return boolean 执行完毕
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		//判断是否为返回事件
		if(KeyEvent.KEYCODE_BACK==event.getKeyCode()){
			if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
				//弹出退出提示对话框
				showExitDailog();
        }
			return true;
		}
		
	    return super.dispatchKeyEvent(event);
	}
}