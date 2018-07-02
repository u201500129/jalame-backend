package pe.tp1.hdpeta.jalame.service;


import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response; 
import javax.ws.rs.core.MediaType;

import pe.tp1.hdpeta.jalame.bean.PersonBean;
import pe.tp1.hdpeta.jalame.bean.GenericResponse;
import pe.tp1.hdpeta.jalame.business.PersonBusiness;
import pe.tp1.hdpeta.jalame.xmllist.PersonList;

@Path("/person")
public class PersonService {

	PersonBusiness personBusiness = new PersonBusiness();  
	  
	  @GET
	  @Path("/login/{user}/{passw}") 
	  @Produces({"application/json"}) 
	  public Response userLogin(@PathParam("user") String user, @PathParam("passw") String passw  ){
		System.out.println("LOGIN start:" + user); 
		GenericResponse response = new GenericResponse();
		
	    try{ 
	    	PersonBean person = personBusiness.login(user, passw); 
	    	
	    	if (person.getCodpersona() > 0 ) {
	    		return Response.ok(person).build();
	    		//return Response.status(200).entity(person).build();	

	    	}else {
    		
	    		response.setStatus(false);
				response.setMessage("Error: Usuario o Clave Incorrecto");
				response.setErrorCode("EC002");
				return Response.status(Response.Status.NOT_FOUND).entity(response).build();

	    		//return Response.status(Response.Status.NOT_FOUND).build();
	    	}
	    		
	    }catch (Exception e){
	    	System.out.println("LOGIN: " + e.getMessage() );
	    }
	    return null;
	  }
	  
//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//CREATE / insert
	    @POST
	    @Path("/add")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response createPerson(PersonBean person) {
	    	GenericResponse response = new GenericResponse(); 
	    	try{ 
	    		PersonBean personResp = personBusiness.addPerson(person);
	    		
		    	if (personResp.getCodpersona() > 0 ) {
		    		return Response.ok(personResp).build();
		    		//return Response.status(200).entity(personResp).build();	

		    	}else {
	    		
		    		response.setStatus(false);
					response.setMessage("Error: Usuario o Clave Incorrecto");
					response.setErrorCode("EC002");
					return Response.status(Response.Status.NOT_FOUND).entity(response).build();

		    		//return Response.status(Response.Status.NOT_FOUND).build();
		    	}
		    		
		    }catch (Exception e){
		    	System.out.println("LOGIN: " + e.getMessage() );
		    }
	    	return null;
	    }


	    
//READ operation / select 
	      @GET
		  @Path("/get/{codigo}") 
	      @Produces(MediaType.APPLICATION_JSON)
		  public Response getPersona(@PathParam("codigo") int codigo){
			GenericResponse response = new GenericResponse();		
		    try{ 
		    	PersonBean person = personBusiness.getPersona(codigo); 
		    	
		    	if (person.getCodpersona() > 0 ) {
		    		return Response.ok(person).build();
		    		//return Response.status(200).entity(person).build();	

		    	}else {
	    		
		    		response.setStatus(false);
					response.setMessage("Error: El codigo de la persona no existe");
					response.setErrorCode("EC003");
					return Response.status(Response.Status.NOT_FOUND).entity(response).build();

		    		//return Response.status(Response.Status.NOT_FOUND).build();
		    	}
		    		
		    }catch (Exception e){
		    	System.out.println("LOGIN: " + e.getMessage() );
		    }
		    return null;
		  }
	    
	    
	    
	    
	    
//UPDATE operation
	    @PUT
	    @Path("/update")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response updatePerson(PersonBean person) {
	    	 GenericResponse response = new GenericResponse();		
	    	 
			    try{ 
			    	PersonBean personResp = personBusiness.updatePersona(person); 
			    	
			    	if (personResp.getCodpersona() > 0 ) { 
			    		return Response.ok(personResp).build();

			    	}else {
		    		
			    		response.setStatus(false);
						response.setMessage("Error: El codigo de la persona no existe");
						response.setErrorCode("EC003");
						return Response.status(Response.Status.NOT_FOUND).entity(response).build();
			    	}
			    }catch (Exception e){
			    	System.out.println("LOGIN: " + e.getMessage() );
			    }
			    return null;
	    }
	 
	    
	    
	    
// DELETE operation
	    @DELETE
	    @Path("/delete/{codigo}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response deleteePerson(@PathParam("codigo") int codigo) {       
	        GenericResponse response = new GenericResponse();		
		    try{ 
		    	
		    	if (personBusiness.deletePersona(codigo) > 0 ) {
		    		response.setStatus(true);
					response.setMessage("Confirmación: Persona eliminada correctamente");
					response.setErrorCode("MC001");
		    		return Response.ok(response).build();

		    	}else {
	    		
		    		response.setStatus(false);
					response.setMessage("Error: No se ha podido eliminar el registro de persona.");
					response.setErrorCode("EC004");
					return Response.status(Response.Status.NOT_FOUND).entity(response).build();
		    	}
		    		
		    }catch (Exception e){
		    	System.out.println("LOGIN: " + e.getMessage() );
		    }
		    return null;
	    }
	    

	    
	    
	  
//@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	  @GET
	  @Path("/list/{apellido}") 
	  @Produces({"application/xml"}) 
	  public PersonList getStockByCodigo(@PathParam("apellido") String apellido){
		try{
	      ArrayList<PersonBean> personList = personBusiness.getPersonList(apellido);
	      return new PersonList(personList);
	    }catch (NumberFormatException e){}
	    return null;
	  }
	  


/*
	public List<User> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        return userList;
    }
 */
	  
	  
	
}
