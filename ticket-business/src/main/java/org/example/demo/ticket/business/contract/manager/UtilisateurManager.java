package org.example.demo.ticket.business.contract.manager;

import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.example.demo.ticket.model.exception.NotFoundException;

public interface UtilisateurManager {
    /**
     *
     * @param pId Identifiant de l'utilisateur
     * @return L'utilisateur recherché ou null si il n'existe pas
     * @throws NotFoundException lorsque l'utilisateur n'a pas été touvé
     */
    public Utilisateur getUtilisateur(int pId) throws NotFoundException;
}
