package com.hadoop.dao.report;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hadoop.po.report.DeliveryTimesReport;

@Repository
public interface DeliveryTimesReportIDao {

	
	/** 通过送货单号查询送回次数信息  */
	public DeliveryTimesReport getReport(String id);
	
	/** 通过条件查询送回次数信息 */
	public List<DeliveryTimesReport> getReports(Map<String, Object> params);
	
	
}
