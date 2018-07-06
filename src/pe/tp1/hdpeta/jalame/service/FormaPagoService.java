package pe.tp1.hdpeta.jalame.service;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import pe.tp1.hdpeta.jalame.bean.FormaPagoBean;
import pe.tp1.hdpeta.jalame.business.FormaPagoBusiness;
import pe.tp1.hdpeta.jalame.xmllist.FormaPagoList;

@Path("/formapago")
public class FormaPagoService {

	FormaPagoBusiness formaPagoBusiness= new FormaPagoBusiness();
	
//LISTA FormaPagos  
	  @GET
	  @Path("/list") 
	  @Produces({"application/json"}) 
	  public FormaPagoList getFormaPagoList(){
		try{
			
	      ArrayList<FormaPagoBean> formaPagoList = formaPagoBusiness.getFormaPagoList(); 
	      return new FormaPagoList(formaPagoList);
	      
	    }catch (NumberFormatException e){}
	    return null;
	  }
	  
}
