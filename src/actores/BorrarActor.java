package actores;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BorrarActor extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	
	JLabel borrado = new JLabel("Desea borrar el actor:");
	JLabel actor = new JLabel("");
	
	JButton btnAceptar = new JButton("Aceptar");
	JButton btnCancelar = new JButton("Cancelar");
	
	
	public BorrarActor() {
		setTitle("Borrar Actor");
		setSize(250,150);
		setResizable(false);
		setLayout(new FlowLayout());
		
		add(borrado);
		add(actor);
		add(btnAceptar);
		add(btnCancelar);
		
		btnAceptar.addActionListener(this);
		btnCancelar.addActionListener(this);
		
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(false);
	}


	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o.equals(btnAceptar)) {
			setVisible(false);
		}else {
			setVisible(false);
		}
		
	}
	
	public static void main(String[] args) {
		new BorrarActor();
	}
	
	

}
