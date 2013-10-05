/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import model.MenuService;

/**
 *
 * @author tim
 */
public class RestauranServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
//        ServletContext sc = event.getServletContext();
//
//        String dogBreed = sc.getInitParameter("breed");
//        Dog d = new Dog(dogBreed);
//        sc.setAttribute("dog", d);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // nothing to do here
    }
}
