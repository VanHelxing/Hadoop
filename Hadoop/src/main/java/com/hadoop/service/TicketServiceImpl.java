package com.hadoop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hadoop.common.Constants;
import com.hadoop.dao.TicketIDao;
import com.hadoop.po.Ticket;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {
	
	@Resource
	private TicketIDao ticketDao;

	/*
	 * 根据条件查询车票
	 * @param sqlwhere
	 * @return listTickets
	 */
	@Override
	public List<Ticket> selectTicketByParam(String sqlwhere) {
		
		Map<String, Object> params = new HashMap<>();
		params.put(Constants.SQL_WHERE, sqlwhere);
		
		List<Ticket> listTickets = ticketDao.selectTicketByParam(params);
		return listTickets;
	}

}
