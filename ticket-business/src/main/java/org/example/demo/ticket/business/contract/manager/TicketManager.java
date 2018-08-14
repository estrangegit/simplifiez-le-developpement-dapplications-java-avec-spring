package org.example.demo.ticket.business.contract.manager;

import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;

import java.util.List;

public interface TicketManager {
    /**
     * Cherche et renvoie le {@link Ticket} numéro {@code pNumero}
     *
     * @param pNumero le numéro du Ticket
     * @return Le {@link Ticket}
     * @throws NotFoundException Si le Ticket n'est pas trouvé
     */
    Ticket getTicket(Long pNumero) throws NotFoundException;

    /**
     * Renvoie la liste des {@link Ticket} correspondants aux critères de recherche.
     *
     * @param pRechercheTicket -
     * @return List
     */
    List<Ticket> getListTicket(RechercheTicket pRechercheTicket);

    /**
     * Renvoie le nombre de {@link Ticket} correspondants aux critères de recherche.
     *
     * @return int
     */
    int getCountTicket(RechercheTicket pRechercheTicket);

    /**
     * Renvoie la liste des statuts disponibles pour les tickets
     * @return List
     */
    List<TicketStatut> getListStatut();

    /**
     * Permet de mettre à jour une entité TicketStatut
     * @param ticketStatut
     */
    String updateTicketStatut(TicketStatut ticketStatut);

    /**
     * Permet l'insertion d'un nouveau TicketStatut en base de données
     * @param pTicketStatut
     */
    void insertTicketStatut(TicketStatut pTicketStatut);
}
