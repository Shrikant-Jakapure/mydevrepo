package listener;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import utils.DButil;
@WebListener
public class TestListener implements ServletContextListener {
    public TestListener() {
        // TODO Auto-generated constructor stub
    }
    public void contextInitialized(ServletContextEvent e)  {
    	System.out.println("contextInitialized");
        ServletContext context = e.getServletContext();
        
        Properties prop = new Properties( );
        prop.setProperty("Driver", context.getInitParameter("DRIVER"));
		prop.setProperty("Url", context.getInitParameter("URL"));
		prop.setProperty("User", context.getInitParameter("USER"));
		prop.setProperty("Password", context.getInitParameter("PASSWORD"));
		DButil.setProperties(prop);
    }
    public void contextDestroyed(ServletContextEvent arg0)  { 
    }
}
