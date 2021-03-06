package org.example.demo.ticket.webapp.rest.resource.projet;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.example.demo.ticket.model.bean.projet.Projet;
import org.example.demo.ticket.model.bean.projet.Version;
import org.example.demo.ticket.model.bean.utilisateur.Utilisateur;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.example.demo.ticket.webapp.rest.resource.AbstractResource;


/**
 * Ressource REST pour les {@link Projet}.
 *
 * @author lgu
 */
@Path("/projets")
@Produces(MediaType.APPLICATION_JSON)
public class ProjetResource extends AbstractResource {

    /**
     * Renvoie le {@link Projet} d'identifiant {@code pId}
     *
     * @param pId identifiant du {@link Projet}
     * @return Le {@link Projet}
     * @throws NotFoundException Si le {@link Projet} n'a pas été trouvé
     */
    @GET
    @Path("{id}")
    public Projet get(@PathParam("id") Integer pId) throws NotFoundException {
        Projet vProjet = getManagerFactory().getProjectManager().getProjet(pId);
        Utilisateur vUtilisateur = getManagerFactory().getUtilisateurManager().getUtilisateur(vProjet.getResponsable().getId());
        vProjet.setResponsable(vUtilisateur);
        return vProjet;
    }

    /**
     * Renvoie tous les {@link Projet}
     *
     * @return List
     */
    @GET
    public List<Projet> get() throws NotFoundException{
        List<Projet> vListProjet = getManagerFactory().getProjectManager().getListProjet();

        for(Projet vProjet: vListProjet){
            Utilisateur vUtilisateur = getManagerFactory().getUtilisateurManager().getUtilisateur(vProjet.getResponsable().getId());
            vProjet.setResponsable(vUtilisateur);
        }
        return vListProjet;
    }


    /**
     *
     * @param projet_id identifiant du projet pour lequel on souhaite ajouter une version
     * @param numero numéro de version que l'on souhaite ajouter
     * @throws NotFoundException Exception levée lorsque l'identifiant du projet ne correspond à aucun projet en base de données
     */
    @PUT
    @Path("/insertVersion/{projet_id}/{numero}")
    public String insertVersion(@PathParam("projet_id") Integer projet_id, @PathParam("numero") String numero) throws NotFoundException {
        Projet vProjet = getManagerFactory().getProjectManager().getProjet(projet_id);
        Version version = new Version();
        version.setProjet(vProjet);
        version.setNumero(numero);
        return getManagerFactory().getProjectManager().insertVersion(version);
    }
}
