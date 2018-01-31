package antJesJM.hf;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;

	JTabbedPane pestañas = new JTabbedPane();
	JPanel actores = new JPanel(new BorderLayout());
	JPanel peliculas = new JPanel(new BorderLayout());
	JPanel reparto = new JPanel(new BorderLayout());
	JPanel pnlBtnActores = new JPanel(new GridLayout(1,4));
	
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
		setLayout(new GridLayout(1, 1));
		scroll.setPreferredSize(new Dimension(400, 100));

		actores.add("Center",scroll);
		pnlBtnActores.add(btnNew);
		pnlBtnActores.add(btnDel);
		pnlBtnActores.add(btnUpd);
		pnlBtnActores.add(btnSee);
		actores.add("South",pnlBtnActores);
		
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
