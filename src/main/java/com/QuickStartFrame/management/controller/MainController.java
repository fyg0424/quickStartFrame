package com.QuickStartFrame.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/main")
public class MainController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "charts";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome() {
		return "main/welcome";
	}
}
