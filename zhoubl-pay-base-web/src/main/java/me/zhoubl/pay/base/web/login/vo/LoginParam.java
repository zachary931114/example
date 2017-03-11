package me.zhoubl.pay.base.web.login.vo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by zhoubl on 2017/2/19.
 */
public class LoginParam {

    @NotBlank(message = "{loginParam.username}")
    private String username;
    @NotBlank(message = "{loginParam.pwd}")
    private String pwd;
    @NotBlank(message = "{loginParam.code}")
    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
