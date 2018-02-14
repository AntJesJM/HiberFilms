package actores;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ModificarActor extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JDialog dlgconfirmar = new JDialog();
	JLabel lblconf = new JLabel("¿Está seguro de que quiere modificar?");
	JButton btnconfirm = new JButton("Aceptar");
	JButton btncanc = new JButton("Cancelar");

	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();

	JLabel lblNombre = new JLabel("nombre");
	JLabel lblApellido = new JLabel("apellido");
	JLabel lblNacionalidad = new JLabel("nacionalidad");
	JLabel lblEdad = new JLabel("edad");

	JTextField txtNombre = new JTextField();
	JTextField txtApellido = new JTextField();
	JTextField txtNacionalidad = new JTextField();
	JTextField txtEdad = new JTextField();

	JButton btnConfirm = new JButton("Confirmar");
	JButton btnCancelar = new JButton("Cancelar");

	public ModificarActor() {
		setTitle("Modificar Actor");
		setSize(300, 200);
		setResizable(false);

		dlgconfirmar.setTitle("Confirmación");
		panelDatos.setLayout(new GridLayout(4, 2));
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

		panelBotones.add(btnConfirm);
		panelBotones.add(btnCancelar);

		add(panelDatos);
		add(panelBotones);

		btnConfirm.addActionListener(this);
		btnCancelar.addActionListener(this);

		btnconfirm.addActionListener(this);
		btncanc.addActionListener(this);

		dlgconfirmar.setLayout(new FlowLayout());
		dlgconfirmar.add(lblconf);
		dlgconfirmar.add(btnconfirm);
		dlgconfirmar.add(btncanc);
		dlgconfirmar.setSize(250, 150);
		dlgconfirmar.setVisible(false);

		setVisible(false);

		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	public static void main(String[] args) {
		new ModificarActor();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnCancelar)) {
			setVisible(false);
		} else if (o.equals(btnConfirm)) {
			dlgconfirmar.setVisible(true);

		}
		if (o.equals(btnconfirm)) {
			dlgconfirmar.setVisible(false);
			setVisible(false);
		} else if (o.equals(btncanc)) {
			dlgconfirmar.setVisible(false);
		}

	}

}
