package com.hzwealth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/{user}/{pagename}")   //RESTFul方式
	public String page(@PathVariable("user") String user,@PathVariable("pagename")String pagename){
		return user+"/"+pagename;
	}
}
