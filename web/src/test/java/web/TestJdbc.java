package web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import web.config.MybatisConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MybatisConfig.class)
public class TestJdbc {

	@Autowired SqlSessionTemplate sqlSession;
	
	@Test
	public void test() {
		System.out.println(sqlSession);
		System.out.println(sqlSession.selectList("web.mapper.DummyMapper.select", null));
	}
}
