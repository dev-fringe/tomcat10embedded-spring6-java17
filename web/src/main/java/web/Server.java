package web;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import lombok.SneakyThrows;

public class Server {

	@SneakyThrows
	public static void main(String[] args) {
		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir("temp");
		Connector conn = new Connector();
		conn.setPort(8080);
		tomcat.setConnector(conn);
		StandardContext ctx = (StandardContext) tomcat.addWebapp("", new File("src/main/webapp/").getAbsolutePath());
		WebResourceRoot resources = new StandardRoot(ctx);
		resources.addPreResources(
				new DirResourceSet(resources, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(), "/"));
		ctx.setResources(resources);
		tomcat.start();
		tomcat.getServer().await();
	}
}
