package org.example.demo.ticket.consumer.impl.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.demo.ticket.consumer.contract.dao.TicketDao;
import org.example.demo.ticket.consumer.impl.rowmapper.ticket.BugRM;
import org.example.demo.ticket.consumer.impl.rowmapper.ticket.EvolutionRM;
import org.example.demo.ticket.consumer.impl.rowmapper.ticket.TicketStatutRM;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Named
public class TicketDaoImpl extends AbstractDaoImpl implements TicketDao {

    private static final Log LOGGER = LogFactory.getLog(TicketDaoImpl.class);

    @Override
    public int getCountTicket(RechercheTicket pRechercheTicket) {
        MapSqlParameterSource vParams = new MapSqlParameterSource();

        StringBuilder vSQL = new StringBuilder("SELECT COUNT(*) FROM ticket WHERE 1=1");

        if (pRechercheTicket != null) {
            if (pRechercheTicket.getAuteurId() != null) {
                vSQL.append(" AND auteur_id = :auteur_id");
                vParams.addValue("auteur_id", pRechercheTicket.getAuteurId());
            }
            if (pRechercheTicket.getProjetId() != null) {
                vSQL.append(" AND projet_id = :projet_id");
                vParams.addValue("projet_id", pRechercheTicket.getProjetId());
            }
        }

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
        int vNbrTicket = vJdbcTemplate.queryForObject(vSQL.toString(), vParams, Integer.class);

        return vNbrTicket;
    }

    @Override
    public List<TicketStatut> getListStatut(){
        String vSQL = "SELECT * FROM public.statut";
        JdbcTemplate vJdbcTemplate = new JdbcTemplate(getDataSource());
        RowMapper<TicketStatut> vRowMapper = new TicketStatutRM();
        List<TicketStatut> vListStatut = vJdbcTemplate.query(vSQL, vRowMapper);
        return vListStatut;
    }

    @Override
    public String updateTicketStatut(TicketStatut pTicketStatut) {
        String vSQL = "UPDATE statut SET libelle = :libelle WHERE id = :id";

        BeanPropertySqlParameterSource vParams = new BeanPropertySqlParameterSource(pTicketStatut);
        vParams.registerSqlType("id", Types.INTEGER);
        vParams.registerSqlType("libelle", Types.VARCHAR);

        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

        int vNbrLigneMaJ = vJdbcTemplate.update(vSQL, vParams);

        return "Mise à jour de " + vNbrLigneMaJ + " lignes.";
    }

    @Override
    public void insertTicketStatut(TicketStatut pTicketStatut) {
        String vSQL = "INSERT INTO statut (id, libelle) VALUES (:id, :libelle)";
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

        BeanPropertySqlParameterSource vParams = new BeanPropertySqlParameterSource(pTicketStatut);
        vParams.registerSqlType("id", Types.INTEGER);
        vParams.registerSqlType("libelle", Types.VARCHAR);

        try {
            vJdbcTemplate.update(vSQL, vParams);
        } catch (DuplicateKeyException vEx) {
            LOGGER.error("Le TicketStatut existe déjà ! id=" + pTicketStatut.getId(), vEx);
            System.out.println("Le TicketStatut existe déjà ! id=" + pTicketStatut.getId());
        }
    }

    @Override
    public Ticket getTicket(Long pNumero) {

        /*Récupération des tickets qui sont des évolutions*/
        String vStrSQLEvo = "SELECT ticket.numero as tNumero," +
                            " ticket.titre as tTitre," +
                            " ticket.date as tDate," +
                            " ticket.description as tDescription," +
                            " projet.id as pId," +
                            " projet.nom as pNom," +
                            " projet.date_creation as pDateCreation," +
                            " projet.cloture as pCloture," +
                            " statut.id as sId," +
                            " statut.libelle as sLibelle" +
                            " FROM ticket, evolution, statut, projet" +
                            " WHERE ticket.numero = evolution.ticket_numero" +
                            " AND ticket.projet_id = projet.id " +
                            " AND ticket.statut_actuel_id = statut.id";

        MapSqlParameterSource vParamsEvo = new MapSqlParameterSource();

        StringBuilder vSbSQLEvo = new StringBuilder(vStrSQLEvo);

        if (pNumero != null) {
            vSbSQLEvo.append(" AND ticket.numero = :tNumero");
            vParamsEvo.addValue("tNumero", pNumero);
        }

        NamedParameterJdbcTemplate vJdbcTemplateEvo = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Ticket> vRowMapper = new EvolutionRM();

        List<Ticket> vListTicketsEvo =  vJdbcTemplateEvo.query(vSbSQLEvo.toString(), vParamsEvo, vRowMapper);

        /* Récupération des tickets qui sont des bugs */
        String vStrSQLBug = "SELECT ticket.numero as tNumero," +
                            " ticket.titre as tTitre," +
                            " ticket.date as tDate," +
                            " ticket.description as tDescription," +
                            " projet.id as pId," +
                            " projet.nom as pNom," +
                            " projet.date_creation as pDateCreation," +
                            " projet.cloture as pCloture," +
                            " statut.id as sId," +
                            " statut.libelle as sLibelle" +
                            " FROM ticket, bug, statut, projet" +
                            " WHERE ticket.numero = bug.ticket_numero" +
                            " AND ticket.projet_id = projet.id " +
                            " AND ticket.statut_actuel_id = statut.id";

        MapSqlParameterSource vParamsBug = new MapSqlParameterSource();

        StringBuilder vSbSQLBug = new StringBuilder(vStrSQLBug);

        if (pNumero != null) {
            vSbSQLEvo.append(" AND ticket.numero = :tNumero");
            vParamsBug.addValue("tNumero", pNumero);
        }

        NamedParameterJdbcTemplate vJdbcTemplateBug = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Ticket> vRowMapperBug = new BugRM();

        List<Ticket> vListTicketsBug =  vJdbcTemplateBug.query(vSbSQLBug.toString(), vParamsBug, vRowMapper);


        List<Ticket> vListTickets = new ArrayList<Ticket>();
        vListTickets.addAll(vListTicketsEvo);
        vListTickets.addAll(vListTicketsBug);

        if(vListTickets.size()>0){
            return vListTickets.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Ticket> getListTicket(RechercheTicket pRechercheTicket) {

        System.out.println(pRechercheTicket.getProjetId());
        System.out.println(pRechercheTicket.getAuteurId());

        /*Récupération des tickets qui sont des évolutions*/
        String vStrSQLEvo = "SELECT ticket.numero as tNumero," +
                " ticket.titre as tTitre," +
                " ticket.date as tDate," +
                " ticket.description as tDescription," +
                " projet.id as pId," +
                " projet.nom as pNom," +
                " projet.date_creation as pDateCreation," +
                " projet.cloture as pCloture," +
                " statut.id as sId," +
                " statut.libelle as sLibelle" +
                " FROM ticket, evolution, statut, projet" +
                " WHERE ticket.numero = evolution.ticket_numero" +
                " AND ticket.projet_id = projet.id " +
                " AND ticket.statut_actuel_id = statut.id";

        MapSqlParameterSource vParamsEvo = new MapSqlParameterSource();

        StringBuilder vSbSQLEvo = new StringBuilder(vStrSQLEvo);

        if (pRechercheTicket != null) {
            if (pRechercheTicket.getAuteurId() != null) {
                vSbSQLEvo.append(" AND auteur_id = :auteur_id");
                vParamsEvo.addValue("auteur_id", pRechercheTicket.getAuteurId());
            }
            if (pRechercheTicket.getProjetId() != null) {
                vSbSQLEvo.append(" AND projet_id = :projet_id");
                vParamsEvo.addValue("projet_id", pRechercheTicket.getProjetId());
            }
        }

        NamedParameterJdbcTemplate vJdbcTemplateEvo = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Ticket> vRowMapper = new EvolutionRM();

        List<Ticket> vListTicketsEvo =  vJdbcTemplateEvo.query(vSbSQLEvo.toString(), vParamsEvo, vRowMapper);

        /* Récupération des tickets qui sont des bugs */
        String vStrSQLBug = "SELECT ticket.numero as tNumero," +
                " ticket.titre as tTitre," +
                " ticket.date as tDate," +
                " ticket.description as tDescription," +
                " projet.id as pId," +
                " projet.nom as pNom," +
                " projet.date_creation as pDateCreation," +
                " projet.cloture as pCloture," +
                " statut.id as sId," +
                " statut.libelle as sLibelle" +
                " FROM ticket, bug, statut, projet" +
                " WHERE ticket.numero = bug.ticket_numero" +
                " AND ticket.projet_id = projet.id " +
                " AND ticket.statut_actuel_id = statut.id";

        MapSqlParameterSource vParamsBug = new MapSqlParameterSource();

        StringBuilder vSbSQLBug = new StringBuilder(vStrSQLBug);

        if (pRechercheTicket != null) {
            if (pRechercheTicket.getAuteurId() != null) {
                vSbSQLBug.append(" AND auteur_id = :auteur_id");
                vParamsBug.addValue("auteur_id", pRechercheTicket.getAuteurId());
            }
            if (pRechercheTicket.getProjetId() != null) {
                vSbSQLBug.append(" AND projet_id = :projet_id");
                vParamsBug.addValue("projet_id", pRechercheTicket.getProjetId());
            }
        }

        NamedParameterJdbcTemplate vJdbcTemplateBug = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Ticket> vRowMapperBug = new BugRM();

        List<Ticket> vListTicketsBug =  vJdbcTemplateBug.query(vSbSQLBug.toString(), vParamsBug, vRowMapper);


        List<Ticket> vListTickets = new ArrayList<Ticket>();
        vListTickets.addAll(vListTicketsEvo);
        vListTickets.addAll(vListTicketsBug);

        return vListTickets;
    }

    @Override
    public String changerStatut(Ticket pTicket, TicketStatut pTicketStatut) {
        int vNbrLigneMaJ = 0;

        Stream<TicketStatut> ticketStatutStream = this.getListStatut().stream();

        Optional<TicketStatut> optionalTicketStatut = ticketStatutStream
                .filter(ticketStatut -> ticketStatut.getId() == pTicketStatut.getId())
                .findFirst();

        if(optionalTicketStatut.isPresent()){
            String vSQL = "UPDATE ticket" +
                    " SET statut_actuel_id = :statut_actuel_id" +
                    " WHERE numero = :numero";

            MapSqlParameterSource vParams = new MapSqlParameterSource();

            StringBuilder vSbSQL = new StringBuilder(vSQL);

            vParams.addValue("statut_actuel_id", pTicketStatut.getId());
            vParams.addValue("numero", pTicket.getNumero());

            NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

            vNbrLigneMaJ = vJdbcTemplate.update(vSQL, vParams);
        }

        return "Mise à jour de " + vNbrLigneMaJ + " lignes.";
    }
}