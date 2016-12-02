package com.hadoop.dao;

import java.util.List;
import java.util.Map;

import org.cx.common.BaseCrud;
import org.springframework.stereotype.Repository;

import com.hadoop.po.Ticket;

@Repository
public interface TicketIDao extends BaseCrud<Ticket>{
	
	
	/*
	 * 根据条件查询车票
	 * @param params
	 * @return List<Ticket>
	 */
	public List<Ticket> selectTicketByParam(Map<String, Object> params);

}
