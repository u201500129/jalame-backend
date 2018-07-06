package pe.tp1.hdpeta.jalame.business;

import java.util.ArrayList;

import pe.tp1.hdpeta.jalame.bean.FormaPagoBean;
import pe.tp1.hdpeta.jalame.dto.FormaPagoDto;

public class FormaPagoBusiness {
	FormaPagoDto formaPagoDto = new FormaPagoDto();
	
	public ArrayList<FormaPagoBean> getFormaPagoList(){
	    return this.formaPagoDto.listarFormaPago();
	}
	
}
