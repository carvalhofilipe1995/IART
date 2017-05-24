package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import algorithm.Node;

public class Console extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Node> nodes = new ArrayList<Node>();

	public Console(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		draw(graphics);
	}

	public void draw(Graphics2D g2d) {

		// Drawing populations
		for (int i = 0; i < nodes.size(); i++)
			drawPopulations(g2d, nodes.get(i));

	}

	public void drawPopulations(Graphics2D g, Node n) {

		int sizeX, sizeY;

		if (getWidth() > getHeight())
			sizeX = sizeY = getHeight() / 64;
		else
			sizeX = sizeY = getWidth() / 64;

		if (n.hasHospital()) {
			g.setColor(Color.BLUE);
			g.fillOval(n.getPos_x() * sizeX + (getWidth() - sizeX * 64) / 2,
					n.getPos_y() * sizeY + (getHeight() - sizeX * 64) / 2, sizeX, sizeY);
		} else {
			g.setColor(Color.GRAY);
			g.fillOval(n.getPos_x() * sizeX + (getWidth() - sizeX * 64) / 2,
					n.getPos_y() * sizeY + (getHeight() - sizeX * 64) / 2, sizeX, sizeY);
		}

	}

}
