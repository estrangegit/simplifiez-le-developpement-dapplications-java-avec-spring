package org.example.demo.ticket.business.impl.manager;

import org.example.demo.ticket.business.contract.manager.TicketManager;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.ticket.Bug;
import org.example.demo.ticket.model.bean.ticket.Evolution;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class TicketManagerImpl extends AbstractManager implements TicketManager {
    @Override
    public Ticket getTicket(Long pNumero) throws NotFoundException {
        return getDaoFactory().getTicketDao().getTicket(pNumero);
    }


    @Override
    public List<Ticket> getListTicket(RechercheTicket pRechercheTicket) {
        return getDaoFactory().getTicketDao().getListTicket(pRechercheTicket);
    }

    @Override
    public int getCountTicket(RechercheTicket pRechercheTicket) {
        return getDaoFactory().getTicketDao().getCountTicket(pRechercheTicket);
    }

    @Override
    public List<TicketStatut> getListStatut() {
        return getDaoFactory().getTicketDao().getListStatut();
    }

    @Override
    public String updateTicketStatut(TicketStatut ticketStatut) {
        return getDaoFactory().getTicketDao().updateTicketStatut(ticketStatut);
    }

    @Override
    public void insertTicketStatut(TicketStatut pTicketStatut) {
        getDaoFactory().getTicketDao().insertTicketStatut(pTicketStatut);
    }
}
