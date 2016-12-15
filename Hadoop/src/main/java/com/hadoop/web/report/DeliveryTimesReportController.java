package com.hadoop.web.report;

import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hadoop.po.report.DeliveryTimesReport;
import com.hadoop.service.report.DeliveryTimesReportService;

@Controller
@RequestMapping("DeliveryTimesReport")
public class DeliveryTimesReportController {

	@Resource
	private DeliveryTimesReportService deliveryTimesReportService;
	
	
	@RequestMapping("/report")
	public String DeliveryTimesReport() {
		return "report/DeliveryTimesReport";
	}
	
	
	@RequestMapping("/getReport")
	@ResponseBody
	public String getReport(@RequestParam(value = "id") String id) throws UnsupportedEncodingException{
		
		DeliveryTimesReport report = deliveryTimesReportService.getReport(id);
		String jsonString = JSON.toJSONString(report);
		return new String(jsonString.getBytes(), "ISO-8859-1");
	}
	
	
	@RequestMapping("getReports")
	@ResponseBody
	public String getReports(String sqlWhere) {
		
		List<DeliveryTimesReport> lists = deliveryTimesReportService.getReports(sqlWhere);
		String jsonString = JSON.toJSONString(lists);
		return jsonString;
	}

}
