package com.theduke.bronze.boundary;

import com.theduke.bronze.business.entity.Gruppe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author Christoph
 */
@Path("gruppe")
public class GruppeResource {
    
    @PersistenceContext
    private EntityManager em;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Gruppe> list() {
        return em.createQuery("select g from Gruppe g", Gruppe.class)
                .getResultList();
    }
        
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Gruppe get(@PathParam("id") int id) {
        return em.find(Gruppe.class, id);
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Gruppe update(Gruppe gruppe) {
        return em.merge(gruppe);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Gruppe gruppe, @Context UriInfo uriInfo) {
        em.persist(gruppe);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(gruppe.getId()));
        return Response.created(builder.build()).build();
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") Long id) {
        Gruppe gruppe = em.find(Gruppe.class, id);
        if (gruppe != null) {
            em.remove(gruppe);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    
}
