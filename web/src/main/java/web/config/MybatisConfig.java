package web.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.hibernate.SessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("web.mapper")
@ComponentScan(basePackages = {"web.service"})
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
	
//	@Bean(name = "userTransaction")
//	public UserTransaction userTransaction() throws Throwable {
//		UserTransactionImp userTransactionImp = new UserTransactionImp();
////		userTransactionImp.setTransactionTimeout(10);
//		return userTransactionImp;
//	}

//	@Bean(name = "atomikosTransactionManager", initMethod = "init", destroyMethod = "close")
//	public TransactionManager atomikosTransactionManager() throws Throwable {
//		UserTransactionManager userTransactionManager = new UserTransactionManager();
//		userTransactionManager.setForceShutdown(false);
//
//		AtomikosJtaPlatform.transactionManager = userTransactionManager;
//
//		return userTransactionManager;
//	}

//	@Bean(name = "transactionManager")
//	@DependsOn({ "userTransaction", "atomikosTransactionManager" })
//	public PlatformTransactionManager transactionManager() throws Throwable {
//		UserTransaction userTransaction = userTransaction();
//
//		AtomikosJtaPlatform.transaction = userTransaction;
//
//		TransactionManager atomikosTransactionManager = atomikosTransactionManager();
//		return new JtaTransactionManager(userTransaction, atomikosTransactionManager);
//	}
//	
//	  @Bean
//	  public JtaTransactionManager transactionManager() {
//	    return new JtaTransactionManager();
//	  }
	 
//	  @Bean
//	  public JtaTransactionManager mybatisManager() {
//	    return new JtaTransactionManagerFactoryBean().getObject();
//	  }

//    @Bean
//    public PlatformTransactionManager transactionManager() {
//
//        JpaTransactionManager txManager = new JpaTransactionManager();
//        txManager.setEntityManagerFactory(entityManagerFactory());
//        return txManager;
//    }
    
//	  @Bean
//	  public DataSourceTransactionManager transactionManager() {
//	    return new DataSourceTransactionManager(dataSource());
//	  }
//	
	

	@Bean
    @Autowired
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
	
//    @Bean(name = "chainedTransactionManager")
//    public ChainedTransactionManager chainedTransactionManager(JpaTransactionManager jpaTransactionManager,
//    		HibernateTransactionManager hibernateTransactionManager) {
//        return new ChainedTransactionManager(hibernateTransactionManager, jpaTransactionManager);
//    }
//	
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
	
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "web.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MariaDB106Dialect");
        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;        
    }
    
}
