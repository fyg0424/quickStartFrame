package com.QuickStartFrame.management.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.QuickStartFrame.management.exception.AuthorizationException;
import com.QuickStartFrame.management.util.Constant;
public class SecurityInterceptor implements HandlerInterceptor{
	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);
	private static final String[] excludeUrls = {"logout","login","index","welcome"};

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestUrl = request.getRequestURI();
		if(logger.isDebugEnabled()){
			logger.debug("验证请求:[" + requestUrl + "]!" );
		}
		
		HttpSession session  = request.getSession();
		if(session.getAttribute(Constant.SESSION_USER) == null) {
			if(logger.isDebugEnabled()){
				logger.debug("拦截非法请求:[" + requestUrl + "]!" );
			}
			throw new AuthorizationException();
		} else {
			//不做拦截的请求
			for (String  excludeUrl: excludeUrls) {
				if(requestUrl.indexOf(excludeUrl)!=-1){
					return true;
				}
			}
			return true;
		}
	}
	

}
