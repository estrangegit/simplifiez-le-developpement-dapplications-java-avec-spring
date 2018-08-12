package org.example.demo.ticket.consumer.contract;

import org.example.demo.ticket.consumer.contract.dao.ProjetDao;
import org.example.demo.ticket.consumer.contract.dao.TicketDao;

public interface DaoFactory {
    TicketDao getTicketDao();
    ProjetDao getProjetDao();
    void setTicketDao(TicketDao ticketDao);
    void setProjetDao(ProjetDao projetDao);
}
