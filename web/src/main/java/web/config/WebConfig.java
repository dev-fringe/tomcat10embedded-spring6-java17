package web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

@EnableWebMvc
@ComponentScan(basePackages = { "web.controller"})
@Import(MybatisConfig.class)
public class WebConfig implements WebApplicationInitializer {

	public void onStartup(ServletContext sc) throws ServletException {
		AnnotationConfigWebApplicationContext c = new AnnotationConfigWebApplicationContext();
		c.setServletContext(sc);
		c.register(WebConfig.class);
		c.refresh();
		DispatcherServlet dispatcherServlet = new DispatcherServlet(c);
		ServletRegistration.Dynamic app = sc.addServlet("dispatcher", dispatcherServlet);
		app.addMapping("*.do");
	}

}