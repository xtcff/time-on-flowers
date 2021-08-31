package com.shop.web.ListenerAndFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class CommonListener
 *
 */
@WebListener
public class CommonListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public CommonListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	ServletContext servletContext = sce.getServletContext();
    	String ctx = servletContext.getContextPath();
    	servletContext.setAttribute("ctx", ctx);
    	servletContext.setAttribute("res", ctx + "/resources");
    	servletContext.setAttribute("js", ctx + "/resources/js");
    	servletContext.setAttribute("comm", ctx + "/resources/common");
    }
	
}
