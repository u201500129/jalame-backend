package pe.tp1.hdpeta.jalame.business;

import java.util.ArrayList;

import pe.tp1.hdpeta.jalame.bean.SedeBean;
import pe.tp1.hdpeta.jalame.dto.SedeDto;

public class SedeBusiness {
	SedeDto sedeDto = new SedeDto();
	
	public ArrayList<SedeBean> getSedeList(){
	    return this.sedeDto.listarSede();
  }
	
	
}
