package org.example.demo.ticket.consumer.impl.dao;

import org.example.demo.ticket.consumer.contract.dao.ProjetDao;
import org.example.demo.ticket.consumer.impl.rowmapper.projet.ProjetRM;
import org.example.demo.ticket.consumer.impl.rowmapper.ticket.EvolutionRM;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.sql.Types;
import java.util.List;

@Named
public class ProjetDaoImpl extends AbstractDaoImpl implements ProjetDao {
    @Override
    public Projet getProjet(Integer pId) {
        String vStrSQLProjets = "SELECT projet.id as pId," +
                                " projet.nom as pNom," +
                                " projet.date_creation as pDateCreation," +
                                " projet.cloture as pCloture," +
                                " utilisateur.id as uId," +
                                " utilisateur.nom as uNom," +
                                " utilisateur.prenom as uPrenom" +
                                " FROM projet, utilisateur " +
                                " WHERE projet.responsable_id = utilisateur.id";

        MapSqlParameterSource vParamsProjets = new MapSqlParameterSource();

        StringBuilder vSbSQLProjets = new StringBuilder(vStrSQLProjets);

        if (pId != null) {
            vSbSQLProjets.append(" AND projet.id = :pId");
            vParamsProjets.addValue("pId", pId);
        }

        NamedParameterJdbcTemplate vJdbcTemplateProjets = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Projet> vRowMapper = new ProjetRM();

        List<Projet> vListProjets =  vJdbcTemplateProjets.query(vSbSQLProjets.toString(), vParamsProjets, vRowMapper);

        if(vListProjets.size()>0){
            return vListProjets.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Projet> getListProjet() {
        String vStrSQLProjets = "SELECT projet.id as pId," +
                                " projet.nom as pNom," +
                                " projet.date_creation as pDateCreation," +
                                " projet.cloture as pCloture," +
                                " utilisateur.id as uId," +
                                " utilisateur.nom as uNom," +
                                " utilisateur.prenom as uPrenom" +
                                " FROM projet, utilisateur " +
                                " WHERE projet.responsable_id = utilisateur.id";

        MapSqlParameterSource vParamsProjets = new MapSqlParameterSource();

        StringBuilder vSbSQLProjets = new StringBuilder(vStrSQLProjets);

        NamedParameterJdbcTemplate vJdbcTemplateProjets = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Projet> vRowMapper = new ProjetRM();

        return  vJdbcTemplateProjets.query(vSbSQLProjets.toString(), vParamsProjets, vRowMapper);
    }
}