package org.example.demo.ticket.consumer.impl;

import org.example.demo.ticket.consumer.contract.DaoFactory;
import org.example.demo.ticket.consumer.contract.dao.ProjetDao;
import org.example.demo.ticket.consumer.contract.dao.TicketDao;

import javax.inject.Inject;
import javax.inject.Named;

@Named("DaoFactory")
public class DaoFactoryImpl implements DaoFactory {
    @Inject
    private ProjetDao projetDao;

    @Override
    public ProjetDao getProjetDao(){
        return projetDao;
    }

    @Override
    public void setProjetDao(ProjetDao projetManager){
        this.projetDao = projetDao;
    }

    @Inject
    private TicketDao ticketDao;

    @Override
    public TicketDao getTicketDao(){
        return ticketDao;
    }

    @Override
    public void setTicketDao(TicketDao ticketManager){
        this.ticketDao = ticketDao;
    }
}
