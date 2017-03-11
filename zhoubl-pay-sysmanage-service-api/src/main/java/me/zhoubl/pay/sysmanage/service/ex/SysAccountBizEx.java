package me.zhoubl.pay.sysmanage.service.ex;

import me.zhoubl.pay.common.ex.BizEx;

/**
 * Created by zhoubl on 2017/2/9.
 */
public class SysAccountBizEx extends BizEx{

    /**
     * 参数异常
     */
    public static final int PARAM_ERROR = 10000001;

    /**
     * 编码已存在
     */
    public static final int CODE_EXISTS_ERROR = 10001001;

    /**
     * 创建失败
     */
    public static final int CREATE_ERROR = 10001002;

    /**
     * 不能删除
     */
    public static final int NOT_DELETE_ERROR = 10001003;

    /**
     * 删除失败
     */
    public static final int DELETE_ERROR = 10001004;

    /**
     * 更新失败
     */
    public static final int UPDATE_ERROR = 10001005;


    public static final SysAccountBizEx PARAMS_ERROR = new SysAccountBizEx(PARAM_ERROR , "参数错误");

    public SysAccountBizEx(int errorCode, String msg) {
        super(errorCode, msg);
    }


}
