package com.hadoop.po.report;

import java.io.Serializable;
import java.util.Date;

public class DeliveryTimesReport implements Serializable {

	private static final long serialVersionUID = -4229138098060919872L;
	
	
	/** 送货单号 */
	private String id;
	
	/** 源单号 */
	private String sId;
	
	/** 送货机构编码, 名称 */
	private String orgId;
	private String orgName;
	
	/** 送货仓库, 名称 */
	private String whId;
	private String whName;
	
	/** 源仓库, 名称 */
	private String sWhId;
	private String sWhName;
	
	/** 销售机构, 名称 */
	private String saleOrgId;
	private String saleOrgName;
	
	/** 送货类型 */
	private String deliveryType;
	
	/** 业主编码 */
	private String cusId;
	
	/** 打印次数 */
	private int prnCnt;
	
	/** 送货日期 */
	private Date deliveryDate;
	
	/** 送货批次 */
	private int deliveryBatch;
	
	/** 送货次数 */
	private int deliveryTimes;
	
	/** 状态 */
	private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getWhId() {
		return whId;
	}

	public void setWhId(String whId) {
		this.whId = whId;
	}

	public String getWhName() {
		return whName;
	}

	public void setWhName(String whName) {
		this.whName = whName;
	}

	public String getsWhId() {
		return sWhId;
	}

	public void setsWhId(String sWhId) {
		this.sWhId = sWhId;
	}

	public String getsWhName() {
		return sWhName;
	}

	public void setsWhName(String sWhName) {
		this.sWhName = sWhName;
	}

	public String getSaleOrgId() {
		return saleOrgId;
	}

	public void setSaleOrgId(String saleOrgId) {
		this.saleOrgId = saleOrgId;
	}

	public String getSaleOrgName() {
		return saleOrgName;
	}

	public void setSaleOrgName(String saleOrgName) {
		this.saleOrgName = saleOrgName;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getCusId() {
		return cusId;
	}

	public void setCusId(String cusId) {
		this.cusId = cusId;
	}

	public int getPrnCnt() {
		return prnCnt;
	}

	public void setPrnCnt(int prnCnt) {
		this.prnCnt = prnCnt;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getDeliveryBatch() {
		return deliveryBatch;
	}

	public void setDeliveryBatch(int deliveryBatch) {
		this.deliveryBatch = deliveryBatch;
	}

	public int getDeliveryTimes() {
		return deliveryTimes;
	}

	public void setDeliveryTimes(int deliveryTimes) {
		this.deliveryTimes = deliveryTimes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
