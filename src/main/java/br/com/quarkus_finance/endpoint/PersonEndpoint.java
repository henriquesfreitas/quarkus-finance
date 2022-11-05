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

import br.com.quarkus_finance.dao.all.PersonDAO;
import br.com.quarkus_finance.dto.all.PersonDTO;
import br.com.quarkus_finance.models.all.Person;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonEndpoint {
	
	@Inject
	protected PersonDAO personDAO; 
	
	@GET
    @Path("{id}")
    public Response find(@PathParam("id") Long idPerson) {
		try {
    		PersonDTO personDTO = personDAO.find(idPerson);
        	return Response.ok(personDTO).build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
    }

    @GET
    @Path("listAll")
    public Response listAll() {
    	return Response.ok(personDAO.listAll()).build();
    }
    
    @POST
    @Path("save")
    public Response save(@Valid PersonDTO personDTO) {
    	try {
			personDAO.save(personDTO);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
    }
    
    @PUT
    @Path("save")
    public Response update(@Valid PersonDTO personDTO) {
    	try {
    		personDAO.update(personDTO);
    		return Response.ok().build();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return Response.status(Status.BAD_REQUEST.getStatusCode()).entity(e.getMessage()).build();
    	}
    }
    
    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") Long idPerson) {
    	try {
    		personDAO.remove(Person.class, idPerson);
        	return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}
    }

}
