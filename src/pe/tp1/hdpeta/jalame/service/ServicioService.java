package pe.tp1.hdpeta.jalame.service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pe.tp1.hdpeta.jalame.bean.GenericResponse;
import pe.tp1.hdpeta.jalame.bean.ServicioBean;
import pe.tp1.hdpeta.jalame.bean.ServicioEstadoBean;
import pe.tp1.hdpeta.jalame.business.ServicioBusiness;
import pe.tp1.hdpeta.jalame.xmllist.ServicioList;


@Path("/servicio")
public class ServicioService {


	ServicioBusiness servicioBusiness= new ServicioBusiness();
	
//CREATE / insert
	    @POST
	    @Path("/add")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response createServicio(ServicioBean servicio) {
	    	GenericResponse response = new GenericResponse(); 
	    	try{ 
	    		ServicioBean servicioResp = servicioBusiness.addServicio(servicio);
	    		
		    	if (servicioResp.getCodServicio() > 0 ) {
		    		return Response.ok(servicioResp).build();
		    	}else {
	    		
		    		response.setStatus(false);
					response.setMessage("Error: No ha sido posible crear el registro");
					response.setErrorCode("EC001");
					return Response.status(Response.Status.NOT_FOUND).entity(response).build(); 
		    	}
		    		
		    }catch (Exception e){
		    	System.out.println("ERROR:" + e.getMessage() );
		    }
	    	return null;
	    }

	    
//READ operation / select 
	      @GET
		  @Path("/get/{codigo}") 
	      @Produces(MediaType.APPLICATION_JSON)
		  public Response getServicio(@PathParam("codigo") int codigo){
			GenericResponse response = new GenericResponse();		
		    try{ 
		    	ServicioBean servicio = servicioBusiness.getServicio(codigo); 
		    	
		    	if (servicio.getCodServicio() > 0 ) {
		    		return Response.ok(servicio).build();
		    	}else {
		    		response.setStatus(false);
					response.setMessage("Error: El codigo de la persona no existe");
					response.setErrorCode("EC003");
					return Response.status(Response.Status.NOT_FOUND).entity(response).build();
		    	}
		    		
		    }catch (Exception e){
		    	System.out.println("ERROR:" + e.getMessage() );
		    }
		    return null;
		  }

	      
//Estado del Servicio
	      @GET
		  @Path("/status/{codigo}") 
	      @Produces(MediaType.APPLICATION_JSON)
		  public Response getEstadoServicio(@PathParam("codigo") int codigo){
			GenericResponse response = new GenericResponse();		
		    try{ 
		    	ServicioEstadoBean servicioEstado = servicioBusiness.getEstadoServicio(codigo); 
		    	
		    	if (servicioEstado.getCodServicio() > 0 ) {
		    		return Response.ok(servicioEstado).build();
		    	}else {
		    		response.setStatus(false);
					response.setMessage("Error: El codigo de la persona no existe");
					response.setErrorCode("EC003");
					return Response.status(Response.Status.NOT_FOUND).entity(response).build();
		    	}
		    		
		    }catch (Exception e){
		    	System.out.println("ERROR:" + e.getMessage() );
		    }
		    
		    return null;
		  }
	    
	      
//LISTA Servicios Usuario
		  @GET
		  @Path("/list/user/{codusuario}") 
		  @Produces({"application/json"}) 
		  public ServicioList getServicioUserList(
				  @PathParam("codusuario") int codusuario  ){
			try{
				
		      ArrayList<ServicioBean> servicioList = servicioBusiness.getServicioList("U", codusuario); 
		      return new ServicioList(servicioList);
		      
		    }catch (NumberFormatException e){}
		    return null;
		  }

//LISTA Servicios Conductor
		  @GET
		  @Path("/list/driver/{codusuario}") 
		  @Produces({"application/json"}) 
		  public ServicioList getServicioDriverList(
				  @PathParam("codusuario") int codusuario  ){
			try{
				
		      ArrayList<ServicioBean> servicioList = servicioBusiness.getServicioList("C", codusuario ); 
		      return new ServicioList(servicioList);
		      
		    }catch (NumberFormatException e){}
		    return null;
		  }

//ESTADO update
		  @PUT
		  @Path("/accept/{codigo}")
		  @Produces(MediaType.APPLICATION_JSON)
		  public Response acceptServicio(@PathParam("codigo") int codigo) {        
			    try{  
			    	if (servicioBusiness.updateEstadoServicio("A", codigo) > 0 ) { 
			    		return Response.ok(prepareGenericResponse(1, "acceptado")).build(); 
			    	}else { 
						return Response.status(Response.Status.NOT_FOUND).entity(prepareGenericResponse(0, "")).build();
			    	} 
			    }catch (Exception e){
			    	System.out.println("ERROR:" + e.getMessage() );
			    }
			    return null;
		    }
		  
		  @PUT
		  @Path("/refuse/{codigo}")
		  @Produces(MediaType.APPLICATION_JSON)
		  public Response refuseServicio(@PathParam("codigo") int codigo) {        
			    try{  
			    	if (servicioBusiness.updateEstadoServicio("R", codigo) > 0 ) { 
			    		return Response.ok(prepareGenericResponse(1, "rechazado")).build(); 
			    	}else { 
						return Response.status(Response.Status.NOT_FOUND).entity(prepareGenericResponse(0, "")).build();
			    	} 
			    }catch (Exception e){
			    	System.out.println("ERROR:" + e.getMessage() );
			    }
			    return null;
		    }
		  
		  @PUT
		  @Path("/desist/{codigo}")
		  @Produces(MediaType.APPLICATION_JSON)
		  public Response desistServicio(@PathParam("codigo") int codigo) {        
			    try{  
			    	if (servicioBusiness.updateEstadoServicio("D", codigo) > 0 ) { 
			    		return Response.ok(prepareGenericResponse(1, "desistido")).build(); 
			    	}else { 
						return Response.status(Response.Status.NOT_FOUND).entity(prepareGenericResponse(0, "")).build();
			    	} 
			    }catch (Exception e){
			    	System.out.println("ERROR:" + e.getMessage() );
			    }
			    return null;
		    }
		  
		  @PUT
		  @Path("/complet/{codigo}")
		  @Produces(MediaType.APPLICATION_JSON)
		  public Response completServicio(@PathParam("codigo") int codigo) {        
			    try{  
			    	if (servicioBusiness.updateEstadoServicio("C", codigo) > 0 ) { 
			    		return Response.ok(prepareGenericResponse(1, "completado")).build(); 
			    	}else { 
						return Response.status(Response.Status.NOT_FOUND).entity(prepareGenericResponse(0, "")).build();
			    	} 
			    }catch (Exception e){
			    	System.out.println("ERROR:" + e.getMessage() );
			    }
			    return null;
		    }
		  
 
		  
		  
		  private GenericResponse prepareGenericResponse(int tipo, String value) {
			  GenericResponse response = new GenericResponse();	
			  if (tipo > 0 ) {
		    		response.setStatus(true);
					response.setMessage("Confirmación: Estado del servicio actualizado correctamente (" + value.toUpperCase() + ") ");
					response.setErrorCode("MC001");
		    	}else {
	    		
		    		response.setStatus(false);
					response.setMessage("Error: No se ha podido actualizar es estado del servicio.");
					response.setErrorCode("EC004");
		    	}
			  
			  return response; 
		  }
		  
		  
		  
		  
		  
}
