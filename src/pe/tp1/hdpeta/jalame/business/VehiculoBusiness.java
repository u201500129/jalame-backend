package pe.tp1.hdpeta.jalame.business;

import java.util.ArrayList;

import pe.tp1.hdpeta.jalame.bean.VehiculoBean;
import pe.tp1.hdpeta.jalame.dto.VehiculoDto;

public class VehiculoBusiness {
	VehiculoDto vehiculoDto = new VehiculoDto();
	  
	 
	  
	  public VehiculoBean addVehiculo(VehiculoBean vehiculo){
	    return this.vehiculoDto.addVehiculo(vehiculo);
	  }
	  
	  public VehiculoBean getVehiculo(int codigoVehiculo) {
		  return this.vehiculoDto.getVehiculo(codigoVehiculo);  
	  }
	  
	  public VehiculoBean updateVehiculo(VehiculoBean vehiculo){
		  return this.vehiculoDto.updateVehiculo(vehiculo);
	  }
	  
	  public int deleteVehiculo(int codigoVehiculo) {
		  return this.vehiculoDto.deleteVehiculo(codigoVehiculo);  
	  }
	  
	  public int visibleVehiculo(int codigoVehiculo, String visible) {
		  return this.vehiculoDto.visibleVehiculo(codigoVehiculo, visible);  
	  }
	  
	  public int locationVehiculo(int codigoVehiculo, String latitud, String longitud) {
		  return this.vehiculoDto.locationVehiculo(codigoVehiculo, latitud, longitud);   
	  }
	  
	  public int asientoVehiculo(int codigoVehiculo, int cantidad) {
		  return this.vehiculoDto.asientoVehiculo(codigoVehiculo, cantidad);  
	  }
	  

	  public ArrayList<VehiculoBean> getVehiculoList(int codigoUsuario, String latitud, String longitud){
		    return this.vehiculoDto.listarVehiculo(codigoUsuario, latitud, longitud);
	  }
	   
	  
	  
}
