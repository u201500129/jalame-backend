package pe.tp1.hdpeta.jalame.business;

import java.util.ArrayList;

import pe.tp1.hdpeta.jalame.bean.ServicioBean;
import pe.tp1.hdpeta.jalame.bean.ServicioEstadoBean;
import pe.tp1.hdpeta.jalame.dto.ServicioDto;

public class ServicioBusiness {

	  ServicioDto servicioDto = new ServicioDto();

	  public ServicioBean addServicio(ServicioBean servicio){
	    return this.servicioDto.addServicio(servicio);
	  }
	  
	  public ServicioBean getServicio(int codigoServicio) {
		  return this.servicioDto.getServicio(codigoServicio);  
	  }
	  
	  public ServicioEstadoBean getEstadoServicio(int codigoServicio) {
		  return this.servicioDto.getEstadoServicio(codigoServicio);  
	  }
	  
	  public int updateEstadoServicio(String estado, int codigoServicio){
		  return this.servicioDto.updateEstadoServicio(estado, codigoServicio);
	  }
	  
	  public ArrayList<ServicioBean> getServicioList(String perfil, int codigoServicio){
		    return this.servicioDto.getServicioList(perfil, codigoServicio);
	  }
	  
}
