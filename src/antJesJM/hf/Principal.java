package antJesJM.hf;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import actores.AnyadirActor;
import actores.BorrarActor;
import actores.ModificarActor;

public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	AnyadirActor aa = new AnyadirActor();
	BorrarActor ba = new BorrarActor();
	ModificarActor ma = new ModificarActor();

	JTabbedPane pestañas = new JTabbedPane();

	JPanel actores = new JPanel(new BorderLayout());
	JPanel peliculas = new JPanel(new BorderLayout());
	JPanel reparto = new JPanel(new BorderLayout());

	JPanel pnlBtnActores = new JPanel(new GridLayout(1, 4));
	JPanel pnlBtnPeliculas = new JPanel(new GridLayout(1, 4));
	JPanel pnlBtnReparto = new JPanel(new GridLayout(1, 4));

	JButton btnNewActor = new JButton("Nuevo");
	JButton btnDelActor = new JButton("Borrar");
	JButton btnUpdActor = new JButton("Modificar");
	JButton btnSeeActor = new JButton("Ver");

	JButton btnNewPelicula = new JButton("Nuevo");
	JButton btnDelPelicula = new JButton("Borrar");
	JButton btnUpdPelicula = new JButton("Modificar");
	JButton btnSeePelicula = new JButton("Ver");

	JButton btnNewReparto = new JButton("Nuevo");
	JButton btnDelReparto = new JButton("Borrar");
	JButton btnUpdReparto = new JButton("Modificar");
	JButton btnSeeReparto = new JButton("Ver");

	String[][] datosActor = { { "1", "a", "b" }, { "3", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" },
			{ "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" } };
	String[] cabeceraActor = { "Nombre", "Nacionalidad", "Edad" };
	JTable tablaActor = new JTable(datosActor, cabeceraActor);
	JScrollPane scrollActor = new JScrollPane(tablaActor);

	String[][] datosPelicula = { { "1", "a", "b" }, { "3", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" },
			{ "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" } };
	String[] cabeceraPelicula = { "Titulo", "Año", "Género" };
	JTable tablaPelicula = new JTable(datosPelicula, cabeceraPelicula);
	JScrollPane scrollPelicula = new JScrollPane(tablaPelicula);

	String[][] datosReparto = { { "1", "a", "b" }, { "3", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" },
			{ "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" } };
	String[] cabeceraReparto = { "Actor", "papel", "premio" };
	JTable tablaReparto = new JTable(datosReparto, cabeceraReparto);
	JScrollPane scrollReparto = new JScrollPane(tablaReparto);

	public Principal() {
		setResizable(false);
		setTitle("HiberFilms");
		setLayout(new GridLayout(1, 1));
		scrollActor.setPreferredSize(new Dimension(400, 100));
		scrollPelicula.setPreferredSize(new Dimension(400, 100));
		scrollReparto.setPreferredSize(new Dimension(400, 100));

		actores.add("Center", scrollActor);
		pnlBtnActores.add(btnNewActor);
		pnlBtnActores.add(btnDelActor);
		pnlBtnActores.add(btnUpdActor);
		pnlBtnActores.add(btnSeeActor);
		actores.add("South", pnlBtnActores);

		peliculas.add("Center", scrollPelicula);
		pnlBtnPeliculas.add(btnNewPelicula);
		pnlBtnPeliculas.add(btnDelPelicula);
		pnlBtnPeliculas.add(btnUpdPelicula);
		pnlBtnPeliculas.add(btnSeePelicula);
		peliculas.add("South", pnlBtnPeliculas);

		reparto.add("Center", scrollReparto);
		pnlBtnReparto.add(btnNewReparto);
		pnlBtnReparto.add(btnDelReparto);
		pnlBtnReparto.add(btnUpdReparto);
		pnlBtnReparto.add(btnSeeReparto);
		reparto.add("South", pnlBtnReparto);

		pestañas.addTab("actores", actores);
		pestañas.addTab("películas", peliculas);
		pestañas.addTab("reparto", reparto);
		add(pestañas);

		tablaActor.setDefaultEditor(Object.class, null);
		tablaPelicula.setDefaultEditor(Object.class, null);
		tablaReparto.setDefaultEditor(Object.class, null);

		tablaActor.getTableHeader().setReorderingAllowed(false);
		tablaPelicula.getTableHeader().setReorderingAllowed(false);
		tablaReparto.getTableHeader().setReorderingAllowed(false);

		tablaActor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPelicula.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaReparto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		btnNewActor.addActionListener(this);
		btnDelActor.addActionListener(this);
		btnUpdActor.addActionListener(this);

		setSize(500, 300);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Principal();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnNewActor)) {
			aa.setVisible(true);
		}
		if (o.equals(btnDelActor)) {
			ba.setVisible(true);
		}
		if(o.equals(btnUpdActor)) {
			ma.setVisible(true);
		}
	}

}
