package com.hadoop.service.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hadoop.common.Constants;
import com.hadoop.dao.report.DeliveryTimesReportIDao;
import com.hadoop.po.report.DeliveryTimesReport;

@Service("deliveryTimesReportService")
public class DeliveryTimesReportServiceImpl implements DeliveryTimesReportService {
	
	@Resource
	private DeliveryTimesReportIDao deliveryTimesReportDao;
	
	@Override
	@Cacheable(value = "defaultCache")
	public DeliveryTimesReport getReport(String id) {
		DeliveryTimesReport deliveryTimesReport = deliveryTimesReportDao.getReport(id);
		return deliveryTimesReport;
	}

	@Override
	@Cacheable(value = "defaultCache")
	public List<DeliveryTimesReport> getReports(String sqlWhere) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(Constants.SQL_WHERE, sqlWhere);
		return deliveryTimesReportDao.getReports(params);
	}

}
