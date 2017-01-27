package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.domain.Person;



/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/myresource")
public class MyWS {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */

	
	@Path("/text")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHello() {
    	
        return "Got it!";
    }
	
	@Path("/response")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response	 response() {
    	
    	Person persona = new Person();
    	persona.setNombre("Antuan");
    	persona.setApellidos("MA");
    	persona.setEdad(38);
    	
    	
        return Response.status(Status.OK).entity(persona).build();
    }
	
	@Path("/persona")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Person persona() {
    	
    	Person persona = new Person();
    	persona.setNombre("Antuan");
    	persona.setApellidos("MA");
    	persona.setEdad(38);
    	
    	
        return persona;
    }
	
	@Path("/personacustom")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Person personaCustom(Person p) {
    	
    	Person persona = new Person();
    	persona.setNombre(p.getNombre());
    	persona.setApellidos(p.getApellidos());
    	persona.setEdad(p.getEdad());
    	
    	
        return persona;
    }
	
	@Path("/personas")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> personas() {
    	    	   
    	List<Person> personas = new ArrayList<Person>();
    	Person persona = new Person();
    	persona.setNombre("Antuan");
    	persona.setApellidos("MA");
    	persona.setEdad(38);
    	
    	personas.add(persona);
    	
    	persona = new Person();
    	persona.setNombre("Felipe");
    	persona.setApellidos("JT");
    	persona.setEdad(21);
    	
    	personas.add(persona);
    	
        return personas;
    }
	
	@Path("/personaxml")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Person xml() {
    	
    	Person persona = new Person();
    	persona.setNombre("Antuan");
    	persona.setApellidos("MA");
    	persona.setEdad(38);
    	
    	
        return persona;
    }
	
	@Path("/notfound")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response	 error1() {
    	
    	    	
    	
        return Response.status(Status.NOT_FOUND).build();
    }
	
	@Path("/noautorizado")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response	 error2() {
    	
    	    	
    	
        return Response.status(Status.UNAUTHORIZED).build();
    }
	
	@Path("/internalerror")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response	 error3() {
    	
    	    	
    	
        return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
	
}
