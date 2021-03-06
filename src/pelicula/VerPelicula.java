package pelicula;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Pelicula;
import clasesDAO.PeliculaDAO;
import main.Principal;

public class VerPelicula extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();

	JLabel lblTitulo = new JLabel("T�tulo: ");
	JLabel lblAnyo = new JLabel("A�o: ");
	JLabel lblGenero = new JLabel("G�nero: ");
	JLabel lblDirector = new JLabel("Director: ");

	JLabel txtTitulo = new JLabel();
	JLabel txtAnyo = new JLabel();
	JLabel txtGenero = new JLabel();
	JLabel txtDirector = new JLabel();

	JButton btnCerrar = new JButton("Cerrar");

	public VerPelicula() {

		setTitle("Modificar Actor");
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		panelDatos.setLayout(new GridLayout(4, 2));
		panelDatos.setBorder(new EmptyBorder(0, 10, 0, 10));
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
	}

	public static void main(String[] args) {
		new VerPelicula();
	}

	public void actionPerformed(ActionEvent e) {
		setVisible(!e.getSource().equals(btnCerrar));
	}

	public void cargarDatos(int id) {
		Pelicula p = PeliculaDAO.buscarPorID(Principal.idPelicula);
		txtTitulo.setText(p.getTitulo());
		txtAnyo.setText(p.getAnio() + "");
		txtGenero.setText(p.getGenero());
		txtDirector.setText(p.getDirector());
	}
}
