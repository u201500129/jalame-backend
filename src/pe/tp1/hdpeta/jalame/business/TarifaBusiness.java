package pe.tp1.hdpeta.jalame.business;

import pe.tp1.hdpeta.jalame.bean.TarifaBean;
import pe.tp1.hdpeta.jalame.dto.TarifaDto;

public class TarifaBusiness {
	TarifaDto tarifaDto = new TarifaDto();
	
	public TarifaBean getTarifa(int distancia){
	    return this.tarifaDto.getTarifa(distancia);
	}
	
}
