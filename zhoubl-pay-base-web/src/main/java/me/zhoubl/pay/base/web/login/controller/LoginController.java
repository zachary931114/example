package me.zhoubl.pay.base.web.login.controller;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;

import me.zhoubl.pay.base.web.login.vo.LoginParam;
import me.zhoubl.pay.common.web.controller.BaseController;

/**
 * Created by zhoubl on 2017/2/19.
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @RequestMapping("/login")
    public String login(@Valid LoginParam loginParam, BindingResult result) {
        if (result.hasErrors()){
            return "/login";
        }
        String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (loginParam.getCode().equals(code)) {
        	
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginParam.getUsername(), loginParam.getPwd());
        token.setRememberMe(true);
        subject.login(token);
        return "/WEB-INF/views/main";
    }

}
