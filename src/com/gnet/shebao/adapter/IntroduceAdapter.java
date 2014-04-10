/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Reserved by
 *  G-Net Integrated Service Co.,  Ltd. 
 ****************************************************************************************/
/**
 * @file IntroduceAdapter.java
 * @author wenhui.li
 * @date 2012-11-25 上午11:47:57 
 * Revision History 
 *     - 2012-11-25: change content for what reason
 ****************************************************************************************/

package com.gnet.shebao.adapter;

import com.gnet.shebao.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class IntroduceAdapter extends BaseAdapter {
    private LayoutInflater inflater;   
    
    private Context mContext;
    private Integer[] mImageIds = {
    	    R.drawable.gallery_list_1,
            R.drawable.gallery_list_2,
            R.drawable.gallery_list_3,
    };
    
    public IntroduceAdapter(Context c) {
        mContext = c;

    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("static-access")
    public View getView(int position, View convertView, ViewGroup parent) {
	View view;
	if(convertView!=null){
		view = convertView;
	}else{
		view = inflater.from(mContext).inflate(R.layout.image_item, null);
	}
	
	ImageView imageView = (ImageView) view.findViewById(R.id.introduceIV);
	imageView.setBackgroundResource(mImageIds[position]);
	//imageView.setBackgroundResource(mGalleryItemBackground);
        
        return view;
    }

}


