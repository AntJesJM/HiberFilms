package antJesJM.hf;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;

import actor.AnyadirActor;
import actor.BorrarActor;
import actor.ModificarActor;
import actor.VerActor;
import clases.Actor;
import clasesDAO.ActorDAO;
import reparto.AnyadirReparto;
import reparto.BorrarReparto;
import reparto.ModificarReparto;
import reparto.VerReparto;
import util.HibernateUtil;

public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static Session session = HibernateUtil.getSession();
	// Tabla Actor
	AnyadirActor aa = new AnyadirActor();
	BorrarActor ba = new BorrarActor();
	ModificarActor ma = new ModificarActor();
	VerActor va = new VerActor();
	// Tabla Pelicula

	// Tabla Reparto
	AnyadirReparto ar;
	BorrarReparto br = new BorrarReparto();
	ModificarReparto mr = new ModificarReparto();
	VerReparto vr = new VerReparto();

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

	static String[][] datosActor = {};

	public static JTable tablaActor;
	static JScrollPane scrollActor = new JScrollPane(tablaActor);

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

	static ArrayList idsActor = new ArrayList();

	public Principal() throws ParseException {
		ar = new AnyadirReparto();
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

		// Listeners del panel de Actor
		btnNewActor.addActionListener(this);
		btnDelActor.addActionListener(this);
		btnUpdActor.addActionListener(this);
		btnSeeActor.addActionListener(this);
		// Listeners del panel de Pelicula

		// Listeners del panel de Reparto
		btnNewReparto.addActionListener(this);
		btnDelReparto.addActionListener(this);
		btnUpdReparto.addActionListener(this);
		btnSeeReparto.addActionListener(this);

		setSize(500, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ActualizarTabla();
		
		tablaActor.setModel(
				new DefaultTableModel(new Object[][] {},new String[] { "Nombre", "Nacionalidad", "Edad" }));
	}

	public static void main(String[] args) throws ParseException {
		new Principal();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnNewActor)) {
			aa.setVisible(true);
			aa.VaciarActor();
		}
		if (o.equals(btnDelActor)) {
			ba.setVisible(true);
		}
		if (o.equals(btnUpdActor)) {
			ma.setVisible(true);
		}
		if (o.equals(btnSeeActor)) {
			va.setVisible(true);
		}

		// Acciones de los botones de Pelicula

		// Acciones de los botones de Reparto
		ar.setVisible(o.equals(btnNewReparto));
		// br.setVisible(o.equals(btnDelReparto));
		// mr.setVisible(o.equals(btnUpdReparto));
		// vr.setVisible(o.equals(btnSeeReparto));
		
	}

	public static void ActualizarTabla() {
		List<Actor> busqueda = ActorDAO.buscarTodos();
		DefaultTableModel modelo = (DefaultTableModel) Principal.tablaActor.getModel();
		int filas = Principal.tablaActor.getRowCount();
		for (int i = 0; filas > i; i++) {
			modelo.removeRow(0);
		}
		for (Actor a : busqueda) {
			idsActor.add(a.getIdActor());
			modelo.addRow(new Object[] { a.getNombre(), a.getNacionalidad(), a.getEdad() });
		}
	}

}
