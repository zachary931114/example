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
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String code;
	private String name;
	private String url;
	private String target;
	private Integer order;
	private String indexCode;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}
}
