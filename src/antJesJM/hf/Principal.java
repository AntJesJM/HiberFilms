package antJesJM.hf;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;

	JTabbedPane pestañas = new JTabbedPane();
	JPanel actores = new JPanel();
	JPanel peliculas = new JPanel();
	JPanel reparto = new JPanel();

	JButton btnNew = new JButton("Nuevo");
	JButton btnDel = new JButton("Borrar");
	JButton btnUpd = new JButton("Modificar");
	JButton btnSee = new JButton("Ver");
	String[][] datos = { { "1", "a", "b" }, { "3", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" },
			{ "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" } };
	String[] cabecera = { "id", "Nombre", "Año de Nacimiento" };
	JTable tabla = new JTable(datos, cabecera);
	JScrollPane scroll = new JScrollPane(tabla);

	public Principal() {
		setTitle("Hibernate");
		setLayout(new FlowLayout());
		scroll.setPreferredSize(new Dimension(400, 100));
		actores.setLayout(new FlowLayout());

		actores.add(scroll);
		actores.add(btnNew);
		actores.add(btnDel);
		actores.add(btnUpd);
		actores.add(btnSee);
		
		
		pestañas.add("actores", actores);
		pestañas.add("películas", peliculas);
		pestañas.add("reparto", reparto);
		add(pestañas);

		setSize(500, 300);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Principal();
	}

	

	

}
