package com.entity.;

/**
* 描述：系统用户实体类
* @author linhuang
* @date 2019/09/20
*/
public class TabOrder implements Serializable{

	private Long id;
	private String orderId;
	private Long userId;
	private Date createTime;
	
	public Long getId() {
	    return this.id;
	}
	
	public void setId(Long id) {
	    this.id = id;
	}
	public String getOrderId() {
	    return this.orderId;
	}
	
	public void setOrderId(String orderId) {
	    this.orderId = orderId;
	}
	public Long getUserId() {
	    return this.userId;
	}
	
	public void setUserId(Long userId) {
	    this.userId = userId;
	}
	public Date getCreateTime() {
	    return this.createTime;
	}
	
	public void setCreateTime(Date createTime) {
	    this.createTime = createTime;
	}
}