package listeners;

import db.accessor.DBConnector;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import model.MenuItem;

/**
 *
 * @author tim
 */
public class RestaurantContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        String driverClassName = sc.getInitParameter("driverClassName");
        String url = sc.getInitParameter("url");
        String userName = sc.getInitParameter("userName");
        String password = sc.getInitParameter("password");
        // Create database connection from init parameters and set it to
        // context
        DBConnector dbManager = new DBConnector(driverClassName, url, 
                userName, password);
        sc.setAttribute("dbManager", dbManager);
        System.out.println("Database connection initialized for Application.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed.
         */
    }
}
