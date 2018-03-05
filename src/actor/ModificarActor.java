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
import javax.swing.text.MaskFormatter;

import antJesJM.hf.Principal;
import clases.Actor;
import clases.Pelicula;
import clasesDAO.ActorDAO;
import clasesDAO.PeliculaDAO;

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

	
	JFormattedTextField txtNombre;
	JFormattedTextField txtApellido;
	JFormattedTextField txtNacionalidad;
	JFormattedTextField txtEdad;
	
	JButton btnConfirm = new JButton("Confirmar");
	JButton btnCancelar = new JButton("Cancelar");
	
	Actor ac;

	public ModificarActor() {
		setTitle("Modificar Actor");
		setSize(300, 200);
		setResizable(false);
		
		MaskFormatter maskNombre = null;
		MaskFormatter maskApellido = null;
		MaskFormatter maskNacionalidad = null;
		MaskFormatter maskEdad = null;
		
		try {
			maskNombre = new MaskFormatter("********************");
			maskApellido = new MaskFormatter("**********************************************************************");
			maskNacionalidad = new MaskFormatter("********************");
			maskEdad = new MaskFormatter("###");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		txtNombre = new JFormattedTextField(maskNombre);
		txtApellido = new JFormattedTextField(maskApellido);
		txtNacionalidad = new JFormattedTextField(maskNacionalidad);
		txtEdad = new JFormattedTextField(maskEdad);

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
			if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty()
					|| txtNacionalidad.getText().trim().isEmpty() || txtEdad.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(getContentPane(), "Debe rellenar todos los campos", "Error",
						JOptionPane.ERROR_MESSAGE);
			}  else {
				actualizarActor();
				setVisible(false);
			}

		}
		if (o.equals(btnconfirm)) {
			dlgconfirmar.setVisible(false);
			setVisible(false);
		} else if (o.equals(btncanc)) {
			dlgconfirmar.setVisible(false);
		}

	}
	
	public void actualizarActor() {
		ac = ActorDAO.buscarPorID(Principal.idActor);
		ac.setNombre(txtNombre.getText().trim());
		ac.setApellido(txtApellido.getText().trim());
		ac.setNacionalidad(txtNacionalidad.getText().trim());
		ac.setEdad(Integer.parseInt(txtEdad.getText().trim()));
		
		ActorDAO.modificar(ac);
		Principal.ActualizarTablas();
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
