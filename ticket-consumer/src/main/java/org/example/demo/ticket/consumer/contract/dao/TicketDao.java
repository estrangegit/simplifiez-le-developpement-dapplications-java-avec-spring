package org.example.demo.ticket.consumer.contract.dao;

import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;

import java.util.List;

public interface TicketDao {
    public int getCountTicket(RechercheTicket pRechercheTicket);
    public List<TicketStatut> getListStatut();
    public String updateTicketStatut(TicketStatut pTicketStatut);
    public void insertTicketStatut(TicketStatut pTicketStatut);
    public Ticket getTicket(Long pNumero);
    public List<Ticket> getListTicket(RechercheTicket pRechercheTicket);
    public String changerStatut(Ticket pTicket, TicketStatut pTicketStatut);
}
