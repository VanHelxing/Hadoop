package com.hadoop.service.report;

import java.util.List;
import com.hadoop.po.report.DeliveryTimesReport;

public interface DeliveryTimesReportService {
	
	
	/** 通过送货单号查询送回次数信息  */
	public DeliveryTimesReport getReport(String id);
	
	/** 通过条件查询送回次数信息 */
	public List<DeliveryTimesReport> getReports(String sqlWhere);
	
	
}
