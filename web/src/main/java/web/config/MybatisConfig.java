package web.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("web.mapper")
public class MybatisConfig {

	@Bean
	DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUsername("root");
		ds.setPassword("moby3776");
		ds.setUrl("jdbc:mariadb://localhost:3306/test");
		ds.setDriverClassName("org.mariadb.jdbc.Driver");
		return ds;
	}
	
//	@Autowired
//	ApplicationContext applicationContext;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource d) throws Exception{
		SqlSessionFactoryBean  s = new SqlSessionFactoryBean();
		s.setDataSource(d);
		
        s.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:/mybatis-config.xml"));
        s.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/web/mapper/**/*Mapper.xml"));    
		return s.getObject();
	}
	
	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory s) {
		return new SqlSessionTemplate(s);
	}
}
