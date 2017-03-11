package me.zhoubl.pay.sysmanage.service.biz;

import java.util.Date;
import java.util.UUID;

import org.mengyun.tcctransaction.Compensable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.base.Strings;

import me.zhoubl.pay.common.service.biz.impl.BaseBizImpl;
import me.zhoubl.pay.common.utils.UtilCodec;
import me.zhoubl.pay.mq.service.api.MqMessageService;
import me.zhoubl.pay.mq.service.entity.MqMessage;
import me.zhoubl.pay.mq.service.enums.MqMessageDeadStatus;
import me.zhoubl.pay.mq.service.enums.MqMessageStatus;
import me.zhoubl.pay.sysmanage.service.dao.SysAccountDao;
import me.zhoubl.pay.sysmanage.service.entity.SysAccount;
import me.zhoubl.pay.sysmanage.service.ex.SysAccountBizEx;

@Service
public class TccSysAccountBiz extends BaseBizImpl<SysAccountDao, SysAccount> {

	private static final Logger logger = LoggerFactory.getLogger(TccSysAccountBiz.class);

	@Autowired
	private MqMessageService mqMessageService;

	@Compensable(confirmMethod = "createSysAccountConfirm" ,cancelMethod = "createSysAccountCancel")
	public void createSysAccountTry(SysAccount sysAccount) {

		logger.info("=====================>createSysAccountTry");

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
		sysAccount.setStatus("0");
		sysAccount.setPwd(UtilCodec.pwdCodec(sysAccount.getPwd(), sysAccount.getCode()));

		if (!insert(sysAccount)) {
			throw new SysAccountBizEx(SysAccountBizEx.CREATE_ERROR, sysAccount.getCode() + "创建失败");
		}

		 MqMessage mqMessage = new MqMessage();
		 mqMessage.setMessageId(null);
		 mqMessage.setMessageBody("123");
		 mqMessage.setMessageType("json");
		 mqMessage.setMessageRetryCount(5);
		 mqMessage.setStatus(MqMessageStatus.WAITING_CONFIRM.name());
		 mqMessage.setDeadStatus(MqMessageDeadStatus.NO.name());
		 mqMessage.setQueue("1");
		 mqMessageService.createMqMessageTry(null, mqMessage);
		
		 mqMessage = new MqMessage();
		 mqMessage.setMessageId(UUID.randomUUID().toString());
		 mqMessage.setMessageBody("1234");
		 mqMessage.setMessageType("json");
		 mqMessage.setMessageRetryCount(5);
		 mqMessage.setStatus(MqMessageStatus.WAITING_CONFIRM.name());
		 mqMessage.setDeadStatus(MqMessageDeadStatus.NO.name());
		 mqMessage.setQueue("2");
		 mqMessageService.createMqMessageTry(null, mqMessage);

	}

	@Transactional(rollbackFor = Exception.class)
	public void createSysAccountConfirm(SysAccount sysAccount) {
		logger.info("=====================>createSysAccountConfirm");
		SysAccount account = selectById(sysAccount.getId());
		if (!account.getStatus().equals("0")) {
			return;
		}
		account.setStatus("2");
		updateById(account);
	}

	@Transactional(rollbackFor = Exception.class)
	public void createSysAccountCancel(SysAccount sysAccount) {
		logger.info("=====================>createSysAccountCancel");
		if (sysAccount.getId() == null) {
			return;
		}
		SysAccount account = selectById(sysAccount.getId());
		if (!account.getStatus().equals("0")) {
			return;
		}
		account.setStatus("1");
		updateById(account);
	}

}
