package entityClasses.hf;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Reparto", catalog = "Cine")
@AssociationOverrides({
		@AssociationOverride(name = "pk.actor",
			joinColumns = @JoinColumn(name = "idActorFK")),
		@AssociationOverride(name = "pk.pelicula",
			joinColumns = @JoinColumn(name = "idPeliculaFK")) })
public class ActorPelicula implements Serializable {

	private static final long serialVersionUID = 1L;
	private ActorPeliculaId pk = new ActorPeliculaId();
	private String papelReparto;
	private boolean oscarReparto;
	
	public ActorPelicula()
	{}

	@EmbeddedId
	public ActorPeliculaId getPk() {
		return pk;
	}

	public void setPk(ActorPeliculaId pk) {
		this.pk = pk;
	}
/*
	@Transient
	public Actor getActor() {
		return getPk().getActor();
	}

	public void setActor(Actor actor) {
		getPk().setActor(actor);
	}
*/
	@Transient
	public Pelicula getPelicula() {
		return getPk().getPelicula();
	}

	public void setPelicula(Pelicula pelicula) {
		getPk().setPelicula(pelicula);
	}
	
	@Column(name = "papel", nullable = true, length = 20)
	public String getPapelReparto() {
		return papelReparto;
	}

	public void setPapelReparto(String papelReparto) {
		this.papelReparto = papelReparto;
	}

	@Column(name = "oscar", nullable = true)
	public boolean isOscarReparto() {
		return oscarReparto;
	}

	public void setOscarReparto(boolean oscarReparto) {
		this.oscarReparto = oscarReparto;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ActorPelicula that = (ActorPelicula) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
	
}
