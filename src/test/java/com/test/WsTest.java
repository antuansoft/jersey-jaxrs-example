package com.test;


import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.domain.Person;



public class WsTest {
	
		private HttpServer server;
	    private WebTarget target;
	    
	    // Base URI the Grizzly HTTP server will listen on (URL Base donde apunta nuestra aplicación)
	    public static final String BASE_URI = "http://localhost:8080/simple-service-webapp/webapi";

	    /**
	     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
	     * @return Grizzly HTTP server.
	     */
	    private HttpServer startServer() {
	        // create a resource config that scans for JAX-RS resources and providers
	        // in com.example package
	        ResourceConfig rc = new ResourceConfig().packages("com.example");

	        // create and start a new instance of grizzly http server
	        // exposing the Jersey application at BASE_URI
	        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
	    }
	    
	    @Before
	    public void setUp() throws Exception {
	    	
	        // start the server
	        server = startServer();
	        // create the client
	        Client c = ClientBuilder.newClient();	        
	        target = c.target(BASE_URI);
	    }

	    @After
	    public void tearDown() throws Exception {
	        server.stop();
	    }

	    /**
	     * Test to see that the message "Got it!" is sent in the response.
	     */
	    @Test
	    public void getIt() {
	        String responseMsg = target.path("myresource/text").request().get(String.class);
	        assertEquals("Got it!", responseMsg);
	    }
	    
	    @Test
	    public void persona() {
	    	Person person = target.path("myresource/persona").request().get(Person.class);	        
	        assertEquals("Antuan", person.getNombre());
	    }
	    
	    @Test
	    public void personaCustom() throws Exception{
	    	
	    	Person input = new Person();
	    	input.setNombre("Antuan");
	    	input.setApellidos("F1");
	    	input.setEdad(22);
	    	
	    	Person person  = target.path("myresource/personacustom").request(MediaType.APPLICATION_JSON).post(Entity.json(input),Person.class);	    		        
	        
	    	assertEquals("Antuan", person.getNombre());
	        assertEquals("F1", person.getApellidos());
	        assertEquals(new Integer(22), person.getEdad());
	    }
	    
	    @Test(expected=NotAcceptableException.class)
	    public void personaCustomXMLInputError() throws Exception{
	    	
	    	Person input = new Person();
	    	input.setNombre("Antuan");
	    	input.setApellidos("F1");
	    	input.setEdad(22);	    	
	    	Person person  = target.path("myresource/personacustom").request(MediaType.APPLICATION_XML).post(Entity.json(input),Person.class);	    		        
	        
	    }
	    
	    
	    @Test(expected=NotAuthorizedException.class)
	    public void noAutorizado() {
	        target.path("/myresource/noautorizado").request().get(String.class);	       
	    }
	    

}
