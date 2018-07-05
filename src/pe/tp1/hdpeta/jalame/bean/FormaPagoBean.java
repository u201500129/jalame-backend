package pe.tp1.hdpeta.jalame.bean;

import java.util.Date;

public class FormaPagoBean {

	private int codFormaPago;
    private String formaPago;
    private String estadoR;
    private Date tsupdate;
    
    public FormaPagoBean() {
    }
    		
	public FormaPagoBean(int codFormaPago, String formaPago, String estadoR, Date tsupdate) {
		super();
		this.codFormaPago = codFormaPago;
		this.formaPago = formaPago;
		this.estadoR = estadoR;
		this.tsupdate = tsupdate;
	}
	public int getCodFormaPago() {
		return codFormaPago;
	}
	public void setCodFormaPago(int codFormaPago) {
		this.codFormaPago = codFormaPago;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getEstadoR() {
		return estadoR;
	}
	public void setEstadoR(String estadoR) {
		this.estadoR = estadoR;
	}
	public Date getTsupdate() {
		return tsupdate;
	}
	public void setTsupdate(Date tsupdate) {
		this.tsupdate = tsupdate;
	}
    
    
    
}
