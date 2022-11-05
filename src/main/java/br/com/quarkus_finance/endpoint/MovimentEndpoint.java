package br.com.quarkus_finance.endpoint;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.quarkus_finance.dao.all.DAOException;
import br.com.quarkus_finance.dao.moviment.MovimentDAO;
import br.com.quarkus_finance.dto.all.MovimentDTO;
import br.com.quarkus_finance.models.all.Moviment;

@Path("/moviment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovimentEndpoint {
	
	@Inject
	protected MovimentDAO movimentDAO; 
	
	@GET
    @Path("{id}")
    public Response find(@PathParam("id") Long idMoviment) {
		try {
    		MovimentDTO movimentDTO = movimentDAO.find(idMoviment);
        	return Response.ok(movimentDTO).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
    }
	
//	@GET
//	@Path("{id}/balance")
//	public Response balance(@PathParam("id") Long idAccount) {
//		try {
//			MovimentDTO movimentDTO = movimentDAO.balance(idAccount);
//			return Response.ok(movimentDTO).build();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
//		}
//	}

    @GET
    @Path("listAll")
    public Response listAll() {
    	return Response.ok(movimentDAO.listAll()).build();
    }
    
    @POST
    @Path("save")
    public Response save(@Valid MovimentDTO movimentDTO) {
    	try {
			movimentDAO.sendToJMSToSave(movimentDTO);
			return Response.ok().build();
		} catch (DAOException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessages()).build();
		}
    }
    
    @PUT
    @Path("save")
    public Response update(@Valid MovimentDTO movimentDTO) {
    	try {
    		movimentDAO.update(movimentDTO);
    		return Response.ok().build();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return Response.status(Status.BAD_REQUEST.getStatusCode()).entity(e.getMessage()).build();
    	}
    }
    
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long idMoviment) {
    	try {
    		movimentDAO.remove(Moviment.class, idMoviment);
        	return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
    }

}
