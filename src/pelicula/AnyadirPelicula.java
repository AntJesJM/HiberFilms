package pelicula;

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
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class AnyadirPelicula extends JFrame implements ActionListener  {

	private static final long serialVersionUID = 1L;
	
	JDialog dlgconfirmar = new JDialog();
	JLabel lblconf = new JLabel("¿Está seguro de que quiere añadir?");
	JButton btnconfirm = new JButton("Aceptar");
	JButton btncanc = new JButton("Cancelar");

	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();

	JLabel lblTitulo = new JLabel("Título ");
	JLabel lblAnyo = new JLabel("Año");
	JLabel lblGenero = new JLabel("Género");
	JLabel lblDirector = new JLabel("Director");

	JFormattedTextField txtTitulo;
	JFormattedTextField txtAnio;
	JFormattedTextField txtGenero;
	JFormattedTextField txtDirector;

	JButton btnGrabar = new JButton("Grabar");
	JButton btnCancelar = new JButton("Cancelar");

	public AnyadirPelicula() throws ParseException  {
		MaskFormatter maskAnio = new MaskFormatter("####");
		MaskFormatter maskTitle = new MaskFormatter("??????????????????????????????????????????????????????????????????????");
		MaskFormatter maskGen = new MaskFormatter("????????????????????");
		MaskFormatter maskDir = new MaskFormatter("??????????????????????????????");
		txtAnio= new JFormattedTextField(maskAnio);
		txtTitulo= new JFormattedTextField(maskTitle);
		txtGenero= new JFormattedTextField(maskGen);
		txtDirector= new JFormattedTextField(maskDir);
		
		setTitle("Añadir Película");
		setSize(300, 200);
		setResizable(false);

		dlgconfirmar.setTitle("Confirmación");
		panelDatos.setLayout(new GridLayout(4, 2));
		panelBotones.setLayout(new FlowLayout());
		setLayout(new GridLayout(2, 1));

		panelDatos.add(lblTitulo);
		panelDatos.add(txtTitulo);
		panelDatos.add(lblAnyo);
		panelDatos.add(txtAnio);
		panelDatos.add(lblGenero);
		panelDatos.add(txtGenero);
		panelDatos.add(lblDirector);
		panelDatos.add(txtDirector);

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

		setVisible(true);

		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	public static void main(String[] args) throws ParseException {
		new AnyadirPelicula();
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
		} else if (o.equals(btncanc)) {
			dlgconfirmar.setVisible(false);
		}

	}

}
