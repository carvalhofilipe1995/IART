package algorithm;

import java.util.ArrayList;

public class Node {

	/*
	 * Variables to f = g + h
	 */
	public double f = 0, g, h;

	ArrayList<String> connectionsMade = new ArrayList<String>();

	public int hospitals_used = 0;

	// ID
	private int id;

	// Position to calculate of the node
	private int pos_x;
	private int pos_y;

	// Edges
	private ArrayList<Edge> edges = new ArrayList<Edge>();

	// Visited Nodes
	private ArrayList<Node> visitedNodes = new ArrayList<Node>();

	// Boolean to check if every node has a hospital
	// Initially every Town hasn't a hospital allocated
	private boolean hasHospital = false;

	public double cost;
	private Node parent; // Parent of the current node

	public Node(int id, int posx, int posy, int cost) {
		this.id = id;
		this.pos_x = posx;
		this.pos_y = posy;
		this.cost = cost;
		this.parent = null;
		this.connectionsMade.add("" + id);
	}

	@Override
	public boolean equals(Object o) {

		Node toCheck = (Node) o;

		if (toCheck.hasHospital == this.hasHospital && toCheck.hospitals_used == this.hospitals_used
				&& toCheck.id == this.id && toCheck.parent == this.parent)
			return true;

		else
			return false;

	}

	public Node(Node n) {
		this.id = n.id;
		this.pos_x = n.pos_x;
		this.pos_y = n.pos_y;
		this.cost = n.cost;
		this.edges = n.edges;
		this.g = n.g;
		this.f = n.f;
		this.h = n.h;
	}

	public void addEdge(Edge e) {
		if (!edges.contains(e))
			this.edges.add(e);
	}

	public double getDistance(Node e) {
		return Math.sqrt(Math.pow((this.pos_x - e.getPos_x()), 2) + Math.pow((this.pos_y - e.getPos_y()), 2));
	}

	public void removeHospital() {
		this.hasHospital = false;
	}

	public ArrayList<Node> getVisitedNodes() {
		return this.visitedNodes;
	}

	public void addVisitedNode(Node e) {
		this.visitedNodes.add(e);
	}

	public double heuristic(ArrayList<Node> allNodes) {

		/*
		 * double distance = Double.MIN_VALUE;
		 * 
		 * Node n = this;
		 * 
		 * for (Node _n : allNodes) if (n.getDistance(_n) > distance &&
		 * !_n.hasHospital) distance = n.getDistance(_n);
		 * 
		 * double total_cost = 0.0;
		 * 
		 * for (Node _n : allNodes) total_cost += _n.getCost(n);
		 * 
		 * return (distance / 20) + (total_cost / allNodes.size());
		 */
		ArrayList<Node> withHospital = new ArrayList<Node>();

		Node n = this;
		if (n.hasHospital)
			withHospital.add(this);

		while (n.parent != null) {

			if (n.parent.hasHospital)
				withHospital.add(n.parent);

			n = n.parent;
		}

		return getSmallestDistance(allNodes, withHospital);
	}

	public double getSmallestDistance(ArrayList<Node> allNodes, ArrayList<Node> withHospital) {

		ArrayList<Node> withoutHospital = new ArrayList<Node>();
		double allDistance = 0;
		double counter = 1;

		for (Node n : allNodes) {
			boolean canAdd = true;
			for (Node an : withHospital) {

				if (n.id == an.id) {
					canAdd = false;
					break;
				}
			}
			if (canAdd)
				withoutHospital.add(n);
		}

		for (Node n : withoutHospital) {

			double distance = Double.MAX_VALUE;

			for (Node _n : withHospital) {

				double d = n.getDistance(_n);

				if (d < distance)
					distance = n.getDistance(_n);

			}

			allDistance += distance;
			counter += 1;

		}

		if (Double.isInfinite(allDistance))
			allDistance = 5000;

		return allDistance / counter;
	}

	public double getDistanceToNode(Node n) {

		for (Edge e : edges) {
			if (e.getDestination().id == n.id)
				return e.getDistance();
		}

		return 0;
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
			return "[F:" + f + "] Node: " + id + " number_of_hospitals: "
					+ this.hospitals_used + " from: " + parent.id;
		else
			return "[F:" + f + "] Node: " + id + " number_of_hospitals: "
					+ this.hospitals_used;
	}

	public double getCost(Node n) {

		if (hasHospital)
			return cost + n.getDistanceToNode(n);
		else
			return n.getDistanceToNode(n);
	}

}