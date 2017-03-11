package me.zhoubl.pay.base.web.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import me.zhoubl.pay.common.utils.UtilCodec;

/**
 * account shiro 密码校验 Created by zhoubl on 2017年2月22日.
 */
public class AccountCredentialsMatcher extends SimpleCredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		Object tokenCredentials = UtilCodec.pwdCodec(String.valueOf(usernamePasswordToken.getPassword()),
				usernamePasswordToken.getUsername());
		Object accountCredentials = getCredentials(info);
		return equals(tokenCredentials, accountCredentials);
	}
}
