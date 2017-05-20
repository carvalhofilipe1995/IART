package objects;

import java.awt.Point;

public class Population {
	
	private Point coordinates;
	
	
	public Population(Point coordinates) {
		this.coordinates = coordinates;
	}


	public Point getCoordinates() {
		return coordinates;
	}


	public void setCoordinates(Point coordinates) {
		this.coordinates = coordinates;
	}
	
}
