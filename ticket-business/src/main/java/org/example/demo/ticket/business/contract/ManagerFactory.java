package org.example.demo.ticket.business.contract;

import org.example.demo.ticket.business.contract.manager.ProjetManager;
import org.example.demo.ticket.business.contract.manager.TicketManager;
import org.example.demo.ticket.business.contract.manager.UtilisateurManager;

import javax.inject.Named;

@Named("managerFactory")
public interface ManagerFactory {
    ProjetManager getProjectManager();
    TicketManager getTicketManager();
    UtilisateurManager getUtilisateurManager();
    void setProjetManager(ProjetManager projetManager);
    void setTicketManager(TicketManager ticketManager);
    void setUtilisateurManager(UtilisateurManager utilisateurManager);
}