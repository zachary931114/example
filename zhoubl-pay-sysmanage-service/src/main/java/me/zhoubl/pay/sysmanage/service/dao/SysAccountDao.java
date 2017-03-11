package me.zhoubl.pay.sysmanage.service.dao;

import me.zhoubl.pay.sysmanage.service.entity.SysAccount;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhoubl
 * @since 2017-02-14
 */
public interface SysAccountDao extends BaseMapper<SysAccount> {

	public int selectCountByCode(@Param(value = "code") String code);
	
	public int insertSysAccount(@Param(value = "sysAccount") SysAccount sysAccount);

}