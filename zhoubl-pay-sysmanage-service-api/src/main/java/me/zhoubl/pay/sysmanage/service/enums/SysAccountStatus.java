package me.zhoubl.pay.sysmanage.service.enums;

/**
 * Created by zhoubl on 2017/2/14.
 */
public enum SysAccountStatus {

    ENABLED("启用"),
    DISABLE("禁用");

    private String text;

    SysAccountStatus(String text){
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
