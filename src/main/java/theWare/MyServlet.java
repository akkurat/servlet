package theWare;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.view.WebappResourceLoader;





public class MyServlet extends HttpServlet{
	
	private VelocityEngine engine;
	

	@Override
	public void init( ServletConfig servletContext) throws ServletException {
		var props = new Properties();
		props.setProperty("resource.loader", "webapp" );
		props.setProperty("webapp.resource.loader.class", "org.apache.velocity.tools.view.WebappResourceLoader");
		props.setProperty("webapp.resource.loader.path", "/tmpl/");
		this.engine = new VelocityEngine(props);
		this.engine.setApplicationAttribute(ServletContext.class.getName(), servletContext.getServletContext());
		
//		var wa = new WebappResourceLoader();
		
//		
//		var cPath = servletContext.getServletContext().getRealPath("/tmpl");
//        engine.setProperty("resource.loader.file.path", cPath);
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var quark = req.getParameter("q");
		var context = new VelocityContext(Map.of("theVariable", "Eine Variable"));
		
		var template = engine.getTemplate("test.vm");
		template.merge(context,resp.getWriter());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		resp.getWriter().println(req.getPathInfo());
	}
	
}
