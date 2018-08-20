package org.example.demo.ticket.business.impl.manager;

import org.example.demo.ticket.business.contract.manager.UtilisateurManager;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.example.demo.ticket.model.exception.NotFoundException;

import javax.inject.Named;

@Named
public class UtilisateurManagerImpl extends AbstractManager implements UtilisateurManager {

    /**
     *
     * @param pId Identifiant de l'utilisateur
     * @return L'utilisateur recherché ou null si il n'existe pas
     * @throws NotFoundException lorsque l'utilisateur n'a pas été touvé
     */
    @Override
    public Utilisateur getUtilisateur(int pId) throws NotFoundException {
        return getDaoFactory().getUtilisateurDao().getUtilisateur(pId);
    }

}
