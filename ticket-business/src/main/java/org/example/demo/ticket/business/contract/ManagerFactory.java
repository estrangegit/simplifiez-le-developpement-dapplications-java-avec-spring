package org.example.demo.ticket.business.contract;

import org.example.demo.ticket.business.contract.manager.ProjetManager;
import org.example.demo.ticket.business.contract.manager.TicketManager;

import javax.inject.Named;

@Named("managerFactory")
public interface ManagerFactory {
    ProjetManager getProjectManager();
    TicketManager getTicketManager();
    void setProjetManager(ProjetManager projetManager);
    void setTicketManager(TicketManager ticketManager);
}