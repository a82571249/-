package com.ge.Demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ge.impl.GetBaiduDataImpl;

@RestController
@RequestMapping("/GetBaiduData")
public class GetBaiduDataController {


	@SuppressWarnings("static-access")
	@RequestMapping("/getdata")
	public String getdata() {
		GetBaiduDataImpl getBaiduDataImpl=new GetBaiduDataImpl();
		String finaldata=null;
		finaldata=getBaiduDataImpl.getdata("http://www.baidu.com");
		return finaldata;
	}
}
