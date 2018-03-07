package actor;

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

import clases.Actor;
import clasesDAO.ActorDAO;
import main.Principal;

public class AnyadirActor extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JDialog dlgConfirmar = new JDialog();
	JLabel lblConf = new JLabel("¿Está seguro de que quiere añadir?");
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

	JButton btnConfirm = new JButton("Grabar");
	JButton btnCancel = new JButton("Cancelar");

	public AnyadirActor() {

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

		setTitle("Añadir Actor");
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);

		dlgConfirmar.setTitle("Confirmación");
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
		new AnyadirActor();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnCancel)) {
			setVisible(false);
			txtNombre.setText("");
			txtApellido.setText("");
			txtEdad.setText("");
			txtNacionalidad.setText("");
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
				JOptionPane.showMessageDialog(getContentPane(), "Si es menor de 10 años introduzca \n un 0 precediendo la edad", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
			setVisible(false);
			crearActor();
			txtNombre.setText("");
			txtApellido.setText("");
			txtEdad.setText("");
			txtNacionalidad.setText("");
		} }else if (o.equals(btnCancelDia)) {
			dlgConfirmar.setVisible(false);
		}

	}

	public void crearActor() {
		List<Actor> buscar = ActorDAO.buscarTodos();
		int posicion = 0;
		for (Actor a : buscar) {
			if (a.getIdActor() > posicion)
				posicion = a.getIdActor();

		}
		posicion++;
		Actor ac = new Actor(txtNombre.getText().trim(), txtApellido.getText().trim(), txtNacionalidad.getText().trim(),
				Integer.parseInt(txtEdad.getText().trim()));
		ActorDAO.guardar(ac);
		Principal.actualizarActor();
		setVisible(false);
	}

}
