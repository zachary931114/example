package me.zhoubl.pay.sysmanage.service.biz.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.base.Strings;

import me.zhoubl.pay.common.dto.PageDto;
import me.zhoubl.pay.common.service.biz.impl.BaseBizImpl;
import me.zhoubl.pay.common.utils.UtilBean;
import me.zhoubl.pay.common.utils.UtilCodec;
import me.zhoubl.pay.sysmanage.service.biz.SysAccountBiz;
import me.zhoubl.pay.sysmanage.service.dao.SysAccountDao;
import me.zhoubl.pay.sysmanage.service.entity.SysAccount;
import me.zhoubl.pay.sysmanage.service.enums.SysAccountStatus;
import me.zhoubl.pay.sysmanage.service.ex.SysAccountBizEx;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubl
 * @since 2017-02-14
 */
@Service
public class SysAccountBizImpl extends BaseBizImpl<SysAccountDao, SysAccount> implements SysAccountBiz {

    @Autowired
    private SysAccountDao sysAccountDao;

    @Override
    public SysAccount login(SysAccount sysAccount) throws SysAccountBizEx {
        if (Strings.isNullOrEmpty(sysAccount.getCode())) {
            throw SysAccountBizEx.PARAMS_ERROR;
        }

        if (Strings.isNullOrEmpty(sysAccount.getPwd())) {
            throw SysAccountBizEx.PARAMS_ERROR;
        }

        List<SysAccount> sysAccountList = selectList(new EntityWrapper<SysAccount>().eq("code", sysAccount.getCode()).eq("pwd", UtilCodec.pwdCodec(sysAccount.getPwd(), sysAccount.getCode())).eq("status", SysAccountStatus.ENABLED.name()));
        if (sysAccountList.size() > 0){
            return sysAccountList.get(0);
        }else{
            return null;
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysAccount create(SysAccount sysAccount) throws SysAccountBizEx {

        if (Strings.isNullOrEmpty(sysAccount.getCode())) {
            throw SysAccountBizEx.PARAMS_ERROR;
        }

        if (Strings.isNullOrEmpty(sysAccount.getPwd())) {
            throw SysAccountBizEx.PARAMS_ERROR;
        }

        if (selectCount(new EntityWrapper<SysAccount>().eq("code", sysAccount.getCode())) > 0) {
            throw new SysAccountBizEx(SysAccountBizEx.CODE_EXISTS_ERROR, "编码已存在");
        }
        sysAccount.setVersion(0);
        sysAccount.setCreateTime(new Date());
        sysAccount.setStatus(SysAccountStatus.ENABLED.name());
        sysAccount.setPwd(UtilCodec.pwdCodec(sysAccount.getPwd(), sysAccount.getCode()));

        if (!insert(sysAccount)) {
            throw new SysAccountBizEx(SysAccountBizEx.CREATE_ERROR, sysAccount.getCode() + "创建失败");
        }
        return sysAccount;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(SysAccount sysAccount) throws SysAccountBizEx {
        if (sysAccount.getId() == null) {
            throw SysAccountBizEx.PARAMS_ERROR;
        }
        sysAccount = selectById(sysAccount.getId());
        if ("root".equals(sysAccount.getCode())) {
            throw new SysAccountBizEx(SysAccountBizEx.NOT_DELETE_ERROR, "root不能删除");
        }
        if (!deleteById(sysAccount.getId())) {
            throw new SysAccountBizEx(SysAccountBizEx.DELETE_ERROR, sysAccount.getCode() + "删除失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysAccount update(SysAccount sysAccount) throws SysAccountBizEx {
        if (sysAccount.getId() == null) {
            throw SysAccountBizEx.PARAMS_ERROR;
        }
        SysAccount account = selectById(sysAccount.getId());

        if (!Strings.isNullOrEmpty(sysAccount.getPwd()) && !sysAccount.getPwd().equals(account.getPwd())) {
            sysAccount.setPwd(UtilCodec.pwdCodec(sysAccount.getPwd(), account.getCode()));
        }

        UtilBean.copyPropertiesIgnoreNull(sysAccount, account);
        account.setVersion(account.getVersion() + 1);
        if (!updateById(account)) {
            throw new SysAccountBizEx(SysAccountBizEx.UPDATE_ERROR, account.getCode() + "更新失败");
        }
        return account;
    }

    @Override
    public PageDto getAllByPage(PageDto pageDto) throws SysAccountBizEx {
        return null;
    }

    @Override
    public SysAccount get(SysAccount sysAccount) throws SysAccountBizEx {
        if (sysAccount.getId() == null) {
            throw SysAccountBizEx.PARAMS_ERROR;
        }
        return selectById(sysAccount.getId());
    }

    @Override
    public SysAccount getByCode(String code) throws SysAccountBizEx {
        if (Strings.isNullOrEmpty(code)) {
            throw SysAccountBizEx.PARAMS_ERROR;
        }

        List<SysAccount> sysAccountList = selectList(new EntityWrapper<SysAccount>().eq("code", code).eq("status", SysAccountStatus.ENABLED.name()));
        if (sysAccountList.size() > 0){
            return sysAccountList.get(0);
        }else{
            return null;
        }
    }

    
}
