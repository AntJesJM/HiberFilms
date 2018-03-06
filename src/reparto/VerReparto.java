package reparto;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Reparto;
import clasesDAO.RepartoDAO;
import main.Principal;


public class VerReparto extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();

	JLabel lblActor = new JLabel("Actor: ");
	JLabel lblPelicula = new JLabel("Pelicula: ");
	JLabel lblPapel = new JLabel("Papel: ");
	JLabel lblPremio = new JLabel("Galardónado: ");

	JLabel txtActor = new JLabel();
	JLabel txtPelicula = new JLabel();
	JLabel txtPapel = new JLabel();
	JLabel txtPremio = new JLabel();

	JButton btnCerrar = new JButton("Cerrar");

	public VerReparto() {

		setTitle("Modificar Reparto");
		setSize(300, 200);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		panelDatos.setLayout(new GridLayout(4, 2));
		panelDatos.setBorder(new EmptyBorder(0, 10, 0, 10));
		panelBotones.setLayout(new FlowLayout());
		setLayout(new GridLayout(2, 1));

		panelDatos.add(lblActor);
		panelDatos.add(txtActor);
		panelDatos.add(lblPelicula);
		panelDatos.add(txtPelicula);
		panelDatos.add(lblPapel);
		panelDatos.add(txtPapel);
		panelDatos.add(lblPremio);
		panelDatos.add(txtPremio);

		panelBotones.add(btnCerrar);
		add(panelDatos);
		add(panelBotones);

		btnCerrar.addActionListener(this);
		setVisible(false);
	}

	public static void main(String[] args) {
		new VerReparto();
	}

	public void actionPerformed(ActionEvent e) {
		setVisible(!e.getSource().equals(btnCerrar));
	}

	public void cargarDatos(int id) {
		Reparto reprt = RepartoDAO.buscarPorID(Principal.idReparto);
		String premiado;
		if (reprt.isPremio() == true) {
			 premiado = "Tiene un premio.";
		} else {
			 premiado = "No tiene premio.";
		}
		txtActor.setText(reprt.getActor().getApellido() + ", " + reprt.getActor().getNombre());
		txtPelicula.setText(reprt.getPelicula().getTitulo());
		txtPapel.setText(reprt.getPapel());
		txtPremio.setText(premiado);
	}
}