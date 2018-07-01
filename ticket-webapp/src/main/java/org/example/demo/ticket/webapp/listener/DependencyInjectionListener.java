package org.example.demo.ticket.webapp.listener;

import org.example.demo.ticket.business.manager.ManagerFactory;
import org.example.demo.ticket.business.manager.ProjetManager;
import org.example.demo.ticket.business.manager.TicketManager;
import org.example.demo.ticket.webapp.rest.resource.AbstractResource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyInjectionListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ManagerFactory vManagerFactory = new ManagerFactory();

        vManagerFactory.setProjetManager(new ProjetManager());
        vManagerFactory.setTicketManager(new TicketManager());

        AbstractResource.setManagerFactory(vManagerFactory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
