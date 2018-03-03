package clasesDAO;

import java.util.List;

import antJesJM.hf.Principal;
import clases.Reparto;

public class RepartoDAO {

	public static void guardar(Reparto rep) {
		Principal.session.beginTransaction();
		Principal.session.save(rep);
		Principal.session.getTransaction().commit();
	}

	public static Reparto buscarPorID(Integer id) {
		Reparto rep = (Reparto) Principal.session.get(Reparto.class, id);
		return rep;
	}

	public static void modificarLoc(Reparto rep) {
		Principal.session.beginTransaction();
		Principal.session.update(rep);
		Principal.session.getTransaction().commit();
	}

	public static void borrar(Reparto prod) {
		Principal.session.beginTransaction();
		Principal.session.delete(prod);
		Principal.session.getTransaction().commit();
	}

	public void borrarPorID(int id) {
		Reparto prod = buscarPorID(id);
		Principal.session.beginTransaction();
		Principal.session.delete(prod);
		Principal.session.getTransaction().commit();
	}

	public static List<Reparto> buscarTodos() {
		@SuppressWarnings("unchecked")
		List<Reparto> reparto = (List<Reparto>) Principal.session.createQuery("FROM Reparto").list();
		return reparto;
	}
}
