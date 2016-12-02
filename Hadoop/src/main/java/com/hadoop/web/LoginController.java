package com.hadoop.web;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hadoop.core.AppContext;
import com.hadoop.core.LoginInfo;
import com.hadoop.service.UserService;
import com.hadoop.util.DateUtil;

@Controller
public class LoginController {
	
	private Logger log = Logger.getLogger(LoginController.class);

	@Resource
	private UserService userService;
	
	
	@RequestMapping("/index")
	public String index() {
		return "login";
	}
	
	
	@RequestMapping("/login")
	public String login(@RequestParam("userName") String userName,
						@RequestParam("password") String password, HttpServletRequest request) {
		
		log.debug("userName : " + userName);
		
		boolean flag = userService.exist(userName, password);
		if (!flag) {
			return "forward:index.do";
		}
		
		LoginInfo info = new LoginInfo();
		info.setUserName(userName);
		info.setLoginTime(DateUtil.longFormat(new Date()));
		info.setHost(request.getRemoteHost());
		info.setIp(request.getRemoteAddr());
		info.setSessionId(request.getSession().getId());
		AppContext.getInstance().put(info.getSessionId(), info);
		request.setAttribute("loginInfo", info);
		
		return "main";
	}
}
