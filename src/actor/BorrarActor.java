package actor;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import antJesJM.hf.Principal;
import clases.Actor;
import clasesDAO.ActorDAO;
import clasesDAO.PeliculaDAO;

public class BorrarActor extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JLabel lblBorrado = new JLabel("Desea borrar el actor:");
	JLabel lblActor = new JLabel("");

	JButton btnAceptar = new JButton("Aceptar");
	JButton btnCancelar = new JButton("Cancelar");

	Actor ac;
	
	public BorrarActor() {
		setTitle("Borrar Actor");
		setSize(250, 150);
		setResizable(false);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);

		add(lblBorrado);
		add(lblActor);
		add(btnAceptar);
		add(btnCancelar);

		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(false);
	}
	
	public void cargarDatos(int id) {
		ac = ActorDAO.buscarPorID(id);
		lblActor.setText(ac.getNombre()+" "+ac.getApellido());
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnAceptar)) {
			ActorDAO.borrar(ac);
			Principal.ActualizarTablas();
			setVisible(false);
		} else {
			setVisible(false);
		}

	}

	public static void main(String[] args) {
		new BorrarActor();
	}

}
