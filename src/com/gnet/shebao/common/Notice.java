/****************************************************************************************
 * Copyright (c) 2010~2012 All Rights Reserved by
 *  G-Net Integrated Service Co.,  Ltd. 
 ****************************************************************************************/
/**
 * @file Notice.java
 * @author wenhui.li
 * @date 2012-12-9 下午1:43:07 
 * Revision History 
 *     - 2012-12-9: change content for what reason
 ****************************************************************************************/

package com.gnet.shebao.common;

public class Notice 
{
    private int noticeId;
    private String noticeTitle;
    private String noticeContent;
    private String noticeTime;
    public int getNoticeId() {
        return noticeId;
    }
    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }
    public String getNoticeTitle() {
        return noticeTitle;
    }
    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }
    public String getNoticeContent() {
        return noticeContent;
    }
    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }
    public String getNoticeTime() {
        return noticeTime;
    }
    public void setNoticeTime(String noticeTime) {
        this.noticeTime = noticeTime;
    }
}

