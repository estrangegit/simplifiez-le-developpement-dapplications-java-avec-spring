package org.example.demo.ticket.business.impl;

import org.example.demo.ticket.business.contract.ManagerFactory;
import org.example.demo.ticket.business.contract.manager.ProjetManager;
import org.example.demo.ticket.business.contract.manager.TicketManager;

import javax.inject.Inject;
import javax.inject.Named;

@Named("managerFactory")
public class ManagerFactoryImpl implements ManagerFactory{

    @Inject
    private ProjetManager projetManager;

    @Override
    public ProjetManager getProjectManager(){
        return projetManager;
    }

    @Override
    public void setProjetManager(ProjetManager projetManager){
        this.projetManager = projetManager;
    }

    @Inject
    private TicketManager ticketManager;

    @Override
    public TicketManager getTicketManager(){
        return ticketManager;
    }

    @Override
    public void setTicketManager(TicketManager ticketManager){
        this.ticketManager = ticketManager;
    }
}
