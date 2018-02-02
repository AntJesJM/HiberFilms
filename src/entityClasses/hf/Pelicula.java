package entityClasses.hf;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import clases.ActorPelicula;

@Entity
@Table(name = "Peliculas", catalog = "Cine")
public class Pelicula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer idPelicula;
	private String tituloPelicula;
	private int anioPelicula;
	private String generoPelicula;
//	private Set<ActorPelicula> actorPelicula = new HashSet<ActorPelicula>(0);

	public Pelicula() {
	}

	public Pelicula(String titulo, int anio, String genero) {
		this.tituloPelicula = titulo;
		this.anioPelicula = anio;
		this.generoPelicula = genero;
	}

/*	public Pelicula(String titulo, int anio, String genero, Set<ActorPelicula> actorPelicula) {
		this.tituloPelicula = titulo;
		this.anioPelicula = anio;
		this.generoPelicula = genero;
		this.actorPelicula = actorPelicula;
	}*/

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idPelicula", unique = true, nullable = false)
	public Integer getIdPelicula() {
		return idPelicula;
	}

	public void setIdPelicula(Integer idPelicula) {
		this.idPelicula = idPelicula;
	}

	@Column(name = "titulo", nullable = false, length = 70)
	public String getTituloPelicula() {
		return tituloPelicula;
	}

	public void setTituloPelicula(String tituloPelicula) {
		this.tituloPelicula = tituloPelicula;
	}

	@Column(name = "anio", nullable = false, length = 4)
	public int getAnioPelicula() {
		return anioPelicula;
	}

	public void setAnioPelicula(int anioPelicula) {
		this.anioPelicula = anioPelicula;
	}

	@Column(name = "genero", nullable = false, length = 20)
	public String getGeneroPelicula() {
		return generoPelicula;
	}

	public void setGeneroPelicula(String generoPelicula) {
		this.generoPelicula = generoPelicula;
	}
/*
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.pelicula")
	public Set<ActorPelicula> getActorPelicula() {
		return actorPelicula;
	}

	public void setActorPelicula(Set<ActorPelicula> actorPelicula) {
		this.actorPelicula = actorPelicula;
	}
*/
	
	
	
}