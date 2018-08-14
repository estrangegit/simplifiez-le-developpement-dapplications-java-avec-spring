package org.example.demo.ticket.webapp.rest.resource.ticket;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.example.demo.ticket.model.bean.ticket.Ticket;
import org.example.demo.ticket.model.bean.ticket.TicketStatut;
import org.example.demo.ticket.model.exception.NotFoundException;
import org.example.demo.ticket.model.recherche.ticket.RechercheTicket;
import org.example.demo.ticket.webapp.rest.resource.AbstractResource;


/**
 * Ressource REST pour les {@link Ticket}.
 *
 * @author lgu
 */
@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
public class TicketResource extends AbstractResource {

    /**
     * Renvoie le {@link Ticket} de numéro {@code pNumero}
     *
     * @param pNumero numéro du {@link Ticket}
     * @return Le {@link Ticket}
     * @throws NotFoundException Si le {@link Ticket} n'a pas été trouvé
     */
    @GET
    @Path("{numero}")
    public Ticket get(@PathParam("numero") Long pNumero) throws NotFoundException {
        Ticket vTicket = getManagerFactory().getTicketManager().getTicket(pNumero);
        return vTicket;
    }

    /**
     * Recherche et renvoie les {@link Ticket} correspondant aux critères.
     *
     * @param pProjetId identifiant du {@link org.example.demo.ticket.model.bean.projet.Projet}
     * @return List
     */
    @GET
    @Path("search")
    public List<Ticket> search(@QueryParam("projetId") Integer pProjetId) {
        List<Ticket> vList = getManagerFactory().getTicketManager().getListTicket(new RechercheTicket()
                                                                    .setProjetId(pProjetId));
        return vList;
    }


    /**
     * Renvoie le nombre de tickets
     * @param pAuteurId
     * @param pProjetId
     * @return String
     */
    @GET
    @Path("count")
    public String getCountTicket(@QueryParam("auteurId") Integer pAuteurId, @QueryParam("projetId") Integer pProjetId) {
        return Integer.toString(getManagerFactory().getTicketManager().getCountTicket(new RechercheTicket().setProjetId(pProjetId).setAuteurId(pAuteurId)));
    }


    /**
     * Recherche et renvoie la liste des statuts disponibles pour les tickets
     * @return List
     */
    @GET
    @Path("ticketStatuts")
    public List<TicketStatut> getListStatut() {
        return getManagerFactory().getTicketManager().getListStatut();
    }

    @PUT
    @Path("updateTicketStatus/{id}/{libelle}")
    public String updateTicketStatut(@PathParam("id") Integer id, @PathParam("libelle") String libelle) {

        TicketStatut tckst = new TicketStatut(id);
        tckst.setLibelle(libelle);

        return getManagerFactory().getTicketManager().updateTicketStatut(tckst);
    }

    @POST
    @Path("insertTicketStatus/{id}/{libelle}")
    public void insertTicketStatut(@PathParam("id") Integer id, @PathParam("libelle") String libelle) {

        TicketStatut tckst = new TicketStatut(id);
        tckst.setLibelle(libelle);

        getManagerFactory().getTicketManager().insertTicketStatut(tckst);
    }
}
