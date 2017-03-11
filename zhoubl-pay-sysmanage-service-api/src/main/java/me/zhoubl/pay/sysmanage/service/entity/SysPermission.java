package me.zhoubl.pay.sysmanage.service.entity;

import me.zhoubl.pay.common.entity.BaseEntity;


/**
 * <p>
 * 
 * </p>
 *
 * @author zhoubl
 * @since 2017-02-14
 */
public class SysPermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String code;
	private String name;

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
}
