package pe.tp1.hdpeta.jalame.service;

import java.util.ArrayList;

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

import pe.tp1.hdpeta.jalame.bean.GenericResponse;
import pe.tp1.hdpeta.jalame.bean.VehiculoBean;
import pe.tp1.hdpeta.jalame.business.VehiculoBusiness;
import pe.tp1.hdpeta.jalame.xmllist.VehiculoList;


@Path("/vehiculo")
public class VehiculoService {

	VehiculoBusiness vehiculoBusiness= new VehiculoBusiness();
	
//CREATE / insert
	    @POST
	    @Path("/add")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response createVehiculo(VehiculoBean vehiculo) {
	    	GenericResponse response = new GenericResponse(); 
	    	try{ 
	    		VehiculoBean vehiculoResp = vehiculoBusiness.addVehiculo(vehiculo);
	    		
		    	if (vehiculoResp.getCodVehiculo() > 0 ) {
		    		return Response.ok(vehiculoResp).build();
		    		//return Response.status(200).entity(personResp).build();	

		    	}else {
	    		
		    		response.setStatus(false);
					response.setMessage("Error: No ha sido posible crear el registro");
					response.setErrorCode("EC001");
					return Response.status(Response.Status.NOT_FOUND).entity(response).build();

		    		//return Response.status(Response.Status.NOT_FOUND).build();
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
		  public Response getVehiculo(@PathParam("codigo") int codigo){
			GenericResponse response = new GenericResponse();		
		    try{ 
		    	VehiculoBean vehiculo = vehiculoBusiness.getVehiculo(codigo); 
		    	
		    	if (vehiculo.getCodVehiculo() > 0 ) {
		    		return Response.ok(vehiculo).build();
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
	    
	    
	    
//UPDATE operation
	    @PUT
	    @Path("/update")
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response updateVehiculo(VehiculoBean vehiculo) {
	    	 GenericResponse response = new GenericResponse();		
	    	 
			    try{ 
			    	VehiculoBean personResp = vehiculoBusiness.updateVehiculo(vehiculo); 
			    	
			    	if (personResp.getCodPersona() > 0 ) { 
			    		return Response.ok(personResp).build();

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
	 
	    
	    
	    
//DELETE operation
	    @DELETE
	    @Path("/delete/{codigo}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response deleteeVehiculo(@PathParam("codigo") int codigo) {       
	        GenericResponse response = new GenericResponse();		
		    try{ 
		    	
		    	if (vehiculoBusiness.deleteVehiculo(codigo) > 0 ) {
		    		response.setStatus(true);
					response.setMessage("Confirmación: Registro eliminado correctamente");
					response.setErrorCode("MC001");
		    		return Response.ok(response).build();

		    	}else {
	    		
		    		response.setStatus(false);
					response.setMessage("Error: No se ha podido eliminar el registro.");
					response.setErrorCode("EC004");
					return Response.status(Response.Status.NOT_FOUND).entity(response).build();
		    	}

		    }catch (Exception e){
		    	System.out.println("ERROR:" + e.getMessage() );
		    }
		    return null;
	    }
	    

	    
//LISTA Vehiculos Cercanos 
	  @GET
	  @Path("/list/{codusuario}/{latitud}/{longitud}") 
	  @Produces({"application/json"}) 
	  public VehiculoList getVehiculoList(
			  @PathParam("codusuario") int codusuario,
			  @PathParam("latitud") String latitud,
			  @PathParam("longitud") String longitud ){
		try{
			
	      ArrayList<VehiculoBean> vehiculoList = vehiculoBusiness.getVehiculoList(codusuario, latitud, longitud); 
	      return new VehiculoList(vehiculoList);
	      
	    }catch (NumberFormatException e){}
	    return null;
	  }
	  	  
	  
	  
//OTRAS OPERACIONES
	    @PUT
	    @Path("/visible/{codigo}/{visible}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response visibleVehiculo(@PathParam("codigo") int codigo, @PathParam("visible") String visible) {       
	        GenericResponse response = new GenericResponse();		
		    try{ 
		    	
		    	if (vehiculoBusiness.visibleVehiculo(codigo, visible) > 0 ) {
		    		response.setStatus(true);
					response.setMessage("Confirmación: Visibilidad del vehiculo actualizado correctamente");
					response.setErrorCode("MC001");
		    		return Response.ok(response).build();

		    	}else {
	    		
		    		response.setStatus(false);
					response.setMessage("Error: No se ha podido actualizar la visibilidad del vehiculo.");
					response.setErrorCode("EC004");
					return Response.status(Response.Status.NOT_FOUND).entity(response).build();
		    	}

		    }catch (Exception e){
		    	System.out.println("ERROR:" + e.getMessage() );
		    }
		    return null;
	    }
	    
	    @PUT
	    @Path("/location/{codigo}/{latitud}/{longitud}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response locationVehiculo(@PathParam("codigo") int codigo, @PathParam("latitud") String latitud, @PathParam("longitud") String longitud) {       
	        GenericResponse response = new GenericResponse();		
		    try{ 
		    	
		    	if (vehiculoBusiness.locationVehiculo(codigo, latitud, longitud) > 0 ) {
		    		response.setStatus(true);
					response.setMessage("Confirmación: Ubicacion Registrado correctamente");
					response.setErrorCode("MC001");
		    		return Response.ok(response).build();

		    	}else {
	    		
		    		response.setStatus(false);
					response.setMessage("Error: No se ha podido registrar Ubicacion.");
					response.setErrorCode("EC004");
					return Response.status(Response.Status.NOT_FOUND).entity(response).build();
		    	}

		    }catch (Exception e){
		    	System.out.println("ERROR:" + e.getMessage() );
		    }
		    return null;
	    }
	    
	    
	    
	    @PUT
	    @Path("/asiento/{codigo}/{cantidad}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response asientosVehiculo(@PathParam("codigo") int codigo, @PathParam("cantidad") int cantidad) {       
	        GenericResponse response = new GenericResponse();		
		    try{ 
		    	
		    	if (vehiculoBusiness.asientoVehiculo(codigo, cantidad) > 0 ) {
		    		response.setStatus(true);
					response.setMessage("Confirmación: Asientos disponibles actualizado correctamente");
					response.setErrorCode("MC001");
		    		return Response.ok(response).build();

		    	}else {
	    		
		    		response.setStatus(false);
					response.setMessage("Error: No se ha podido actualizar la disponibilidad de asientos.");
					response.setErrorCode("EC004");
					return Response.status(Response.Status.NOT_FOUND).entity(response).build();
		    	}

		    }catch (Exception e){
		    	System.out.println("ERROR:" + e.getMessage() );
		    }
		    return null;
	    }
	    
	    

}
