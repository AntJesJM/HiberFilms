package reparto;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

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

import actor.AnyadirActor;
import antJesJM.hf.Principal;
import clases.Pelicula;
import clases.Reparto;
import clasesDAO.PeliculaDAO;
import clasesDAO.RepartoDAO;

public class ModificarReparto extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JDialog dlgConfirmar = new JDialog();
	JLabel lblConf = new JLabel("¿Está seguro de querer modificar?");
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
	Reparto reprt;

	public ModificarReparto() throws ParseException {
		setTitle("Añadir Reparto");
		setSize(300, 200);
		setResizable(false);
		MaskFormatter maskPapel = new MaskFormatter("********************");
		txtPapel= new JFormattedTextField(maskPapel);

		dlgConfirmar.setTitle("Confirmación");
		panelDatos.setLayout(new GridLayout(4, 2));
		panelBotones.setLayout(new FlowLayout());
		panelGrupoBtns.setLayout(new GridLayout(1,2));
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
		new ModificarReparto();
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
//	public void actualizarReparto() {
//		reprt = RepartoDAO.buscarPorID(Principal.idPelicula);
//		reprt.setActor(cActor.getSelectedItem().trim());
//		reprt.setPelicula(cPelicula.getSelectedItem().trim());
//		reprt.setPapel(txtPapel.getText().trim());
//		reprt.setPremio();
//		RepartoDAO.modificar(reprt);
//		Principal.ActualizarTablas();
//		setVisible(false);
//	}
//
//	public void cargarDatos(int id) {
//		Pelicula p = PeliculaDAO.buscarPorID(Principal.idPelicula);
//		txtTitulo.setValue(p.getTitulo());
//		txtAnio.setValue(p.getAnio() + "");
//		txtGenero.setValue(p.getGenero());
//		txtDirector.setValue(p.getDirector());
//	}
}
