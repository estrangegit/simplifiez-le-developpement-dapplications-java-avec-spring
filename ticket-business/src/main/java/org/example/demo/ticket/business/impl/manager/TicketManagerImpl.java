package org.example.demo.ticket.business.impl.manager;

import org.example.demo.ticket.business.contract.manager.TicketManager;
import org.example.demo.ticket.model.bean.ticket.*;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.inject.Named;
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

        TransactionStatus vTransactionStatus =
                getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());

        String result;

        try{
            result =  getDaoFactory().getTicketDao().updateTicketStatut(ticketStatut);

            TransactionStatus vTScommit = vTransactionStatus;
            vTransactionStatus = null;
            getPlatformTransactionManager().commit(vTScommit);
        }finally {
            if(vTransactionStatus != null){
                getPlatformTransactionManager().rollback(vTransactionStatus);
            }
        }
        return result;
    }

    @Override
    public void insertTicketStatut(TicketStatut pTicketStatut) {

        TransactionStatus vTransactionStatus =
                getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());

        try{
            getDaoFactory().getTicketDao().insertTicketStatut(pTicketStatut);

            TransactionStatus vTScommit = vTransactionStatus;
            vTransactionStatus = null;
            getPlatformTransactionManager().commit(vTScommit);
        }finally {
            if(vTransactionStatus != null){
                getPlatformTransactionManager().rollback(vTransactionStatus);
            }
        }
    }

    @Override
    public String changerStatut(Ticket pTicket, TicketStatut pNewStatut) {
        TransactionStatus vTransactionStatus =
                getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());

        String result;

        try{
            result = getDaoFactory().getTicketDao().changerStatut(pTicket, pNewStatut);

            TransactionStatus vTScommit = vTransactionStatus;
            vTransactionStatus = null;
            getPlatformTransactionManager().commit(vTScommit);
        }finally {
            if(vTransactionStatus != null){
                getPlatformTransactionManager().rollback(vTransactionStatus);
            }
        }
        return result;
    }
}