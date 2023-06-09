package web.controller;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@Transactional
public class TestController {

	@Autowired SqlSessionTemplate sqlSessionTemplate;
	
	@RequestMapping("/index.do")
	public @ResponseBody String hello() {
		return "2323"; 
	}
	
	@RequestMapping("/index1.do")
	public @ResponseBody Object hello1() {
		return sqlSessionTemplate.selectList("web.mapper.DummyMapper.select", null);
	}
}