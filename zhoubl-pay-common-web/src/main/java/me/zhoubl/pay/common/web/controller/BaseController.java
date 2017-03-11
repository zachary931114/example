package me.zhoubl.pay.common.web.controller;

import me.zhoubl.pay.common.ex.BizEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhoubl on 2017/2/16.
 */
public class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    protected final String ERROR_INFO = "系统内部错误";

    /**
     * 初始化
     */
    @ModelAttribute
    public void init(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap){
        this.request = request;
        this.response = response;
        this.session = request.getSession();

    }

    @ExceptionHandler
    public String exp(Exception ex , HttpServletRequest req) {
        // 根据不同错误转向不同页面
        if(ex instanceof BizEx) {
            req.setAttribute("type", "BizEx");
            req.setAttribute("errMessage", ex.getMessage());
            return "/WEB-INF/views/error/500";
        }else {
            req.setAttribute("type", "Exception");
            req.setAttribute("errMessage", ERROR_INFO);
            logger.error(ex.getMessage(),ex);
            return "/WEB-INF/views/error/500";
        }
    }
}
