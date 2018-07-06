package pe.tp1.hdpeta.jalame.bean;

public class ServicioEstadoBean {
	private int codServicio;
    private String fecRegistro;
    private String inicioServ;
    private String finServ;
    private String estadoServ;
    private String estadoR;
    private String tsupdate;
    
    
    // Con fines de reducri el consumo de datos 
    
	public ServicioEstadoBean() { 
		
	}
  
	public int getCodServicio() {
		return codServicio;
	}
	public void setCodServicio(int codServicio) {
		this.codServicio = codServicio;
	}
	public String getFecRegistro() {
		return fecRegistro;
	}
	public void setFecRegistro(String fecRegistro) {
		this.fecRegistro = fecRegistro;
	}
	public String getInicioServ() {
		return inicioServ;
	}
	public void setInicioServ(String inicioServ) {
		this.inicioServ = inicioServ;
	}
	public String getFinServ() {
		return finServ;
	}
	public void setFinServ(String finServ) {
		this.finServ = finServ;
	}
	public String getEstadoServ() {
		return estadoServ;
	}
	public void setEstadoServ(String estadoServ) {
		this.estadoServ = estadoServ;
	}
	public String getEstadoR() {
		return estadoR;
	}
	public void setEstadoR(String estadoR) {
		this.estadoR = estadoR;
	}
	public String getTsupdate() {
		return tsupdate;
	}
	public void setTsupdate(String tsupdate) {
		this.tsupdate = tsupdate;
	}
    
    
    
}
