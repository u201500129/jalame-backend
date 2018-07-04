package pe.tp1.hdpeta.jalame.bean;

//import javax.xml.bind.annotation.XmlRootElement;

//@XmlRootElement(name = "person")
public class PersonBean {
	private int codPersona;
	private String nombre;
	private String apellido;
	private String sexo;
	private String dni;
	private String perfil;
	private String carrera;
	private String correo;
	private String estadoR;
	private String clave;
	private String telefono;
	 
	
	public PersonBean() {
		super();
		// TODO constructor stub
	}

	public PersonBean(int codPersona, String nombre, String apellido, String sexo, String dni, String perfil,
			String carrera, String correo, String estadoR, String clave, String telefono) {
		super();
		this.codPersona = codPersona;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.dni = dni;
		this.perfil = perfil;
		this.carrera = carrera;
		this.correo = correo;
		this.estadoR = estadoR;
		this.clave = clave;
		this.telefono = telefono;
	}
	
	
	public int getCodPersona() {
		return codPersona;
	}
	public void setCodPersona(int codPersona) {
		this.codPersona = codPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getCarrera() {
		return carrera;
	}
	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getEstadoR() {
		return estadoR;
	}
	public void setEstadoR(String estadoR) {
		this.estadoR = estadoR;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	

	
}
