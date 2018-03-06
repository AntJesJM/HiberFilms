package reparto;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import actor.AnyadirActor;
import antJesJM.hf.Principal;
import clases.Actor;
import clases.Pelicula;
import clases.Reparto;
import clasesDAO.ActorDAO;
import clasesDAO.PeliculaDAO;
import clasesDAO.RepartoDAO;

public class ModificarReparto extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	static ArrayList idActores = new ArrayList();
	static ArrayList idPeliculas = new ArrayList();
	boolean prem;

	JDialog dlgConfirmar = new JDialog();
	JLabel lblConf = new JLabel("¿Está seguro de querer modificar?");
	JButton btnConfirmDia = new JButton("Aceptar");
	JButton btnCancelDia = new JButton("Cancelar");
	JRadioButton optSi = new JRadioButton("Si", true);
	JRadioButton optNo = new JRadioButton("No", false);

	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();
	JPanel panelGrupoBtns = new JPanel();

	JLabel lblActor = new JLabel("Actor: ");
	JLabel lblPelicula = new JLabel("Pelicula: ");
	JLabel lblPapel = new JLabel("Papel interpretado: ");
	JLabel lblPremio = new JLabel("¿Ha recibido algun premio?: ");

	static Choice cActor = new Choice();
	static Choice cPelicula = new Choice();
	JTextField txtPapel = new JTextField();
	ButtonGroup groupPremio = new ButtonGroup();

	JButton btnGuardar = new JButton("Guardar");
	JButton btnCancelar = new JButton("Cancelar");
	// Reparto reprt;

	public ModificarReparto()  {
		Principal.ActualizarTablas();
		setTitle("Modificar Reparto");
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		rellenarActores();
		rellenarPeliculas();
		MaskFormatter maskPapel=null;
		try {
			maskPapel = new MaskFormatter("********************");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtPapel = new JFormattedTextField(maskPapel);

		dlgConfirmar.setTitle("Confirmación");
		panelDatos.setBorder(new EmptyBorder(0, 10, 0, 10));
		panelDatos.setLayout(new GridLayout(4, 2));
		panelBotones.setLayout(new FlowLayout());
		panelGrupoBtns.setLayout(new GridLayout(1, 2));
		setLayout(new GridLayout(2, 1));

		groupPremio.add(optSi);
		groupPremio.add(optNo);

		panelGrupoBtns.add(optSi);
		panelGrupoBtns.add(optNo);

		panelDatos.add(lblActor);
		panelDatos.add(cActor);
		panelDatos.add(lblPelicula);
		panelDatos.add(cPelicula);
		panelDatos.add(lblPapel);
		panelDatos.add(txtPapel);
		panelDatos.add(lblPremio);
		panelDatos.add(panelGrupoBtns);

		panelBotones.add(btnGuardar);
		panelBotones.add(btnCancelar);

		add(panelDatos);
		add(panelBotones);

		btnGuardar.addActionListener(this);
		btnCancelar.addActionListener(this);
		btnConfirmDia.addActionListener(this);
		btnCancelDia.addActionListener(this);

		dlgConfirmar.setLayout(new FlowLayout());
		dlgConfirmar.add(lblConf);
		dlgConfirmar.add(btnConfirmDia);
		dlgConfirmar.add(btnCancelDia);
		dlgConfirmar.setSize(250, 150);
		dlgConfirmar.setVisible(false);
		dlgConfirmar.setLocationRelativeTo(null);
		setVisible(false);

		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	public static void main(String[] args) throws ParseException {
		new ModificarReparto();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnCancelar)) {
			setVisible(false);
		} else if (o.equals(btnGuardar)) {
			dlgConfirmar.setVisible(true);

		}
		if (o.equals(btnConfirmDia)) {
			dlgConfirmar.setVisible(false);
			if (txtPapel.getText().isEmpty()) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe rellenar el campo papel", "Error",
						JOptionPane.ERROR_MESSAGE);
			}else {
				actualizarReparto();
				setVisible(false);
			}
			
		} else if (o.equals(btnCancelDia)) {
			dlgConfirmar.setVisible(false);
		}

	}

	public static void rellenarActores() {
		Principal.ActualizarTablas();
		cActor.removeAll();
		idActores.clear();
		// cActor.add("Seleccione actor");
		List<Actor> busqActor = ActorDAO.buscarTodos();
		for (Actor a : busqActor) {
			idActores.add(a.getIdActor());
			cActor.addItem(a.getApellido() + ", " + a.getNombre());
		}
	}

	public static void rellenarPeliculas() {
		cPelicula.removeAll();
		idPeliculas.clear();
		// cPelicula.add("Seleccione pelicula");
		List<Pelicula> busqPel = PeliculaDAO.buscarTodos();
		for (Pelicula pel : busqPel) {
			idPeliculas.add(pel.getIdPelicula());
			cPelicula.addItem(pel.getTitulo());
		}
	}

	public void cargarDatos(int id) {
		rellenarActores();
		rellenarPeliculas();
		Reparto reprt = RepartoDAO.buscarPorID(id);

		for (int i = 0; i < idPeliculas.size(); i++) {
			if (idPeliculas.get(i).equals(reprt.getPelicula().getIdPelicula())) {
				cPelicula.select(i);
			}

		}
		for (int i = 0; i < idActores.size(); i++) {
			if (idActores.get(i).equals(reprt.getActor().getIdActor())) {
				cActor.select(i);
			}

		}

		txtPapel.setText(reprt.getPapel());
		if (reprt.isPremio() == true) {
			optSi.setSelected(true);
		} else {
			optNo.setSelected(true);
		}
		
	}

	public void actualizarReparto() {

		if (groupPremio.getSelection() == optSi) {
			prem = true;
		} else {
			prem = false;
		}
		int idActorCho = cActor.getSelectedIndex();
		int idPeliculaCho = cPelicula.getSelectedIndex();

		Pelicula peli = PeliculaDAO.buscarPorID((Integer) idPeliculas.get(idPeliculaCho));
		Actor actor = ActorDAO.buscarPorID((Integer) idActores.get(idActorCho));
		Reparto reprt = RepartoDAO.buscarPorID(Principal.idReparto);
		reprt.setPapel(txtPapel.getText());
		reprt.setPremio(prem);
		reprt.setActor(actor);
		reprt.setPelicula(peli);
		RepartoDAO.modificar(reprt);
		Principal.ActualizarTablas();
		setVisible(false);
	}

}
