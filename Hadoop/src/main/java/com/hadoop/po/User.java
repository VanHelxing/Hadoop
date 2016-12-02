package com.hadoop.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "van_user")
public class User implements Serializable {

	private static final long serialVersionUID = -1187073755025127296L;
	
	
	/** 用户ID */
	private String userID;
	
	/** 用户姓名 */
	private String userName;
	
	/** 用户类型  (0:普通用户  1:管理员) */
	private String userType;

	@Id
	@Column(name = "user_id", length = 6)
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	@Column(name = "user_name", length = 50)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "user_type", length = 1)
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
