package de.homelabs.rfilesystems.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class CustomDispatcherServlet extends DispatcherServlet {

	private static final long serialVersionUID = 1L;

	
	public CustomDispatcherServlet() {
		super();
	}


	public CustomDispatcherServlet(WebApplicationContext webApplicationContext) {
		super(webApplicationContext);
	}


	@Override
	protected void noHandlerFound(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//super.noHandlerFound(request, response);
		response.getOutputStream().println("Fehler, Seite nicht gefunden!");
		//response.sendRedirect("index.html");
	}

	
}
