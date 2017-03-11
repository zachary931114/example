package me.zhoubl.pay.sysmanage.service.api.impl;

import me.zhoubl.pay.common.dto.PageDto;
import me.zhoubl.pay.common.service.biz.impl.BaseBizImpl;
import me.zhoubl.pay.common.utils.UtilCodec;
import me.zhoubl.pay.mq.service.api.MqMessageService;
import me.zhoubl.pay.mq.service.entity.MqMessage;
import me.zhoubl.pay.mq.service.enums.MqMessageDeadStatus;
import me.zhoubl.pay.mq.service.enums.MqMessageStatus;
import me.zhoubl.pay.sysmanage.service.api.SysAccountService;
import me.zhoubl.pay.sysmanage.service.biz.SysAccountBiz;
import me.zhoubl.pay.sysmanage.service.biz.TccSysAccountBiz;
import me.zhoubl.pay.sysmanage.service.dao.SysAccountDao;
import me.zhoubl.pay.sysmanage.service.entity.SysAccount;
import me.zhoubl.pay.sysmanage.service.enums.SysAccountStatus;
import me.zhoubl.pay.sysmanage.service.ex.SysAccountBizEx;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.base.Strings;

/**
 * Created by zhoubl on 2017/2/9.
 */
@Service
public class SysAccountServiceImpl extends BaseBizImpl<SysAccountDao, SysAccount> implements SysAccountService {

	@Autowired
	private SysAccountBiz sysAccountBiz;
	
    @Autowired
    private TccSysAccountBiz tccSysAccountBiz;
    
    @Autowired
    private MqMessageService mqMessageService;

    @Override
    public SysAccount login(SysAccount sysAccount) throws SysAccountBizEx {
        return null;
    }

    @Override
    public void create(SysAccount sysAccount) throws SysAccountBizEx {
        tccSysAccountBiz.createSysAccountTry(sysAccount);
    }

    @Override
    public void delete(SysAccount sysAccount) throws SysAccountBizEx {

    }

    @Override
    public SysAccount update(SysAccount sysAccount) throws SysAccountBizEx {
        return null;
    }

    @Override
    public PageDto getAllByPage(PageDto pageDto) throws SysAccountBizEx {
        return null;
    }

    @Override
    public SysAccount get(SysAccount sysAccount) throws SysAccountBizEx {
        return null;
    }

    @Override
    public SysAccount getByCode(String code) throws SysAccountBizEx {
        return sysAccountBiz.getByCode(code);
    }
}
