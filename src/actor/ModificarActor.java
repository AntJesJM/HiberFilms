package actor;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import clases.Actor;
import clasesDAO.ActorDAO;
import main.Principal;

public class ModificarActor extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	JDialog dlgConfirmar = new JDialog();
	JLabel lblConf = new JLabel("�Est� seguro de que quiere modificar?");
	JButton btnConfirmDia = new JButton("Aceptar");
	JButton btnCancelDia = new JButton("Cancelar");

	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();

	JLabel lblNombre = new JLabel("Nombre: ");
	JLabel lblApellido = new JLabel("Apellido: ");
	JLabel lblNacionalidad = new JLabel("Nacionalidad: ");
	JLabel lblEdad = new JLabel("Edad: ");

	JFormattedTextField txtNombre;
	JFormattedTextField txtApellido;
	JFormattedTextField txtNacionalidad;
	JFormattedTextField txtEdad;

	JButton btnConfirm = new JButton("Confirmar");
	JButton btnCancel = new JButton("Cancelar");

	Actor ac;

	public ModificarActor() {
		setTitle("Modificar Actor");
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);

		MaskFormatter maskNombre = null;
		MaskFormatter maskApellido = null;
		MaskFormatter maskNacionalidad = null;
		MaskFormatter maskEdad = null;

		try {
			maskNombre = new MaskFormatter("********************");
			maskApellido = new MaskFormatter("**********************************************************************");
			maskNacionalidad = new MaskFormatter("********************");
			maskEdad = new MaskFormatter("##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		txtNombre = new JFormattedTextField(maskNombre);
		txtApellido = new JFormattedTextField(maskApellido);
		txtNacionalidad = new JFormattedTextField(maskNacionalidad);
		txtEdad = new JFormattedTextField(maskEdad);

		dlgConfirmar.setTitle("Confirmaci�n");
		panelDatos.setBorder(new EmptyBorder(0, 10, 0, 10));
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
		panelBotones.add(btnCancel);

		add(panelDatos);
		add(panelBotones);

		btnConfirm.addActionListener(this);
		btnCancel.addActionListener(this);

		btnConfirmDia.addActionListener(this);
		btnCancelDia.addActionListener(this);

		dlgConfirmar.setLayout(new FlowLayout());
		dlgConfirmar.add(lblConf);
		dlgConfirmar.add(btnConfirmDia);
		dlgConfirmar.add(btnCancelDia);
		dlgConfirmar.setSize(250, 150);
		dlgConfirmar.setVisible(false);
		dlgConfirmar.setLocationRelativeTo(null);
		setVisible(false);

		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	public static void main(String[] args) {
		new ModificarActor();
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
			if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty()
					|| txtNacionalidad.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe rellenar todos los campos", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (txtEdad.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Si es menor de 10 a�os introduzca \n un 0 precediendo la edad", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				actualizarActor();
				setVisible(false);
			}
		} else if (o.equals(btnCancelDia)) {
			dlgConfirmar.setVisible(false);
		}

	}

	public void actualizarActor() {
		ac = ActorDAO.buscarPorID(Principal.idActor);
		ac.setNombre(txtNombre.getText().trim());
		ac.setApellido(txtApellido.getText().trim());
		ac.setNacionalidad(txtNacionalidad.getText().trim());
		ac.setEdad(Integer.parseInt(txtEdad.getText().trim()));

		ActorDAO.modificar(ac);
		Principal.actualizarActor();
		Principal.actualizarReparto();
		setVisible(false);
	}

	public void cargarDatos(int id) {
		Actor a = ActorDAO.buscarPorID(Principal.idActor);
		txtNombre.setValue(a.getNombre());
		txtApellido.setValue(a.getApellido());
		txtNacionalidad.setValue(a.getNacionalidad());
		txtEdad.setValue(a.getEdad() + "");
	}

}
