package entityClasses.hf;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ActorPeliculaId implements Serializable{

	private static final long serialVersionUID = 1L;
//	private Actor actor;
	private Pelicula pelicula;
/*	
	@ManyToOne
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	*/
	@ManyToOne
	public Pelicula getPelicula() {
		return pelicula;
	}
	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}
/*	
	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActorPeliculaId that = (ActorPeliculaId) o;

        if (actor != null ? !actor.equals(that.actor) : that.actor != null) return false;
        if (pelicula != null ? !pelicula.equals(that.pelicula) : that.pelicula != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (actor != null ? actor.hashCode() : 0);
        result = 31 * result + (pelicula != null ? pelicula.hashCode() : 0);
        return result;
    }
	*/
}

