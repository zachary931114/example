package me.zhoubl.pay.common.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoubl on 2017/2/14.
 */
public class PageDto implements Serializable{
	
	public PageDto(){}
	
    private Integer pageIndex = 1;
    private Integer pageSize = 25;
    private List<Object> datas;
    private Integer dataSumSize;
    private Map<String,Object> params;
    
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<Object> getDatas() {
		return datas;
	}
	public void setDatas(List<Object> datas) {
		this.datas = datas;
	}
	public Integer getDataSumSize() {
		return dataSumSize;
	}
	public void setDataSumSize(Integer dataSumSize) {
		this.dataSumSize = dataSumSize;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
