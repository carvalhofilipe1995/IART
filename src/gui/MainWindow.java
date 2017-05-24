package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import algorithm.Node;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private Console console;

	public MainWindow(ArrayList<Node> nodes) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException ex) {
		}
		
	
		
		this.nodes = nodes;
		
		this.console = new Console(this.nodes);
		getContentPane().add(console);
		
		setTitle("A2: Pesquisa aplicada à localização de unidades de saúde");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	
	public void startFrame() {
		setSize(1000, 1000);
		
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(size.width / 2 - getSize().width / 2, size.height / 2
				- getSize().height / 2);

		setVisible(true);
	
	}
	
}
