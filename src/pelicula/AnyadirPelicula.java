package pelicula;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import antJesJM.hf.Principal;
import clases.Pelicula;
import clasesDAO.PeliculaDAO;

public class AnyadirPelicula extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JDialog dlgConfirmar = new JDialog();
	JLabel lblConfirm = new JLabel("¿Está seguro de añadir esta entrada?");
	JButton btnAceptar = new JButton("Aceptar");
	JButton btnCancelar = new JButton("Cancelar");

	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();

	JLabel lblTitulo = new JLabel("Título ");
	JLabel lblAnyo = new JLabel("Año");
	JLabel lblGenero = new JLabel("Género");
	JLabel lblDirector = new JLabel("Director");

	JFormattedTextField txtTitulo;
	JFormattedTextField txtAnio;
	JFormattedTextField txtGenero;
	JFormattedTextField txtDirector;

	JButton btnGrabar = new JButton("Grabar");
	JButton btnCancel = new JButton("Cancelar");

	public AnyadirPelicula() {
		MaskFormatter maskAnio = null;
		MaskFormatter maskTitle = null;
		MaskFormatter maskGen = null;
		MaskFormatter maskDir = null;
		try {
			maskAnio = new MaskFormatter("####");
			maskTitle = new MaskFormatter("**********************************************************************");
			maskGen = new MaskFormatter("********************");
			maskDir = new MaskFormatter("******************************");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		txtAnio = new JFormattedTextField(maskAnio);
		txtTitulo = new JFormattedTextField(maskTitle);
		txtGenero = new JFormattedTextField(maskGen);
		txtDirector = new JFormattedTextField(maskDir);

		setTitle("Añadir Película");
		setSize(300, 200);
		setResizable(false);

		dlgConfirmar.setTitle("Confirmación");
		panelDatos.setLayout(new GridLayout(4, 2));
		panelBotones.setLayout(new FlowLayout());
		setLayout(new GridLayout(2, 1));

		panelDatos.add(lblTitulo);
		panelDatos.add(txtTitulo);
		panelDatos.add(lblAnyo);
		panelDatos.add(txtAnio);
		panelDatos.add(lblGenero);
		panelDatos.add(txtGenero);
		panelDatos.add(lblDirector);
		panelDatos.add(txtDirector);

		panelBotones.add(btnGrabar);
		panelBotones.add(btnCancel);

		add(panelDatos);
		add(panelBotones);

		btnGrabar.addActionListener(this);
		btnCancel.addActionListener(this);

		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		dlgConfirmar.setLayout(new FlowLayout());
		dlgConfirmar.add(lblConfirm);
		dlgConfirmar.add(btnAceptar);
		dlgConfirmar.add(btnCancelar);
		dlgConfirmar.setSize(250, 150);
		dlgConfirmar.setVisible(false);

		setVisible(false);

		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	public static void main(String[] args) {
		new AnyadirPelicula();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnCancel)) {
			setVisible(false);
		} else if (o.equals(btnGrabar)) {
			dlgConfirmar.setVisible(true);

		}
		if (o.equals(btnAceptar)) {
			dlgConfirmar.setVisible(false);
			setVisible(false);
			if (txtTitulo.getText().isEmpty() || txtAnio.getText().isEmpty() || txtGenero.getText().isEmpty()
					|| txtDirector.getText().isEmpty())
				JOptionPane.showMessageDialog(getContentPane(), "Rellene los campos vacíos", "Error",
						JOptionPane.ERROR_MESSAGE);

			else {
				CrearMecanico();
			}

		} else if (o.equals(btnCancelar)) {
			dlgConfirmar.setVisible(false);
		}

	}

	public void CrearMecanico() {
		List<Pelicula> buscTod = PeliculaDAO.buscarTodos();
		int id = 0;
		for (Pelicula p : buscTod) {
			if (p.getIdPelicula() > id)
				id = p.getIdPelicula();

		}
		id++;
		Pelicula peli = new Pelicula(txtTitulo.getText(),Integer.parseInt(txtAnio.getText()), txtGenero.getText(),txtDirector.getText());
		PeliculaDAO.guardar(peli);
//		Principal.ActualizarTablas();
		setVisible(false);
	}

}
