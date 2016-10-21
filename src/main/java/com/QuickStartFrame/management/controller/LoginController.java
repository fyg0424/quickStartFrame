package com.QuickStartFrame.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.QuickStartFrame.management.pojo.User;
import com.QuickStartFrame.management.service.UserService;
import com.QuickStartFrame.management.util.Constant;
import com.alibaba.fastjson.JSONObject;
@Controller
public class LoginController {
	private static final String LOGIN_PAGE = "login";
	
	private static final String INDEX_PAGE = "index";
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/login.ajax", method = RequestMethod.POST)
	public Object Login(String loginName,String password,HttpServletRequest request, HttpServletResponse response){
		logger.info("MD5密码："+password);
		logger.info("验证用户信息");
		JSONObject result = new JSONObject();
		User user = userService.queryUserByLoginName(loginName);
		if(user != null){
			if(user.getPassword().equals(password)){
				request.getSession().setAttribute(Constant.SESSION_USER, user);
				result.put(Constant.KEY_STATUS, Constant.STATUS_SUCC);
			}
			else{
				result.put(Constant.KEY_STATUS, Constant.STATUS_FAIL);
				result.put(Constant.KEY_MSG, "password is error!");
			}
		}
		else{
			result.put(Constant.KEY_STATUS, Constant.STATUS_FAIL);
			result.put(Constant.KEY_MSG, "user is no exist!");
		}
		return result;
	}
	
	@RequestMapping(value = {"/", "/index", "index.html", "index.html", "index.jsp"}, method = RequestMethod.GET) 
	public String index(HttpServletRequest request, HttpServletResponse response) {
		String page = login(request, response);
		return page;
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		User user = (User)request.getSession().getAttribute(Constant.SESSION_USER);
		if(user != null) {
			logger.info("跳转首页");
			return INDEX_PAGE;
		} 
		logger.info("跳转登录页面");
		return LOGIN_PAGE;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		//撤销session
		request.getSession().removeAttribute(Constant.SESSION_USER);
		
		return "redirect:/login";
	}
	

}
