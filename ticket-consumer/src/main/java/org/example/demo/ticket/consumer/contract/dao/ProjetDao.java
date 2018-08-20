package org.example.demo.ticket.consumer.contract.dao;

import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.exception.NotFoundException;

import java.util.List;

public interface ProjetDao {
    public Projet getProjet(Integer pId) throws NotFoundException;
    public List<Projet> getListProjet();
    public String insertVersion(Version pVersion);
}
