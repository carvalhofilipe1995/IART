package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import objects.Population;

public class Console extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Population> populations = new ArrayList<Population>();

	public Console(ArrayList<Population> populations) {
		this.populations = populations;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		draw(graphics);
	}

	public void draw(Graphics2D g2d) {

		// Drawing populations
		for (int i = 0; i < populations.size(); i++)
			drawPopulations(g2d, populations.get(i));

	}

	public void drawPopulations(Graphics2D g, Population p) {

		int sizeX, sizeY;

		if (getWidth() > getHeight())
			sizeX = sizeY = getHeight() / 64;
		else
			sizeX = sizeY = getWidth() / 64;

		g.setColor(Color.GRAY);
		g.fillOval(p.getCoordinates().x * sizeX + (getWidth() - sizeX * 64) / 2,
				p.getCoordinates().y * sizeY + (getHeight() - sizeX * 64) / 2, sizeX, sizeY);

	}

}
