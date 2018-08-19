package org.example.demo.ticket.business.impl.manager;

import org.example.demo.ticket.business.contract.manager.TicketManager;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.ticket.*;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

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

    @Override
    public String changerStatut(Ticket pTicket, TicketStatut pNewStatut) {
        TransactionTemplate vTransactionTemplate = new TransactionTemplate(getPlatformTransactionManager());

        return vTransactionTemplate.execute(new TransactionCallback<String>() {
            @Override
            public String doInTransaction(TransactionStatus status){
                return getDaoFactory().getTicketDao().changerStatut(pTicket, pNewStatut);
            }
        });

//          Exemple d'implémentation utilisant une lambda expression
//        return vTransactionTemplate.execute((status) -> getDaoFactory().getTicketDao().changerStatut(pTicket, pNewStatut));

    }
}
