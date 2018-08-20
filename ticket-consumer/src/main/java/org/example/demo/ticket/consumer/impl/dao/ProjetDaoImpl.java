package org.example.demo.ticket.consumer.impl.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.demo.ticket.consumer.contract.dao.ProjetDao;
import org.example.demo.ticket.consumer.impl.rowmapper.projet.ProjetRM;
import org.example.demo.ticket.consumer.impl.rowmapper.ticket.EvolutionRM;
import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.sql.Types;
import java.util.List;

@Named
public class ProjetDaoImpl extends AbstractDaoImpl implements ProjetDao {

    private static final Log LOGGER = LogFactory.getLog(ProjetDaoImpl.class);

    @Override
    public Projet getProjet(Integer pId) throws NotFoundException {

        String vStrSQLProjets = "SELECT projet.id as pId," +
                                " projet.nom as pNom," +
                                " projet.date_creation as pDateCreation," +
                                " projet.cloture as pCloture," +
                                " projet.responsable_id as uId" +
                                " FROM public.projet";

        MapSqlParameterSource vParamsProjets = new MapSqlParameterSource();

        StringBuilder vSbSQLProjets = new StringBuilder(vStrSQLProjets);

        if (pId != null) {
            vSbSQLProjets.append(" WHERE projet.id = :pId");
            vParamsProjets.addValue("pId", pId);
        }

        NamedParameterJdbcTemplate vJdbcTemplateProjets = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Projet> vRowMapper = new ProjetRM();

        List<Projet> vListProjets =  vJdbcTemplateProjets.query(vSbSQLProjets.toString(), vParamsProjets, vRowMapper);

        if(vListProjets.size()>0){
            return vListProjets.get(0);
        }else{
            throw new NotFoundException("Le projet d'identifiant " + pId + " n'existe pas en base de données.");
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

    @Override
    public String insertVersion(Version pVersion) {
        String vSQL = "INSERT INTO public.version (projet_id, numero) VALUES (:projet_id, :numero)";
        NamedParameterJdbcTemplate vJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

        MapSqlParameterSource vParams = new MapSqlParameterSource();

        vParams.registerSqlType("projet_id",Types.INTEGER);
        vParams.registerSqlType("numero", Types.VARCHAR);

        vParams.addValue("projet_id", pVersion.getProjet().getId());
        vParams.addValue("numero", pVersion.getNumero());

        vJdbcTemplate.update(vSQL, vParams);
        return  "La version " + pVersion.getNumero() + " a été ajoutée pour le projet d'identifiant " + pVersion.getProjet().getId();
    }
}