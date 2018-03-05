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
import javax.swing.JOptionPane;
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
import clases.Pelicula;
import clases.Reparto;
import clasesDAO.ActorDAO;
import clasesDAO.PeliculaDAO;
import clasesDAO.RepartoDAO;
import pelicula.AnyadirPelicula;
import pelicula.BorrarPelicula;
import pelicula.ModificarPelicula;
import pelicula.VerPelicula;
import reparto.AnyadirReparto;
import reparto.BorrarReparto;
import reparto.ModificarReparto;
import reparto.VerReparto;
import util.HibernateUtil;

public class Principal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static Session session = HibernateUtil.getSession();
	int unselected = -1;
	// Tabla Actor
	AnyadirActor addActor = new AnyadirActor();
	BorrarActor delActor = new BorrarActor();
	ModificarActor modActor = new ModificarActor();
	VerActor verActor = new VerActor();

	// Tabla Reparto
	AnyadirReparto addReparto;
	BorrarReparto delReparto = new BorrarReparto();
	ModificarReparto modReparto = new ModificarReparto();
	VerReparto verReparto = new VerReparto();

	// Tabla Pelicula
	AnyadirPelicula addPel = new AnyadirPelicula();
	BorrarPelicula delPel = new BorrarPelicula();
	ModificarPelicula modPel = new ModificarPelicula();
	VerPelicula verPel = new VerPelicula();
	
	public static int idPelicula;
	public static int idActor;
	public static int idReparto;

	JTabbedPane pestañas = new JTabbedPane();

	JPanel pnlActores = new JPanel(new BorderLayout());
	JPanel pnlPelicula = new JPanel(new BorderLayout());
	JPanel pnlReparto = new JPanel(new BorderLayout());

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

	static ArrayList arrIdActor = new ArrayList();
	static ArrayList arrIdPelicula = new ArrayList();
	static ArrayList arrIdReparto = new ArrayList();

	public static JTable tablaActor = new JTable();
	static JScrollPane scrollActor = new JScrollPane(tablaActor);

	public static JTable tablaPelicula = new JTable();
	JScrollPane scrollPelicula = new JScrollPane(tablaPelicula);

	public static JTable tablaReparto = new JTable();
	JScrollPane scrollReparto = new JScrollPane(tablaReparto);

	public Principal() throws ParseException {
		addReparto = new AnyadirReparto();
		setResizable(false);
		setTitle("HiberFilms");
		setLayout(new GridLayout(1, 1));
		setLocationRelativeTo(null);
		scrollActor.setPreferredSize(new Dimension(400, 100));
		scrollPelicula.setPreferredSize(new Dimension(400, 100));
		scrollReparto.setPreferredSize(new Dimension(400, 100));

		pnlActores.add("Center", scrollActor);
		pnlBtnActores.add(btnNewActor);
		pnlBtnActores.add(btnDelActor);
		pnlBtnActores.add(btnUpdActor);
		pnlBtnActores.add(btnSeeActor);
		pnlActores.add("South", pnlBtnActores);

		pnlPelicula.add("Center", scrollPelicula);
		pnlBtnPeliculas.add(btnNewPelicula);
		pnlBtnPeliculas.add(btnDelPelicula);
		pnlBtnPeliculas.add(btnUpdPelicula);
		pnlBtnPeliculas.add(btnSeePelicula);
		pnlPelicula.add("South", pnlBtnPeliculas);

		pnlReparto.add("Center", scrollReparto);
		pnlBtnReparto.add(btnNewReparto);
		pnlBtnReparto.add(btnDelReparto);
		pnlBtnReparto.add(btnUpdReparto);
		pnlBtnReparto.add(btnSeeReparto);
		pnlReparto.add("South", pnlBtnReparto);

		pestañas.addTab("Actores", pnlActores);
		pestañas.addTab("Películas", pnlPelicula);
		pestañas.addTab("Reparto", pnlReparto);
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

		tablaActor
				.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Nacionalidad", "Edad" }));
		tablaPelicula.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Título", "Año", "Género" }));
		tablaReparto.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Actor", "Película", "Papel" }));
		
		// Listeners del panel de Actor
		btnNewActor.addActionListener(this);
		btnDelActor.addActionListener(this);
		btnUpdActor.addActionListener(this);
		btnSeeActor.addActionListener(this);
		// Listeners del panel de Pelicula
		btnNewPelicula.addActionListener(this);
		btnDelPelicula.addActionListener(this);
		btnUpdPelicula.addActionListener(this);
		btnSeePelicula.addActionListener(this);
		// Listeners del panel de Reparto
		btnNewReparto.addActionListener(this);
		btnDelReparto.addActionListener(this);
		btnUpdReparto.addActionListener(this);
		btnSeeReparto.addActionListener(this);

		setSize(500, 300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ActualizarTablas();
	}

	public static void main(String[] args) throws ParseException {
		new Principal();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnNewActor)) {
			addActor.setVisible(true);
		}
		if (o.equals(btnDelActor)) {
			if (tablaActor.getSelectedRow() == unselected) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe seleccionar un Actor.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} else {
				idActor = (Integer) arrIdActor.get(tablaActor.getSelectedRow());
				delActor.cargarDatos(idActor);
				delActor.setVisible(true);
			}
		}
		if (o.equals(btnUpdActor)) {
			if (tablaActor.getSelectedRow() == unselected) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe seleccionar un Actor.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} else {
				idActor = (Integer) arrIdActor.get(tablaActor.getSelectedRow());
				modActor.cargarDatos(idActor);
				modActor.setVisible(true);
			}
		}
		if (o.equals(btnSeeActor)) {
			if (tablaActor.getSelectedRow() == unselected) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe seleccionar un Actor.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} else {
				idActor = (Integer) arrIdActor.get(tablaActor.getSelectedRow());
				verActor.cargarDatos(idActor);
				verActor.setVisible(true);
			}
		}

		// Acciones de los botones de Pelicula
		if (o.equals(btnNewPelicula)) {
			addPel.setVisible(true);
		}
		if (o.equals(btnDelPelicula)) {
			if (tablaPelicula.getSelectedRow() == unselected) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe seleccionar una película.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} else {
				idPelicula = (Integer) arrIdPelicula.get(tablaPelicula.getSelectedRow());
				delPel.cargarDatos(idPelicula);
				delPel.setVisible(true);
			}
		}
		if (o.equals(btnUpdPelicula)) {
			if (tablaPelicula.getSelectedRow() == unselected) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe seleccionar una película.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} else {
				idPelicula = (Integer) arrIdPelicula.get(tablaPelicula.getSelectedRow());
				modPel.cargarDatos(idPelicula);
				modPel.setVisible(true);
			}
		}
		if (o.equals(btnSeePelicula)) {
			if (tablaPelicula.getSelectedRow() == unselected) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe seleccionar una película.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} else {
				idPelicula = (Integer) arrIdPelicula.get(tablaPelicula.getSelectedRow());
				verPel.cargarDatos(idPelicula);
				verPel.setVisible(true);
			}
		}
		
		// Acciones de los botones de Reparto
		if (o.equals(btnNewReparto)) {
			addReparto.setVisible(true);
		}
		if (o.equals(btnDelReparto)) {
			if (tablaReparto.getSelectedRow() == unselected) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe seleccionar un reparto.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} else {
				idReparto = (Integer) arrIdReparto.get(tablaReparto.getSelectedRow());
				delReparto.cargarDatos(idReparto);
				delReparto.setVisible(true);
			}
		}
		if (o.equals(btnUpdReparto)) {
			if (tablaReparto.getSelectedRow() == unselected) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe seleccionar un reparto.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} else {
				idReparto = (Integer) arrIdReparto.get(tablaReparto.getSelectedRow());
				//modReparto.cargarDatos(idReparto);
				modReparto.setVisible(true);
			}
		}
		
		if (o.equals(btnSeeReparto)) {
			if (tablaReparto.getSelectedRow() == unselected) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe seleccionar un reparto.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} else {
				idReparto = (Integer) arrIdReparto.get(tablaReparto.getSelectedRow());
				verReparto.cargarDatos(idReparto);
				verReparto.setVisible(true);
			}
		} 

	}

	@SuppressWarnings("unchecked")
	public static void ActualizarTablas() {
		// Resteo arrays ids
		arrIdActor.clear();
		arrIdPelicula.clear();
		arrIdReparto.clear();

		// Tabla actor
		List<Actor> buscActor = ActorDAO.buscarTodos();
		DefaultTableModel modActor = (DefaultTableModel) Principal.tablaActor.getModel();
		int contador = Principal.tablaActor.getRowCount();
		for (int i = 0; contador > i; i++) {
			modActor.removeRow(0);
		}
		for (Actor act : buscActor) {
			arrIdActor.add(act.getIdActor());
			modActor.addRow(new Object[] { act.getNombre(), act.getNacionalidad(), act.getEdad() });
		}
		// Tabla pelicula
		List<Pelicula> buscPelicula = PeliculaDAO.buscarTodos();
		DefaultTableModel modPelicula = (DefaultTableModel) Principal.tablaPelicula.getModel();
		int contador2 = Principal.tablaPelicula.getRowCount();
		for (int i = 0; contador2 > i; i++)
			modPelicula.removeRow(0);
		for (Pelicula pel : buscPelicula) {
			arrIdPelicula.add(pel.getIdPelicula());
			modPelicula.addRow(new Object[] { pel.getTitulo(), pel.getAnio(), pel.getGenero() });
		}
		// Tabla Reparto
		List<Reparto> buscReparto = RepartoDAO.buscarTodos();
		DefaultTableModel modReparto = (DefaultTableModel) Principal.tablaReparto.getModel();
		int contador3 = Principal.tablaReparto.getRowCount();
		for (int i = 0; contador3 > i; i++)
			modReparto.removeRow(0);
		for (Reparto rep : buscReparto) {
			arrIdReparto.add(rep.getIdReparto());
			modReparto
					.addRow(new Object[] { rep.getActor().getNombre(), rep.getPelicula().getTitulo(), rep.getPapel() });

		}
	}

}
