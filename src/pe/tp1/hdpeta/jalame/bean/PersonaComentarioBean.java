package pe.tp1.hdpeta.jalame.bean;

import java.util.Date;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "comentario")
public class PersonaComentarioBean {
	private int codServicio;
	private int persona;
	private String comentario;
	private int calificacion;
	private String autor;
	private Date fecha;
		
	public PersonaComentarioBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonaComentarioBean(int codServicio, int persona, String comentario, int calificacion, String autor,
			Date fecha) {
		super();
		this.codServicio = codServicio;
		this.persona = persona;
		this.comentario = comentario;
		this.calificacion = calificacion;
		this.autor = autor;
		this.fecha = fecha;
	}
	
	
	public int getCodServicio() {
		return codServicio;
	}
	public void setCodServicio(int codServicio) {
		this.codServicio = codServicio;
	}
	public int getPersona() {
		return persona;
	}
	public void setPersona(int persona) {
		this.persona = persona;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
