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
		Tomcat t = new Tomcat();
		t.setBaseDir("/temp"); //c:\temp
		Connector c = new Connector();
		c.setPort(8080);
		t.setConnector(c);// tomcat 10은 이렇게 써야 된다. 바뀜. 9는 틀림.
		setContext(t);
		t.start();
		t.getServer().await();
	}

	/**
	 * maven achetype
	 * @param t
	 */
	private static void setContext(Tomcat t) {
		StandardContext c = (StandardContext) t.addWebapp("", new File("src/main/webapp/").getAbsolutePath());// ${project home}/src/main/webapp
		WebResourceRoot r = new StandardRoot(c);
		r.addPreResources(new DirResourceSet(r, "/WEB-INF/classes", new File("target/classes").getAbsolutePath(), "/"));
		c.setResources(r);
	}
}
