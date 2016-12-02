package com.hadoop.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "van_ticket")
public class Ticket implements Serializable {

	private static final long serialVersionUID = -2514200408528125990L;
	
	/** 座位编号  */
	private int ticketID;
	
	/** 座位名称  */
	private String ticketName;
	
	/** 状态   0:有票   1：无票 */
	private String status;

	
	@Id
	@Column(name = "ticket_id", length = 5)
	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	@Column(name = "ticket_name", length = 20)
	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	@Column(name = "status", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
