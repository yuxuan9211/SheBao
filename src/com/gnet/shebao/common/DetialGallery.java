/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Reserved by
 *  G-Net Integrated Service Co.,  Ltd. 
 ****************************************************************************************/
package com.gnet.shebao.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

/**
 * @brief  产品介绍浏览类
 * @author pengfei.feng
 * @date 2012-10-20 下午03:56:44
 *
 */
public class DetialGallery extends Gallery {

	/**
	 * @breif 构造方法
	 * @param context 上下文文本对象
	 * @param attrSet AttributeSet对象
	 */
    public DetialGallery(Context context ,AttributeSet attrSet) {
       super(context,attrSet);
    }

    /**
     * @brief 判断是否左划
     * @param e1 起始点
     * @param e2 结束点
     * @return boolean 是左划
     */
	 private boolean isScrollingLeft(MotionEvent e1, MotionEvent e2){   
	    return e2.getX() > e1.getX(); 
	 }
	 
	 /**
	  * @brief 重写滑动事件
	  * @param e1 起始点
	  * @param e2 结束点
	  * @param velocityX 
	  * @param velocityY
	  * @return boolean 返回值
	  */
	 @Override
	 public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,float velocityY) {
	  // TODO Auto-generated method stub
	  int kEvent;  
	  if(isScrollingLeft(e1, e2)){ 
	   //Check if scrolling left     
	   kEvent = KeyEvent.KEYCODE_DPAD_LEFT;  
	   }  else{ 
	    //Otherwise scrolling right    
	    kEvent = KeyEvent.KEYCODE_DPAD_RIGHT;   
	    }  
	  onKeyDown(kEvent, null);  
	  return true;  
	  }
 }
