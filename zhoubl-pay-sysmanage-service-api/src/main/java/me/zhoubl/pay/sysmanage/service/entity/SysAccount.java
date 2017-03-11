package me.zhoubl.pay.sysmanage.service.entity;

import me.zhoubl.pay.common.entity.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;


/**
 * <p>
 * 
 * </p>
 *
 * @author zhoubl
 * @since 2017-02-14
 */
public class SysAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank
	private String code;
	@NotBlank
	private String name;
	@NotBlank
	private String pwd;
	private String email;
	private String phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
