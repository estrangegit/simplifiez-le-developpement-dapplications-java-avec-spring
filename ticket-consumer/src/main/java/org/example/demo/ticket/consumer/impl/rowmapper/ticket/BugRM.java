package org.example.demo.ticket.consumer.impl.rowmapper.ticket;

import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.ticket.Bug;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BugRM implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
        Ticket vTicket = new Bug();
        vTicket.setNumero(rs.getLong("tNumero"));
        vTicket.setTitre(rs.getString("tTitre"));
        vTicket.setDate(rs.getDate("tDate"));
        vTicket.setDescription(rs.getString("tDescription"));

        Projet vProjet = new Projet();
        vProjet.setId(rs.getInt("pId"));
        vProjet.setNom(rs.getString("pNom"));
        vProjet.setDateCreation(rs.getDate("pDateCreation"));
        vProjet.setCloture(rs.getBoolean("pCloture"));

        vTicket.setProjet(vProjet);

        TicketStatut vTicketStatut = new TicketStatut();
        vTicketStatut.setId(rs.getInt("sId"));
        vTicketStatut.setLibelle(rs.getString("sLibelle"));

        vTicket.setStatut(vTicketStatut);

        return vTicket;
    }
}
