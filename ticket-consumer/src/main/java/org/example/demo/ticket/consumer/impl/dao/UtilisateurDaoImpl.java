package org.example.demo.ticket.consumer.impl.dao;

import org.example.demo.ticket.consumer.contract.dao.UtilisateurDao;
import org.example.demo.ticket.consumer.impl.rowmapper.utilisateur.UtilisateurRM;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.inject.Named;
import java.util.List;

@Named
public class UtilisateurDaoImpl extends AbstractDaoImpl implements UtilisateurDao {
    @Override
    public Utilisateur getUtilisateur(Integer pId) throws NotFoundException{
        String vStrSQLUtilisateurs = "SELECT * FROM public.utilisateur";

        MapSqlParameterSource vParamsProjets = new MapSqlParameterSource();

        StringBuilder vSbSQLUtilisateurs = new StringBuilder(vStrSQLUtilisateurs);

        if (pId != null) {
            vSbSQLUtilisateurs.append(" WHERE utilisateur.id = :pId");
            vParamsProjets.addValue("pId", pId);
        }

        NamedParameterJdbcTemplate vJdbcTemplateProjets = new NamedParameterJdbcTemplate(getDataSource());
        RowMapper<Utilisateur> vRowMapper = new UtilisateurRM();

        List<Utilisateur> vListUtilisateurs =  vJdbcTemplateProjets.query(vSbSQLUtilisateurs.toString(), vParamsProjets, vRowMapper);

        if(vListUtilisateurs.size()>0){
            return vListUtilisateurs.get(0);
        }else{
            throw new NotFoundException("L'utilisateur d'identifiant " + pId + " n'existe pas en base de données.");
        }
    }
}
