package actor;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Actor;
import clasesDAO.ActorDAO;
import main.Principal;

public class VerActor extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();

	JLabel lblNombre = new JLabel("Nombre: ");
	JLabel lblApellido = new JLabel("Apellido: ");
	JLabel lblNacionalidad = new JLabel("Nacionalidad: ");
	JLabel lblEdad = new JLabel("Edad: ");

	JLabel txtNombre = new JLabel();
	JLabel txtApellido = new JLabel();
	JLabel txtNacionalidad = new JLabel();
	JLabel txtEdad = new JLabel();

	JButton btnCerrar = new JButton("Cerrar");

	public VerActor() {
		setTitle("Modificar Actor");
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		panelDatos.setLayout(new GridLayout(4, 2));
		panelDatos.setBorder(new EmptyBorder(0, 10, 0, 10));
		panelBotones.setLayout(new FlowLayout());
		setLayout(new GridLayout(2, 1));

		panelDatos.add(lblNombre);
		panelDatos.add(txtNombre);
		panelDatos.add(lblApellido);
		panelDatos.add(txtApellido);
		panelDatos.add(lblNacionalidad);
		panelDatos.add(txtNacionalidad);
		panelDatos.add(lblEdad);
		panelDatos.add(txtEdad);

		panelBotones.add(btnCerrar);

		add(panelDatos);
		add(panelBotones);

		btnCerrar.addActionListener(this);

		setVisible(false);

		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	public static void main(String[] args) {
		new VerActor();
	}

	public void actionPerformed(ActionEvent e) {

		setVisible(!e.getSource().equals(btnCerrar));

	}

	public void cargarDatos(int id) {
		Actor a = ActorDAO.buscarPorID(Principal.idActor);
		txtNombre.setText(a.getNombre());
		txtApellido.setText(a.getApellido());
		txtNacionalidad.setText(a.getNacionalidad());
		txtEdad.setText(a.getEdad() + "");

	}

}
