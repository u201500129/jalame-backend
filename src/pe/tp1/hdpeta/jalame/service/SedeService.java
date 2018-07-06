package pe.tp1.hdpeta.jalame.service;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import pe.tp1.hdpeta.jalame.bean.SedeBean;
import pe.tp1.hdpeta.jalame.business.SedeBusiness;
import pe.tp1.hdpeta.jalame.xmllist.SedeList;


@Path("/sede")
public class SedeService {

	SedeBusiness sedeBusiness= new SedeBusiness();
	
//LISTA Sedes  
	  @GET
	  @Path("/list") 
	  @Produces({"application/json"}) 
	  public SedeList getSedeList( ){
		try{
			
	      ArrayList<SedeBean> sedeList = sedeBusiness.getSedeList(); 
	      return new SedeList(sedeList);
	      
	    }catch (Exception e){
	    	System.out.println("Error Service: " + e.getMessage());
	    }
	    return null;
	  }
	  
	  
}
