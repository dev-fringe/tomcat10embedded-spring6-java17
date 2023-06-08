package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("/index.do")
	public @ResponseBody String hello() {
		return "2323"; 
	}
	
	@RequestMapping("/index1.do")
	public @ResponseBody String hello1() {
		return "132312332"; 
	}
}