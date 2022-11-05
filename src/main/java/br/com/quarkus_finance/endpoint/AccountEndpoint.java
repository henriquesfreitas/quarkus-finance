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

import br.com.quarkus_finance.dao.all.AccountDAO;
import br.com.quarkus_finance.dao.all.DAOException;
import br.com.quarkus_finance.dto.all.AccountDTO;
import br.com.quarkus_finance.models.all.Account;

@Path("/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountEndpoint {
	
	@Inject
	protected AccountDAO accountDAO; 
	
	@GET
    @Path("{id}")
    public Response find(@PathParam("id") Long idAccount) {
		try {
    		AccountDTO accountDTO = accountDAO.find(idAccount);
        	return Response.ok(accountDTO).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
    }
	
    @GET
    @Path("listAll")
    public Response listAll() {
    	return Response.ok(accountDAO.listAll()).build();
    }
    
    @POST
    @Path("save")
    public Response save(@Valid AccountDTO accountDTO) {
    	try {
			accountDAO.save(accountDTO);
			return Response.ok().build();
		} catch (DAOException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessages()).build();
		}
    }
    
    @PUT
    @Path("save")
    public Response update(@Valid AccountDTO accountDTO) {
    	try {
    		accountDAO.update(accountDTO);
    		return Response.ok().build();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return Response.status(Status.BAD_REQUEST.getStatusCode()).entity(e.getMessage()).build();
    	}
    }
    
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long idAccount) {
    	try {
    		accountDAO.remove(Account.class, idAccount);
        	return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
    }

}
