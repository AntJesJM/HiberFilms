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

public class VerPelicula extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	int idPelicula;
	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();

	JLabel lblTitulo = new JLabel("Título ");
	JLabel lblAnyo = new JLabel("Año");
	JLabel lblGenero = new JLabel("Género");
	JLabel lblDirector = new JLabel("Director");

	JLabel txtTitulo = new JLabel();
	JLabel txtAnyo = new JLabel();
	JLabel txtGenero = new JLabel();
	JLabel txtDirector = new JLabel();

	JButton btnCerrar = new JButton("Cerrar");

	public VerPelicula() {

		setTitle("Modificar Actor");
		setSize(300, 200);
		setResizable(false);

		panelDatos.setLayout(new GridLayout(4, 2));
		panelBotones.setLayout(new FlowLayout());
		setLayout(new GridLayout(2, 1));

		panelDatos.add(lblTitulo);
		panelDatos.add(txtTitulo);
		panelDatos.add(lblAnyo);
		panelDatos.add(txtAnyo);
		panelDatos.add(lblGenero);
		panelDatos.add(txtGenero);
		panelDatos.add(lblDirector);
		panelDatos.add(txtDirector);

		panelBotones.add(btnCerrar);
		add(panelDatos);
		add(panelBotones);

		btnCerrar.addActionListener(this);
		setVisible(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	public static void main(String[] args) {
		new VerPelicula();
	}

	public void actionPerformed(ActionEvent e) {
		// Object o = e.getSource();
		setVisible(!e.getSource().equals(btnCerrar));
	}

	public void mostrarInfo(int id) {
		Pelicula p = PeliculaDAO.buscarPorID(idPelicula);
		txtTitulo.setText(p.getTitulo());
		txtAnyo.setText(p.getAnio() + "");
		txtGenero.setText(p.getGenero());
		txtDirector.setText(p.getDirector());
	}
}
