package web.service;

import org.hibernate.SessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.model.Aa;

@Service
@Transactional
public class TestService {

	@Autowired SqlSessionTemplate sqlSessionTemplate;
	@Autowired SessionFactory sessionFactory;

	public Object hello1() {
		sessionFactory.getCurrentSession().persist(new Aa("ㅇㅇ"));
		return sqlSessionTemplate.selectList("web.mapper.DummyMapper.select", null);
	}
}
