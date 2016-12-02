package com.hadoop.service;

import java.util.List;
import com.hadoop.po.Ticket;

public interface TicketService {

	/*
	 * 根据条件查询车票
	 * @param sqlwhere
	 * @return List<Ticket>
	 */
	public List<Ticket> selectTicketByParam(String sqlwhere);
}
