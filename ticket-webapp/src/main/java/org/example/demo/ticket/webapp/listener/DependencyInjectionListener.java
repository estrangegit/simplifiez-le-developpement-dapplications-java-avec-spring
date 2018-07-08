package org.example.demo.ticket.webapp.listener;

import org.example.demo.ticket.business.impl.ManagerFactoryImpl;
import org.example.demo.ticket.business.impl.manager.ProjetManagerImpl;
import org.example.demo.ticket.business.impl.manager.TicketManagerImpl;
import org.example.demo.ticket.webapp.rest.resource.AbstractResource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DependencyInjectionListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ManagerFactoryImpl vManagerFactoryImpl = new ManagerFactoryImpl();

        vManagerFactoryImpl.setProjetManager(new ProjetManagerImpl());
        vManagerFactoryImpl.setTicketManager(new TicketManagerImpl());

        AbstractResource.setManagerFactory(vManagerFactoryImpl);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
