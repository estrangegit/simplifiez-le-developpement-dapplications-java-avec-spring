package org.example.demo.ticket.business.impl.manager;

import org.example.demo.ticket.business.contract.manager.ProjetManager;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.inject.Named;
import java.util.List;

@Named
public class ProjetManagerImpl extends AbstractManager implements ProjetManager {

    /**
     * Renvoie le projet demandé
     *
     * @param pId l'identifiant du projet
     * @return Le {@link Projet}
     * @throws NotFoundException lorsque le projet n'a pas été touvé
     */
    @Override
    public Projet getProjet(Integer pId) throws NotFoundException {
        return this.getDaoFactory().getProjetDao().getProjet(pId);
    }

    /**
     *
     * @return La liste des projets présents en base de données
     */
    @Override
    public List<Projet> getListProjet() {
        return this.getDaoFactory().getProjetDao().getListProjet();
    }

    /**
     *
     * @param pVersion
     */
    public String insertVersion(Version pVersion){
        TransactionStatus vTransactionStatus =
                getPlatformTransactionManager().getTransaction(new DefaultTransactionDefinition());

        String result;

        try{
            result = this.getDaoFactory().getProjetDao().insertVersion(pVersion);

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
