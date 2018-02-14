package clases;

import java.io.Serializable;
import java.util.Set;


public class Actor implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idActor;
	private String nombre;
	private String apellido;
	private String nacionalidad;
	private int edad;
	private Set<Reparto> reparto;

	public Actor() {
	}

	public Actor(String nombre, String apellido, String nacionalidad, int edad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.edad=edad;
	}
	
	public Actor(String nombre, String apellido, String nacionalidad, int edad, Set<Reparto> reparto) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nacionalidad = nacionalidad;
		this.edad=edad;
		this.reparto=reparto;
	}

	public Integer getIdActor() {
		return idActor;
	}

	public void setIdActor(Integer idActor) {
		this.idActor = idActor;
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Set<Reparto> getReparto() {
		return reparto;
	}

	public void setReparto(Set<Reparto> reparto) {
		this.reparto = reparto;
	}

	
}
