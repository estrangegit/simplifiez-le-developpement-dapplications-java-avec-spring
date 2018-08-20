package org.example.demo.ticket.consumer.contract.dao;

import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.example.demo.ticket.model.exception.NotFoundException;

public interface UtilisateurDao {
    public Utilisateur getUtilisateur(Integer pId) throws NotFoundException;
}
