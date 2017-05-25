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

		drawEdges(g2d, nodes);

	}

	public void drawPopulations(Graphics2D g, Node n) {

		if (n.getId() == 0)
			return;

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

	public void drawEdges(Graphics2D g, ArrayList<Node> all) {

		int sizeX, sizeY;

		if (getWidth() > getHeight())
			sizeX = sizeY = getHeight() / 64;
		else
			sizeX = sizeY = getWidth() / 64;

		for (int i = 1; i < all.size(); i++) {

			double value = Double.MAX_VALUE;
			int x = 0;
			int y = 0;

			for (int j = 0; j < all.size(); j++)
				if (all.get(i).getDistanceToNode(all.get(j)) < value && all.get(i) != all.get(j)) {
					value = all.get(i).getDistanceToNode(all.get(j));
					x = all.get(j).getPos_x();
					y = all.get(j).getPos_y();
				}

			if (x != 0 && y != 0 && all.get(i).getPos_x() != 0 && all.get(i).getPos_y() != 0) {
				if (value > 10) {
					g.setColor(Color.RED);
					g.drawLine(all.get(i).getPos_x() * sizeX + (getWidth() - sizeX * 64) / 2,
							all.get(i).getPos_y() * sizeY + (getHeight() - sizeX * 64) / 2,
							x * sizeX + (getWidth() - sizeX * 64) / 2, y * sizeY + (getHeight() - sizeX * 64) / 2);
				} else {
					g.setColor(Color.GREEN);
					g.drawLine(all.get(i).getPos_x() * sizeX + (getWidth() - sizeX * 64) / 2,
							all.get(i).getPos_y() * sizeY + (getHeight() - sizeX * 64) / 2,
							x * sizeX + (getWidth() - sizeX * 64) / 2, y * sizeY + (getHeight() - sizeX * 64) / 2);

				}
			}
		}

	}

}
