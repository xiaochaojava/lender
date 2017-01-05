package com.hzwealth.controller;

import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {
	@InitBinder		//绑定一个自定义的日期格式转换，如果自己配置了格式转换，默认的格式转换失效。
	public void InitBinder (ServletRequestDataBinder binder){
		binder.registerCustomEditor(
			java.util.Date.class, 
			new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
}
