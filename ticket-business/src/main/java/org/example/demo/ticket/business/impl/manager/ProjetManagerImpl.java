package org.example.demo.ticket.business.impl.manager;

import org.example.demo.ticket.business.contract.manager.ProjetManager;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.exception.NotFoundException;

import javax.inject.Named;
import java.util.ArrayList;
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

    @Override
    public List<Projet> getListProjet() {
        return this.getDaoFactory().getProjetDao().getListProjet();
    }
}
