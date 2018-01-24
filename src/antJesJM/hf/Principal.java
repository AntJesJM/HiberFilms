package antJesJM.hf;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;

	JTabbedPane pesta�as = new JTabbedPane();
	JPanel actores = new JPanel();
	JPanel peliculas = new JPanel();
	JPanel reparto = new JPanel();
	
	
	
	JButton btnNew = new JButton("Nuevo");
	JButton btnDel = new JButton("Borrar");
	JButton btnUpd = new JButton("Modificar");
	JButton btnSee = new JButton("Ver");
	String[][] datos = { { "1", "a", "b" }, { "3", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" },
			{ "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" } };
	String[] cabecera = { "id", "Nombre", "A�o de Nacimiento" };
	JTable tabla = new JTable(datos, cabecera);
	JScrollPane JS = new JScrollPane(tabla);

	public Principal() {
		setTitle("Hibernate");
		setLayout(new GridLayout(1,1));
		JS.setPreferredSize(new Dimension(400, 100));

		actores.add(JS);
		
		
		
		pesta�as.add("actores",actores);
		pesta�as.add("pel�culas", peliculas);
		pesta�as.add("reparto",reparto);
		add(pesta�as);

		setSize(500, 300);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Principal();
	}

}
