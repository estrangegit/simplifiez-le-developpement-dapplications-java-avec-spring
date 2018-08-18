package org.example.demo.ticket.consumer.contract.dao;

import org.example.demo.ticket.model.bean.projet.Projet;

import java.util.List;

public interface ProjetDao {
    public Projet getProjet(Integer pId);
    public List<Projet> getListProjet();
}
