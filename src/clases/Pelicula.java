package clases;

import java.io.Serializable;
import java.util.Set;


public class Pelicula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer idPelicula;
	private String titulo;
	private int anio;
	private String genero;
	private String director;
	private Set<Reparto> reparto;

	public Pelicula() {
	}

	public Pelicula(String titulo, int anio, String genero, String director) {
		this.titulo = titulo;
		this.anio = anio;
		this.genero = genero;
		this.director=director;
	}
	
	public Pelicula(String titulo, int anio, String genero, Set<Reparto> reparto) {
		this.titulo = titulo;
		this.anio = anio;
		this.genero = genero;
		this.reparto=reparto;
	}


	public Integer getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(Integer idPelicula) {
		this.idPelicula = idPelicula;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Set<Reparto> getReparto() {
		return reparto;
	}

	public void setReparto(Set<Reparto> reparto) {
		this.reparto = reparto;
	}
	
}
