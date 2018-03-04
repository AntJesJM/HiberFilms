package pelicula;

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
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.transaction.Transactional.TxType;

import antJesJM.hf.Principal;
import clases.Pelicula;
import clasesDAO.PeliculaDAO;

public class ModificarPelicula extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	int idPelicula;
	JDialog dlgConfirmar = new JDialog();
	JLabel lblConfirm = new JLabel("¿Está seguro de que quiere modificar?");
	JButton btnAceptar = new JButton("Aceptar");
	JButton btnCancelar = new JButton("Cancelar");

	JPanel panelDatos = new JPanel();
	JPanel panelBotones = new JPanel();

	JLabel lblTitulo = new JLabel("Título ");
	JLabel lblAnyo = new JLabel("Año");
	JLabel lblGenero = new JLabel("Género");
	JLabel lblDirector = new JLabel("Director");

	JFormattedTextField txtTitulo;
	JFormattedTextField txtAnyo;
	JFormattedTextField txtGenero;
	JFormattedTextField txtDirector;

	JButton btnConfirm = new JButton("Confirmar");
	JButton btnCancel = new JButton("Cancelar");

	public ModificarPelicula() {
		setTitle("Modificar Película");
		setSize(300, 200);
		setResizable(false);
		MaskFormatter maskAnio = null;
		MaskFormatter maskTitle = null;
		MaskFormatter maskGen = null;
		MaskFormatter maskDir = null;
		try {
			maskAnio = new MaskFormatter("####");
			maskTitle = new MaskFormatter(
					"**********************************************************************");
			maskGen = new MaskFormatter("********************");
			maskDir = new MaskFormatter("******************************");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		txtAnyo = new JFormattedTextField(maskAnio);
		txtTitulo = new JFormattedTextField(maskTitle);
		txtGenero = new JFormattedTextField(maskGen);
		txtDirector = new JFormattedTextField(maskDir);

		dlgConfirmar.setTitle("Confirmación");
		panelDatos.setLayout(new GridLayout(4, 2));
		panelBotones.setLayout(new FlowLayout());
		setLayout(new GridLayout(2, 1));

		panelDatos.add(lblTitulo);
		panelDatos.add(txtTitulo);
		panelDatos.add(lblAnyo);
		panelDatos.add(txtAnyo);
		panelDatos.add(lblGenero);
		panelDatos.add(txtGenero);
		panelDatos.add(lblDirector);
		panelDatos.add(txtDirector);

		panelBotones.add(btnConfirm);
		panelBotones.add(btnCancel);

		add(panelDatos);
		add(panelBotones);

		btnConfirm.addActionListener(this);
		btnCancel.addActionListener(this);

		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);

		dlgConfirmar.setLayout(new FlowLayout());
		dlgConfirmar.add(lblConfirm);
		dlgConfirmar.add(btnAceptar);
		dlgConfirmar.add(btnCancelar);
		dlgConfirmar.setSize(250, 150);
		dlgConfirmar.setVisible(false);

		setVisible(false);

		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	public static void main(String[] args) {
		new ModificarPelicula();
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnCancel)) {
			setVisible(false);
		} else if (o.equals(btnConfirm)) {
			dlgConfirmar.setVisible(true);

		}
		if (o.equals(btnAceptar)) {
			dlgConfirmar.setVisible(false);
			setVisible(false);
			if(txtTitulo.getText().isEmpty()||txtAnyo.getText().isEmpty()||txtGenero.getText().isEmpty()||txtDirector.getText().isEmpty())
				JOptionPane.showMessageDialog(getContentPane(), "Faltan datos por introducir", "Error", JOptionPane.ERROR_MESSAGE);
			else
			{guardarPelicula();}
		} else if (o.equals(btnCancelar)) {
			dlgConfirmar.setVisible(false);
		}
	}

	public void guardarPelicula(){
		Pelicula p=PeliculaDAO.buscarPorID(idPelicula);
		p.setTitulo((txtTitulo.getText()));
		p.setAnio(Integer.parseInt(txtAnyo.getText()));
		p.setGenero(txtGenero.getText());
		p.setDirector(txtDirector.getText());
		PeliculaDAO.modificar(p);
		ActualizarTabla();
		setVisible(false);
	}
	
	public void ActualizarTabla(){
		List<Pelicula> buscTod = PeliculaDAO.buscarTodos();
//		DefaultTableModel modelo=(DefaultTableModel) Principal.tablaPelicula.getModel();
//		int filas=Principal.tablaPelicula.getRowCount();
//		for (int i=0;filas>i;i++)
//			 modelo.removeRow(0);
//		List<Pelicula> busqueda2 = PeliculaDAO.buscarTodos();
//
//		for (Pelicula p : busqueda2)
//		{
//			modelo.addRow(new Object[]{p.getIdPelicula(),p.getTitulo(),p.getAnio()});
//		
//		}
	
	}
	public void CargarDatos(int id)
	{
		Pelicula p = PeliculaDAO.buscarPorID(idPelicula);
		txtTitulo.setText(p.getTitulo());
		txtAnyo.setText(p.getAnio() + "");
		txtGenero.setText(p.getGenero());
		txtDirector.setText(p.getDirector());
		
	}

}
