package me.zhoubl.pay.sysmanage.service.biz;

import me.zhoubl.pay.common.dto.PageDto;
import me.zhoubl.pay.common.service.biz.BaseBiz;
import me.zhoubl.pay.sysmanage.service.entity.SysAccount;
import me.zhoubl.pay.sysmanage.service.ex.SysAccountBizEx;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubl
 * @since 2017-02-14
 */
public interface SysAccountBiz extends BaseBiz<SysAccount> {

    public SysAccount login(SysAccount sysAccount) throws SysAccountBizEx;
    public SysAccount create(SysAccount sysAccount) throws SysAccountBizEx;
    public void delete(SysAccount sysAccount) throws SysAccountBizEx;
    public SysAccount update(SysAccount sysAccount) throws SysAccountBizEx;
    public PageDto getAllByPage(PageDto pageDto) throws SysAccountBizEx;
    public SysAccount get(SysAccount sysAccount) throws SysAccountBizEx;
    public SysAccount getByCode(String code) throws SysAccountBizEx;
	
}
