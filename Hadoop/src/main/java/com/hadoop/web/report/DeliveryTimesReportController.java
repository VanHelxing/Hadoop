package com.hadoop.web.report;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hadoop.po.report.DeliveryTimesReport;
import com.hadoop.service.report.DeliveryTimesReportService;

@Controller
public class DeliveryTimesReportController {

	@Resource
	private DeliveryTimesReportService deliveryTimesReportService;
	
	
	@RequestMapping("DeliveryTimesReport")
	@ResponseBody
	public String getReport(@RequestParam(value = "id") String id) throws UnsupportedEncodingException{
		
		DeliveryTimesReport report = deliveryTimesReportService.getReport(id);
		String jsonString = JSON.toJSONString(report);
		return new String(jsonString.getBytes(), "ISO-8859-1");
	}
	
	@RequestMapping("test")
	@ResponseBody
	public String test() throws UnsupportedEncodingException {
		return new String("范海辛".getBytes(), "ISO-8859-1");
	}
	
	
	@RequestMapping("test1")
	public String test1(ModelMap modelMap) {
		modelMap.addAttribute("name", "范海辛");
		return "main";
	}
}
