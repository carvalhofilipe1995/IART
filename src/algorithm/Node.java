package algorithm;

import java.util.ArrayList;

public class Node {

	/*
	 * Variables to f = g + h
	 */
	public double f = 0, g, h;

	public int hospitals_used = 0;

	// ID
	private int id;

	// Position to calculate of the node
	private int pos_x;
	private int pos_y;

	// Edges
	private ArrayList<Edge> edges = new ArrayList<Edge>();

	// Boolean to check if every node has a hospital
	// Initially every Town has a hospital allocated
	private boolean hasHospital = false;

	public int cost;
	private Node parent; // Parent of the current node

	public Node(int id, int posx, int posy, int cost) {
		this.id = id;
		this.pos_x = posx;
		this.pos_y = posy;
		this.cost = cost;
		this.parent = null;
	}

	public void addEdge(Edge e) {
		if (!edges.contains(e))
			this.edges.add(e);
	}

	public double getDistance(Node e) {
		return Math.sqrt(Math.pow((this.pos_x - e.getPos_x()), 2) + Math.pow((this.pos_y - e.getPos_y()), 2));
	}

	public void addHospital(int hospitalNumber) {
		this.hasHospital = true;
		this.hospitals_used = hospitalNumber;
	}

	public void removeHospital() {
		this.hasHospital = false;
	}

	public double heuristic() {

		int counter = 1;
		double distance = 0.0;

		for (Edge e : edges) {
//			System.out.println("		Edge | Source -> " + e.getSource() + " | Destination -> " + e.getDestination());
			if (!e.getDestination().hasHospital) {
				distance += e.getDistance();
				counter++;
			}
		}

		return distance / counter;
	}

	public boolean isAnyNear() {

		for (int i = 0; i < this.edges.size(); i++) {
			System.out.println(this.edges.get(i));
			if (this.edges.get(i).getDistance() <= 10.0 && this.edges.get(i).getSource().hasHospital)
				return true;
		}

		return false;
	}

	public void finalCheck() {

		for (int i = 0; i < this.edges.size(); i++)
			if (this.edges.get(i).getDistance() <= 10.0)
				this.hasHospital = true;
	}

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	public int getPos_x() {
		return pos_x;
	}

	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	public int getPos_y() {
		return pos_y;
	}

	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public boolean hasHospital() {
		return hasHospital;
	}

	public void setHasHospital(boolean hasHospital) {
		this.hasHospital = hasHospital;
	}

	public String toString() {

		if (parent != null)
			return "" + id + " hasHospitals: " + hasHospital + ": from " + parent.id;
		else
			return "" + id + " hasHospitals: " + hasHospital;
	}

}