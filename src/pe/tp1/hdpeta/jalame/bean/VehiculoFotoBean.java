package pe.tp1.hdpeta.jalame.bean;
 
public class VehiculoFotoBean {
	private int codVehiculo;
    private int codPersona;
    private String visible;
    private String estadoR;
    private String foto;
    
       
	public VehiculoFotoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
 
	public VehiculoFotoBean(int codVehiculo, int codPersona, String visible, String estadoR, String foto) {
		super();
		this.codVehiculo = codVehiculo;
		this.codPersona = codPersona;
		this.visible = visible;
		this.estadoR = estadoR;
		this.foto = foto;
	}
	
	
	public int getCodVehiculo() {
		return codVehiculo;
	}
	public void setCodVehiculo(int codVehiculo) {
		this.codVehiculo = codVehiculo;
	}
	public int getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(int codPersona) {
		this.codPersona = codPersona;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public String getEstadoR() {
		return estadoR;
	}
	public void setEstadoR(String estadoR) {
		this.estadoR = estadoR;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

    
    
    
    
}
