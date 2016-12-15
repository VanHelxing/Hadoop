<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String context = request.getContextPath();
	request.setAttribute("context",context);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>送货次数报表</title>
<script type="text/javascript" src="/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="/css/ReportStyle.css">
</head>
<body>
	<div id="reportHead">
		<datalist id="warehouse">
			<option value="0001">物流中心仓</option>
			<option value="0002">塘厦卖场</option>
			<option value="0004">新塘卖场</option>
			<option value="0006">凤岗卖场</option>
			<option value="0008">南城卖场</option>
			<option value="0010">常平卖场</option>
			<option value="0012">工程销售仓</option>
		</datalist>
		<ul>
			<li>
				<label for="id">送货单号:</label>
				<input type="text" id="id" name="h.id" maxlength="20"  placeholder="请输入送货单号...">
			</li>
			<li>
				<label for="s_id">TL单号:</label>
				<input type="text" id="s_id" name="h.s_id"  maxlength="20" placeholder="请输入TL单号...">
			</li>
			<li>
				<label for="org_id">送货机构:</label>
				<input type="text" id="org_id" name="h.org_id" maxlength="20" placeholder="请选择送货机构...">
			</li>
			<li>
				<label for="wh_id">送货仓库:</label>
				<input type="text" list="warehouse" id="wh_id" name="h.wh_id" maxlength="20" placeholder="请选择送货仓库...">
			</li>
		</ul>
		<div class="query">
			<button id="query">查询</button>
		</div>
	</div>
	<div id="reportContent">
		<table id="table_head">
			<tr>
				<th>序号</th>
				<th>送货单号</th>
				<th>源单号</th>
				<th>送货批次</th>
				<th>送货次数</th>
				<th>送货日期</th>
				<th>打印次数</th>
				<th>送货机构</th>
				<th>送货机构名称</th>
				<th>送货仓库</th>
				<th>送货仓库名称</th>
				<th>源仓库</th>
				<th>源仓库名称</th>
				<th>销售机构</th>
				<th>销售机构名称</th>
				<th>送货类型</th>
				<th>业主编码 </th>
				<th>状态</th>
			</tr>
		</table>
	</div>

	<script type="text/javascript">
		$(document).ready(function () {
			var sqlWhere = "";
			$("#query").click(function() {
				var id = $("#id").val();
				var sId = $("#s_id").val();
				var orgId = $("#org_id").val();
				var whId = $("#wh_id").val();
				sqlWhere = convertStatement("h.id", id, "h.s_id", sId, "h.org_id", orgId, "h.wh_id", whId);
				$.get(
					"${context}/DeliveryTimesReport/getReports.do",
					{
						sqlWhere : sqlWhere
					},
					function (data, status) {
						var json = eval('(' + data +')');
						$.each(json, function(index, report) {
							$("#table_head").append(
								"<tr><td>" + (index + 1) + "</td>" +
								"<td>" + report.id + "</td>" +
								"<td>" + report.sId + "</td>" +
								"<td>" + report.deliveryBatch + "</td>" +
								"<td>" + report.deliveryTimes + "</td>" +
								"<td>" + report.deliveryDate + "</td>" +
								"<td>" + report.prnCnt + "</td>" +
								"<td>" + report.orgId + "</td>" +
								"<td>" + report.orgName + "</td>" +
								"<td>" + report.whId + "</td>" +
								"<td>" + report.whName + "</td>" +
								"<td>" + report.sWhId + "</td>" +
								"<td>" + report.sWhName + "</td>" +
								"<td>" + report.saleOrgId + "</td>" +
								"<td>" + report.saleOrgName + "</td>" +
								"<td>" + report.deliveryType + "</td>" +
								"<td>" + report.cusId + "</td>" +
								"<td>" + report.status + "</td></tr>"
							);
						});
					}
				);
			});

			function convertStatement() {
				var args = arguments;
				var sqlWhere = "";
				
				for(var i = 0; i < args.length; i+=2)
				{
					sqlWhere += " coalesce(" + args[i] + ", '') like '" + args[i+1] + "%' and ";
				}
				sqlWhere = sqlWhere.substring(0, sqlWhere.length - 4);
				return sqlWhere;
			}
		});
	</script>
</body>
</html>