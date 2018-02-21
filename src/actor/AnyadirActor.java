package actor;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import antJesJM.hf.Principal;
import clases.Actor;
import clasesDAO.ActorDAO;

public class AnyadirActor extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JDialog dlgconfirmar = new JDialog();
	JLabel lblconf = new JLabel("¿Está seguro de que quiere añadir?");
	JButton btnconfirm = new JButton("Aceptar");
	JButton btncanc = new JButton("Cancelar");

	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();

	JLabel lblNombre = new JLabel("nombre");
	JLabel lblApellido = new JLabel("apellido");
	JLabel lblNacionalidad = new JLabel("nacionalidad");
	JLabel lblEdad = new JLabel("edad");

	static JTextField txtNombre = new JTextField();
	static JTextField txtApellido = new JTextField();
	static JTextField txtNacionalidad = new JTextField();
	static JTextField txtEdad = new JTextField();

	JButton btnGrabar = new JButton("Grabar");
	JButton btnCancelar = new JButton("Cancelar");

	public AnyadirActor() {
		setTitle("Añadir Actor");
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

		panelBotones.add(btnGrabar);
		panelBotones.add(btnCancelar);

		add(panelDatos);
		add(panelBotones);

		btnGrabar.addActionListener(this);
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
		new AnyadirActor();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnCancelar)) {
			setVisible(false);
		} else if (o.equals(btnGrabar)) {
			dlgconfirmar.setVisible(true);

		}
		if (o.equals(btnconfirm)) {
			dlgconfirmar.setVisible(false);
			setVisible(false);
			CrearActor();
		} else if (o.equals(btncanc)) {
			dlgconfirmar.setVisible(false);
		}

	}
	
	public static void VaciarActor() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtEdad.setText("");
		txtNacionalidad.setText("");
	}
	
	public void CrearActor() {
		List<Actor> buscar = ActorDAO.buscarTodos();
		int id = 0;
		
		int filas = Principal.tablaActor.getRowCount();
		
		for(Actor a:buscar) {
			if(a.getIdActor()>id) {
				id = a.getIdActor();
			}
		}
		id++;
		Actor actor = new Actor(txtNombre.getText(),txtApellido.getText(),txtNacionalidad.getText(),Integer.parseInt(txtEdad.getText()));
		ActorDAO.guardar(actor);
		Principal.ActualizarTabla();
		setVisible(false);
	}

}
