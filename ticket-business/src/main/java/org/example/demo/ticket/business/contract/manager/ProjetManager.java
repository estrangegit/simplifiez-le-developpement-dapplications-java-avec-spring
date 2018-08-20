package org.example.demo.ticket.business.contract.manager;

import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.exception.NotFoundException;

import java.util.List;

public interface ProjetManager {
    /**
     * Renvoie le projet demandé
     *
     * @param pId l'identifiant du projet
     * @return Le {@link Projet}
     * @throws NotFoundException lorsque le projet n'a pas été touvé
     */
    public Projet getProjet(Integer pId) throws NotFoundException;

    /**
     * Renvoie la liste des {@link Projet}
     *
     * @return Liste de projet présents en base de données
     */
    public List<Projet> getListProjet();

    /**
     *
     * @param pVersion
     */
    public String insertVersion(Version pVersion);
}
