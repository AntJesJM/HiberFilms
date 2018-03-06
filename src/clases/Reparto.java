package clases;

import java.io.Serializable;

public class Reparto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idReparto;
	private String papel;
	private boolean premio;
	private Pelicula pelicula;
	private Actor actor;

	Reparto(){}
	Reparto(Integer id, String papel, boolean premio, Pelicula pelicula,Actor actor){
		this.idReparto=id;
		this.papel=papel;
		this.premio=premio;
		this.pelicula=pelicula;
		this.actor=actor;
	}

	public Reparto(String papel, boolean prem, Pelicula pelicula, Actor actor) {
		this.papel=papel;
		this.premio=prem;
		this.pelicula=pelicula;
		this.actor=actor;
	}
	public Integer getIdReparto() {
		return idReparto;
	}
	public void setIdReparto(Integer idReparto) {
		this.idReparto = idReparto;
	}
	public String getPapel() {
		return papel;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}
	public boolean isPremio() {
		return premio;
	}
	public void setPremio(boolean premio) {
		this.premio = premio;
	}
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
}
