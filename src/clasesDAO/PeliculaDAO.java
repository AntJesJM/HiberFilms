package clasesDAO;

import java.util.List;
import javax.persistence.TypedQuery;
import antJesJM.hf.Principal;
import clases.Pelicula;

public class PeliculaDAO {
	
	public static void guardar(Pelicula film) {
		Principal.session.beginTransaction();
		Principal.session.save(film);
		Principal.session.getTransaction().commit();
	}

	public static Pelicula buscarPorID(Integer id) {
		Pelicula film = (Pelicula) Principal.session.get(Pelicula.class, id);
		return film;
	}

	public static void modificar(Pelicula film) {
		Principal.session.beginTransaction();
		Principal.session.update(film);
		Principal.session.getTransaction().commit();
	}

	public static void borrar(Pelicula film) {
		Principal.session.beginTransaction();
		Principal.session.delete(film);
		Principal.session.getTransaction().commit();
	}

	public void borrarPorID(int id) {
		Pelicula film = buscarPorID(id);
		Principal.session.beginTransaction();
		Principal.session.delete(film);
		Principal.session.getTransaction().commit();
	}

	public static List<Pelicula> buscarTodos() {

		@SuppressWarnings("unchecked")
		TypedQuery<Pelicula> query = (TypedQuery<Pelicula>) Principal.session.createQuery("FROM Pelicula");
		List<Pelicula> result = query.getResultList();
		return result;
	}
}
