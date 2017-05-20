package gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import objects.Population;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Console console;
	
	private int numberOfPopulations;
	private ArrayList<Population> populations = new ArrayList<Population>();

	public MainWindow() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException ex) {
		}
		
		this.numberOfPopulations = 30;
		
		Random rand = new Random(); 
		
		// Randomize the coordinates of every population
		for (int i = 0; i < this.numberOfPopulations; i++) {
			Point p = new Point((int) (rand.nextInt(64)), (int) rand.nextInt(64));
			this.populations.add(new Population(p));
		}
		
		this.console = new Console(populations);
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
