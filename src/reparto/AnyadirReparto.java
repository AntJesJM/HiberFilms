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

import clases.Actor;
import clases.Pelicula;
import clases.Reparto;
import clasesDAO.ActorDAO;
import clasesDAO.PeliculaDAO;
import clasesDAO.RepartoDAO;
import main.Principal;

public class AnyadirReparto extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	static ArrayList idActor = new ArrayList();
	@SuppressWarnings("rawtypes")
	static ArrayList idPelicula = new ArrayList();

	JDialog dlgConfirmar = new JDialog();
	JLabel lblConf = new JLabel("¿Está seguro de que quiere añadir?");
	JButton btnConfirmDia = new JButton("Aceptar");
	JButton btnCancelDia = new JButton("Cancelar");
	JRadioButton optSi = new JRadioButton("Si", true);
	JRadioButton optNo = new JRadioButton("No", false);

	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();
	JPanel panelGrupoBtns = new JPanel();

	JLabel lblActor = new JLabel("Actor: ");
	JLabel lblPelicula = new JLabel("Película: ");
	JLabel lblPapel = new JLabel("Papel interpretado: ");
	JLabel lblPremio = new JLabel("Galardonado: ");

	static Choice cActor = new Choice();
	static Choice cPelicula = new Choice();
	JTextField txtPapel = new JTextField();
	ButtonGroup groupPremio = new ButtonGroup();

	JButton btnConfirmar = new JButton("Guardar");
	JButton btnCancelar = new JButton("Cancelar");

	boolean prem;

	public AnyadirReparto() {

		setTitle("Añadir Reparto");
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		MaskFormatter maskPapel = null;
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

		panelBotones.add(btnConfirmar);
		panelBotones.add(btnCancelar);

		add(panelDatos);
		add(panelBotones);

		btnConfirmar.addActionListener(this);
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
		new AnyadirReparto();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnCancelar)) {
			setVisible(false);
			txtPapel.setText("");
		} else if (o.equals(btnConfirmar)) {
			dlgConfirmar.setVisible(true);

		}
		if (o.equals(btnConfirmDia)) {
			dlgConfirmar.setVisible(false);
			if (txtPapel.getText().isEmpty()) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe rellenar el campo papel", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				setVisible(false);
				crearReparto();
				txtPapel.setText("");
			}

		} else if (o.equals(btnCancelDia)) {
			dlgConfirmar.setVisible(false);
		}

	}

	@SuppressWarnings("unchecked")
	public static void rellenarActores() {
		cActor.removeAll();
		idActor.clear();
		List<Actor> busqActor = ActorDAO.buscarTodos();
		for (Actor a : busqActor) {
			idActor.add(a.getIdActor());
			cActor.addItem(a.getApellido() + ", " + a.getNombre());
		}
	}

	@SuppressWarnings("unchecked")
	public static void rellenarPeliculas() {
		cPelicula.removeAll();
		idPelicula.clear();
		List<Pelicula> busqPel = PeliculaDAO.buscarTodos();
		for (Pelicula pel : busqPel) {
			idPelicula.add(pel.getIdPelicula());
			cPelicula.addItem(pel.getTitulo());
		}
	}

	public void crearReparto() {
		if (optSi.isSelected()) {
			prem = true;
		} else {
			prem = false;
		}
		int idActorCho = cActor.getSelectedIndex();
		int idPeliculaCho = cPelicula.getSelectedIndex();
		Actor actor = ActorDAO.buscarPorID((Integer) idActor.get(idActorCho));
		Pelicula peli = PeliculaDAO.buscarPorID((Integer) idPelicula.get(idPeliculaCho));
		Reparto reprt = new Reparto(txtPapel.getText(), prem, peli, actor);
		RepartoDAO.guardar(reprt);
		Principal.actualizarReparto();
	}

}