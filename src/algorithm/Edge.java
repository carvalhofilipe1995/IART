package algorithm;

public class Edge {

	private Node source; // Source Node object
	private Node destination; // Destination Node object
	private double distance; // Distance associated to this Edge object

	public Edge(Node source, Node destination, double distance) {
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}

	// Getters and Setters

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node getDestination() {
		return destination;
	}

	public void setDestination(Node destination) {
		this.destination = destination;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String toString() {
		return "Source: " + this.source + " Destination: " + this.destination + " Distance: " + this.distance;
	}

}
