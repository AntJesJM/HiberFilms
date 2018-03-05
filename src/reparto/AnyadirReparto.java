package reparto;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import antJesJM.hf.Principal;
import clases.Reparto;
import clasesDAO.RepartoDAO;

public class AnyadirReparto extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JDialog dlgConfirmar = new JDialog();
	JLabel lblConf = new JLabel("¿Está seguro de que quiere añadir?");
	JButton btnConfirm = new JButton("Aceptar");
	JButton btnCanc = new JButton("Cancelar");
	JRadioButton optSi = new JRadioButton("Si", true);
	JRadioButton optNo = new JRadioButton("No", false);

	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();
	JPanel panelGrupoBtns = new JPanel();

	JLabel lblActor = new JLabel("Actor");
	JLabel lblPelicula = new JLabel("Pelicula");
	JLabel lblPapel = new JLabel("Papel interpretado");
	JLabel lblPremio = new JLabel("¿Ha recibido algun premio?");

	Choice cActor = new Choice();
	Choice cPelicula = new Choice();
	JTextField txtPapel = new JTextField();
	ButtonGroup groupPremio = new ButtonGroup();

	JButton btnGuardar = new JButton("Guardar");
	JButton btnCancelar = new JButton("Cancelar");

	public AnyadirReparto() throws ParseException {
		setTitle("Añadir Reparto");
		setSize(300, 200);
		setResizable(false);
		MaskFormatter maskPapel = new MaskFormatter("********************");
		txtPapel = new JFormattedTextField(maskPapel);

		dlgConfirmar.setTitle("Confirmación");
		panelDatos.setLayout(new GridLayout(4, 2));
		panelBotones.setLayout(new FlowLayout());
		panelGrupoBtns.setLayout(new GridLayout(1, 2));
		setLayout(new GridLayout(2, 1));

		groupPremio.add(optSi);
		groupPremio.add(optNo);

		panelGrupoBtns.add(optSi);
		panelGrupoBtns.add(optNo);

		panelDatos.add(lblActor);
		panelDatos.add(cActor);
		panelDatos.add(lblPelicula);
		panelDatos.add(cPelicula);
		panelDatos.add(lblPapel);
		panelDatos.add(txtPapel);
		panelDatos.add(lblPremio);
		panelDatos.add(panelGrupoBtns);

		panelBotones.add(btnGuardar);
		panelBotones.add(btnCancelar);

		add(panelDatos);
		add(panelBotones);

		btnGuardar.addActionListener(this);
		btnCancelar.addActionListener(this);

		btnConfirm.addActionListener(this);
		btnCanc.addActionListener(this);

		dlgConfirmar.setLayout(new FlowLayout());
		dlgConfirmar.add(lblConf);
		dlgConfirmar.add(btnConfirm);
		dlgConfirmar.add(btnCanc);
		dlgConfirmar.setSize(250, 150);
		dlgConfirmar.setVisible(false);

		setVisible(false);

		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	public static void main(String[] args) throws ParseException {
		new AnyadirReparto();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnCancelar)) {
			setVisible(false);
		} else if (o.equals(btnGuardar)) {
			dlgConfirmar.setVisible(true);

		}
		if (o.equals(btnConfirm)) {
			dlgConfirmar.setVisible(false);
			setVisible(false);
		} else if (o.equals(btnCanc)) {
			dlgConfirmar.setVisible(false);
		}

	}

	public void CrearReparto() {
		List<Reparto> buscTod = RepartoDAO.buscarTodos();
		int posicion = 0;
		for (Reparto p : buscTod) {
			if (p.getIdReparto() > posicion)
				posicion = p.getIdReparto();

		}
		posicion++;
		// if (groupPremio.getSelection() == "si") {
		//
		// }
		// Reparto reprt = new Reparto(txtPapel.getText().trim(), valor,
		// cPelicula.getSelectedItem().toString().trim(),
		// cActor.getSelectedItem().toString().trim() );
		// RepartoDAO.guardar(reprt);
		Principal.ActualizarTablas();
		setVisible(false);
	}

}