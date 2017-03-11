package me.zhoubl.pay.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhoubl on 2017/2/13.
 */
public class BaseEntity implements Serializable{

    /**
     * version
     */
    protected Integer version;
    
    /**
     * 创建时间
     */
    protected Date createTime;
    
    /**
     * 最后修改时间
     */
    protected Date updateTime;
    
    /**
     * 创建人
     */
    protected Long createrId;
    
    /**
     * 最后修改人
     */
    protected Long updaterId;
    
    /**
     * 状态
     */
    protected String status;
    
    /**
     * 备注
     */
    protected String remark;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCreaterId() {
		return createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public Long getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(Long updaterId) {
		this.updaterId = updaterId;
	}

}
