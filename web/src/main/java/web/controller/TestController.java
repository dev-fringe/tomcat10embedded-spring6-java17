package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.service.TestService;

@Controller
public class TestController {

	@Autowired
	TestService s;

	@RequestMapping("/index.do")
	public @ResponseBody String hello() {
		return "2323";
	}

	@RequestMapping("/index1.do")
	public @ResponseBody Object hello1() {
		return s.hello1();
	}
}