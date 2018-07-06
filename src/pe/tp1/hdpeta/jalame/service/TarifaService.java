package pe.tp1.hdpeta.jalame.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pe.tp1.hdpeta.jalame.bean.GenericResponse;
import pe.tp1.hdpeta.jalame.bean.TarifaBean;
import pe.tp1.hdpeta.jalame.business.TarifaBusiness;

@Path("/tarifa")
public class TarifaService {

	TarifaBusiness tarifaBusiness= new TarifaBusiness();
	
	
//READ operation / select 
    @GET
	@Path("/get/{distancia}") 
    @Produces(MediaType.APPLICATION_JSON)
	  public Response getTarifa(@PathParam("distancia") int distancia){
		GenericResponse response = new GenericResponse();		
	    try{ 
	    	TarifaBean tarifa = tarifaBusiness.getTarifa(distancia); 
	    	
	    	if (tarifa.getCodTarifa() > 0 ) {
	    		return Response.ok(tarifa).build();
	    	}else {
	    		response.setStatus(false);
				response.setMessage("Error: No ha sido posible obtener la tarifa.");
				response.setErrorCode("EC003");
				return Response.status(Response.Status.NOT_FOUND).entity(response).build();
	    	}
	    		
	    }catch (Exception e){
	    	System.out.println("ERROR:" + e.getMessage() );
	    }
	    return null;
	  }
    
}
