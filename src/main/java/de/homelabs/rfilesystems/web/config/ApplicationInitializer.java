package de.homelabs.rfilesystems.web.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import de.homelabs.rfilesystems.web.servlet.CustomDispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {
	
	public void onStartup(ServletContext servletContext)
			throws ServletException {

		AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
		root.setServletContext(servletContext);
		root.scan("de.homelabs");
		//root.refresh();

		Dynamic servlet = servletContext.addServlet("websandbox",
				new CustomDispatcherServlet(root));
		
//		Dynamic servlet = servletContext.addServlet("websandbox",
//				new DispatcherServlet(root));
		servlet.setLoadOnStartup(1);
	
		//Default
		servlet.addMapping("/");
		
		//filter
		//FilterChainProxy websandboxFCP = (FilterChainProxy) root.getBean("springSecurityFilterChain");
		//servletContext.addFilter("springSecurityFilterChain", websandboxFCP);
		
		//context listener
		ContextLoaderListener ctxListener = new ContextLoaderListener(root);
		servletContext.addListener(ctxListener);
		
		//filter neu
//		FilterRegistration.Dynamic springFilter = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
//        springFilter.addMappingForUrlPatterns(null, true, "/*");
	}
}
