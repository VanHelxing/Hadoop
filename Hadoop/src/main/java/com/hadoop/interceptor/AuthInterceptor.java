package com.hadoop.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hadoop.core.AppContext;
import com.hadoop.core.LoginInfo;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private static Logger log = Logger.getLogger(AuthInterceptor.class);
	
	// 不需要验证权限的路径
	private static List<String> excludes = Arrays.asList(new String[] {
		"/login.do",
		"/logout.do",
		"/index.do"
	});

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean flag = false;
		String ctx = request.getContextPath();
		String uri = request.getRequestURI();
		uri = uri.substring(ctx.length());
		log.debug("请求路径：" + uri);
		if (excludes.contains(uri)) {
			flag = true;
		} else {
			String sessionId = request.getSession().getId();
			LoginInfo info = AppContext.getInstance().getLoginInfo(sessionId);
			if(info != null) {
				AppContext.getInstance().updateExpireTime(sessionId);
				AppContext.getInstance().setContext(info);
				flag = true;
			}
		}
		
		return flag;
	}
	
	
}
