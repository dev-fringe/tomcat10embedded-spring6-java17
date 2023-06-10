package web;

import org.junit.Test;
import org.junit.runner.RunWith;import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import web.config.MybatisConfig;
import web.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisConfig.class)
public class TestJdbc {

	@Autowired TestService service;
	@Autowired SqlSessionTemplate sqlSessionTemplate;
	@Test
	public void test() {
		try {
			System.out.println(service.hello1());
//			System.out.println(sqlSessionTemplate.selectList("web.mapper.DummyMapper.select", null));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
