package me.zhoubl.pay.sysmanage.service.api;

import me.zhoubl.pay.common.dto.PageDto;
import me.zhoubl.pay.sysmanage.service.entity.SysAccount;
import me.zhoubl.pay.sysmanage.service.ex.SysAccountBizEx;

/**
 * Created by zhoubl on 2017/2/9.
 */
public interface SysAccountService {
    public SysAccount login(SysAccount sysAccount) throws SysAccountBizEx;
    public void create(SysAccount sysAccount) throws SysAccountBizEx;
    public void delete(SysAccount sysAccount) throws SysAccountBizEx;
    public SysAccount update(SysAccount sysAccount) throws SysAccountBizEx;
    public PageDto getAllByPage(PageDto pageDto) throws SysAccountBizEx;
    public SysAccount get(SysAccount sysAccount) throws SysAccountBizEx;
    public SysAccount getByCode(String code) throws SysAccountBizEx;
}
