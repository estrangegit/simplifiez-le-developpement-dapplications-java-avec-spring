package org.example.demo.ticket.business.manager;

public class ManagerFactory {

    private ProjetManager projetManager;
    private TicketManager ticketManager;

    public ProjetManager getProjectManager(){
        return projetManager;
    }

    public TicketManager getTicketManager(){
        return ticketManager;
    }

    public void setProjetManager(ProjetManager projetManager){
        this.projetManager = projetManager;
    }

    public void setTicketManager(TicketManager ticketManager){
        this.ticketManager = ticketManager;
    }
}
