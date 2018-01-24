package antJesJM.hf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.text.View;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;

	JTabbedPane pestañas = new JTabbedPane();
	JPanel actores = new JPanel();
	JPanel peliculas = new JPanel();
	JPanel reparto = new JPanel();

	JButton btnNew = new JButton("Nuevo");
	JButton btnDel = new JButton("Borrar");
	JButton btnUpd = new JButton("Modificar");
	JButton btnSee = new JButton("Ver");
	String[][] datos = { { "1", "a", "b" }, { "3", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" },
			{ "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" }, { "2", "a", "b" } };
	String[] cabecera = { "id", "Nombre", "Año de Nacimiento" };
	JTable tabla = new JTable(datos, cabecera);
	JScrollPane JS = new JScrollPane(tabla);

	public Principal() {
		setTitle("Hibernate");
		setLayout(new GridLayout(1, 1));
		JS.setPreferredSize(new Dimension(400, 100));

		actores.add(JS);

		//pestañas.setUI(new MyTabbedPaneUI());
		pestañas.add("actores", actores);
		pestañas.add("películas", peliculas);
		pestañas.add("reparto", reparto);
		add(pestañas);

		setSize(500, 300);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Principal();
	}

	/*public static class MyTabbedPaneUI extends javax.swing.plaf.basic.BasicTabbedPaneUI {

		@Override
		protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect,
				Rectangle textRect) {

			Color savedColor = g.getColor();
			g.setColor(new Color(185, 9, 11));
			g.fillRect(rects[tabIndex].x, rects[tabIndex].y, rects[tabIndex].width, rects[tabIndex].height);
			g.setColor(Color.white);
			g.drawRect(rects[tabIndex].x, rects[tabIndex].y, rects[tabIndex].width, rects[tabIndex].height);
			g.setColor(savedColor);
		}
	}*/

	

}
