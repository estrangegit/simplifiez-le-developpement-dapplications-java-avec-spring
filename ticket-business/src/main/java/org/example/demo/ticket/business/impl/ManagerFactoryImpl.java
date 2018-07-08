package org.example.demo.ticket.business.impl;

import org.example.demo.ticket.business.contract.ManagerFactory;
import org.example.demo.ticket.business.contract.manager.ProjetManager;
import org.example.demo.ticket.business.contract.manager.TicketManager;

public class ManagerFactoryImpl implements ManagerFactory{

    private ProjetManager projetManager;
    private TicketManager ticketManager;

    @Override
    public ProjetManager getProjectManager(){
        return projetManager;
    }

    @Override
    public TicketManager getTicketManager(){
        return ticketManager;
    }

    @Override
    public void setProjetManager(ProjetManager projetManager){
        this.projetManager = projetManager;
    }

    @Override
    public void setTicketManager(TicketManager ticketManager){
        this.ticketManager = ticketManager;
    }
}
