package pelicula;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import clases.Pelicula;
import clasesDAO.PeliculaDAO;
import main.Principal;

public class BorrarPelicula extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JLabel lblBorrado = new JLabel("¿Desea borrar esta película?:");
	JLabel lblPelicula = new JLabel();
	JButton btnAceptar = new JButton("Aceptar");
	JButton btnCancelar = new JButton("Cancelar");
	JPanel pnlBotones = new JPanel(new GridLayout(1, 2));

	Pelicula peli;

	public BorrarPelicula() {
		setTitle("Borrar Película");
		setSize(250, 150);
		setResizable(false);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		add(lblBorrado);
		add(lblPelicula);
		pnlBotones.add(btnAceptar);
		pnlBotones.add(btnCancelar);
		add(pnlBotones);

		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(false);
	}

	public void cargarDatos(int id) {
		peli = PeliculaDAO.buscarPorID(id);
		lblPelicula.setText(peli.getTitulo());
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnAceptar)) {
			PeliculaDAO.borrar(peli);
			Principal.actualizarPelicula();
			Principal.actualizarReparto();
			setVisible(false);
		} else {
			setVisible(false);
		}
	}

	public static void main(String[] args) {
		new BorrarPelicula();
	}

}
