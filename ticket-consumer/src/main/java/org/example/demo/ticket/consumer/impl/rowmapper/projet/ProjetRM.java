package org.example.demo.ticket.consumer.impl.rowmapper.projet;

import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjetRM implements RowMapper<Projet> {
    public Projet mapRow(ResultSet pRS, int pRowNum) throws SQLException {
        Projet vProjet = new Projet();

        vProjet.setId(pRS.getInt("pId"));
        vProjet.setNom(pRS.getString("pNom"));
        vProjet.setDateCreation(pRS.getDate("pDateCreation"));
        vProjet.setCloture(pRS.getBoolean("pCloture"));

        Utilisateur vRespondable = new Utilisateur();
        vRespondable.setId(pRS.getInt("uId"));
        vRespondable.setNom(pRS.getString("uNom"));
        vRespondable.setPrenom(pRS.getString("uPrenom"));

        vProjet.setResponsable(vRespondable);

        return vProjet;
    }
}
