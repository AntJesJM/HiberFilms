package reparto;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import antJesJM.hf.Principal;
import clases.Reparto;
import clasesDAO.RepartoDAO;

public class BorrarReparto extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JLabel lblBorrado = new JLabel("¿Desea borrar esta entrada de reparto?:");
	JLabel lblReparto1 = new JLabel();
	JLabel lblReparto2 = new JLabel();
	JButton btnAceptar = new JButton("Aceptar");
	JButton btnCancelar = new JButton("Cancelar");
	JPanel pnlBotones = new JPanel(new GridLayout(1, 2));

	Reparto reparto;

	public BorrarReparto() {
		setTitle("Borrar Reparto");
		setSize(250, 150);
		setResizable(false);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		add(lblBorrado);
		add(lblReparto1);
		add(lblReparto2);
		pnlBotones.add(btnAceptar);
		pnlBotones.add(btnCancelar);
		add(pnlBotones);

		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(false);
		//setSize(500, 400);
	}

	public void cargarDatos(int id) {
		reparto = RepartoDAO.buscarPorID(id);
		lblReparto1.setText(reparto.getActor().getApellido() + ", " + reparto.getActor().getNombre());
		lblReparto2.setText("como: "+ reparto.getPapel() + " en " + reparto.getPelicula().getTitulo());
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnAceptar)) {
			RepartoDAO.borrar(reparto);
			Principal.ActualizarTablas();
			setVisible(false);
		} else {
			setVisible(false);
		}
	}

	public static void main(String[] args) {
		new BorrarReparto();
	}

}