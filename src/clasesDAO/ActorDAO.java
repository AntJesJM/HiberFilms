package clasesDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import antJesJM.hf.Principal;
import clases.Actor;

public class ActorDAO {
	
	public static void guardar(Actor actor) {
		Principal.session.beginTransaction();
		Principal.session.save(actor);
		Principal.session.getTransaction().commit();
	}
	
	public static Actor buscarPorID(Integer id) {
		Actor actor = (Actor) Principal.session.get(Actor.class, id);
		return actor;
	}
	
	public static void modificar(Actor actor) {
		Principal.session.beginTransaction();
		Principal.session.update(actor);
		Principal.session.getTransaction().commit();
	}
	
	public static void borrar(Actor actor) {
		Principal.session.beginTransaction();
		Principal.session.delete(actor);
		Principal.session.getTransaction().commit();
	}
	
	public void borrarPorID(int id) {
		Actor actor = buscarPorID(id);
		Principal.session.beginTransaction();
		Principal.session.delete(actor);
		Principal.session.getTransaction().commit();
	}
	
	public static List<Actor> buscarTodos() {

		@SuppressWarnings("unchecked")
		TypedQuery<Actor> query = (TypedQuery<Actor>) Principal.session.createQuery("FROM Actor");
		List<Actor> result = query.getResultList();
		return result;
	}

}
