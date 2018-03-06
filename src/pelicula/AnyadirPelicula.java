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
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import antJesJM.hf.Principal;
import clases.Pelicula;
import clasesDAO.PeliculaDAO;

public class AnyadirPelicula extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JDialog dlgConfirmar = new JDialog();
	JLabel lblConfirm = new JLabel("¿Está seguro de añadir esta entrada?");
	JButton btnConfirmDia = new JButton("Aceptar");
	JButton btnCancelDia = new JButton("Cancelar");

	JPanel panelDatos = new JPanel(new GridLayout(4, 2));
	JPanel panelBotones = new JPanel(new FlowLayout());

	JLabel lblTitulo = new JLabel("Título: ");
	JLabel lblAnyo = new JLabel("Año: ");
	JLabel lblGenero = new JLabel("Género: ");
	JLabel lblDirector = new JLabel("Director: ");

	JFormattedTextField txtTitulo;
	JFormattedTextField txtAnio;
	JFormattedTextField txtGenero;
	JFormattedTextField txtDirector;

	JButton btnConfirm = new JButton("Grabar");
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
		setLocationRelativeTo(null);
		setLayout(new GridLayout(2, 1));
		setVisible(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		panelDatos.setBorder(new EmptyBorder(0, 10, 0, 10));
		panelDatos.add(lblTitulo);
		panelDatos.add(txtTitulo);
		panelDatos.add(lblAnyo);
		panelDatos.add(txtAnio);
		panelDatos.add(lblGenero);
		panelDatos.add(txtGenero);
		panelDatos.add(lblDirector);
		panelDatos.add(txtDirector);
		panelBotones.add(btnConfirm);
		panelBotones.add(btnCancel);

		add(panelDatos);
		add(panelBotones);

		btnConfirm.addActionListener(this);
		btnCancel.addActionListener(this);
		btnConfirmDia.addActionListener(this);
		btnCancelDia.addActionListener(this);

		dlgConfirmar.setTitle("Confirmación");
		dlgConfirmar.setLayout(new FlowLayout());
		dlgConfirmar.setLocationRelativeTo(null);
		dlgConfirmar.add(lblConfirm);
		dlgConfirmar.add(btnConfirmDia);
		dlgConfirmar.add(btnCancelDia);
		dlgConfirmar.setSize(250, 150);
		dlgConfirmar.setVisible(false);

	}

	public static void main(String[] args) {
		new AnyadirPelicula();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnCancel)) {
			setVisible(false);
		} else if (o.equals(btnConfirm)) {
			dlgConfirmar.setVisible(true);

		}
		if (o.equals(btnConfirmDia)) {
			dlgConfirmar.setVisible(false);
			if (txtTitulo.getText().trim().isEmpty() || txtAnio.getText().trim().isEmpty()
					|| txtGenero.getText().trim().isEmpty() || txtDirector.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe rellenar todos los campos", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (Integer.parseInt(txtAnio.getText().trim()) < 1895) {
				JOptionPane.showMessageDialog(getContentPane(), "La primera película data de 1895", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				crearPelicula();
				setVisible(false);
			}
		}
		if (o.equals(btnCancelDia)) {
			dlgConfirmar.setVisible(false);
		}

	}

	public void crearPelicula() {
		List<Pelicula> buscTod = PeliculaDAO.buscarTodos();
		int posicion = 0;
		for (Pelicula p : buscTod) {
			if (p.getIdPelicula() > posicion)
				posicion = p.getIdPelicula();

		}
		posicion++;
		Pelicula peli = new Pelicula(txtTitulo.getText().trim(), Integer.parseInt(txtAnio.getText().trim()),
				txtGenero.getText().trim(), txtDirector.getText().trim());
		PeliculaDAO.guardar(peli);
		Principal.ActualizarTablas();
	}

}
