package pelicula;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BorrarPelicula extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JLabel lblBorrado = new JLabel("Desea borrar esta película:");
	JLabel lblPelicula = new JLabel("");

	JButton btnAceptar = new JButton("Aceptar");
	JButton btnCancelar = new JButton("Cancelar");

	public BorrarPelicula() {
		setTitle("Borrar Película");
		setSize(250, 150);
		setResizable(false);
		setLayout(new FlowLayout());

		add(lblBorrado);
		add(lblPelicula);
		add(btnAceptar);
		add(btnCancelar);

		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnAceptar)) {
			setVisible(false);
		} else {
			setVisible(false);
		}

	}

	public static void main(String[] args) {
		new BorrarPelicula();
	}

}
