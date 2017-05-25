package algorithm;

import java.util.ArrayList;
import java.util.Random;

public class Graph {

	// Node objects that constitute this Graph object
	private ArrayList<Node> nodes;
	// Edge objects that are a part of this Graph object
	private ArrayList<Edge> edges = new ArrayList<Edge>();

	public Graph(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public void connectAllNodes() {

		for (int i = 0; i < this.nodes.size(); i++)
			for (int j = i + 1; j < this.nodes.size(); j++) {

				double distance = this.nodes.get(i).getDistance(this.nodes.get(j));
				Edge e = new Edge(this.nodes.get(i), this.nodes.get(j), distance);
				Edge f = new Edge(this.nodes.get(j), this.nodes.get(i), distance);
				this.nodes.get(i).addEdge(e);
				this.nodes.get(j).addEdge(f);
				this.edges.add(e);
				this.edges.add(f);

			}

	}

	// Getters and Setters
	public ArrayList<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	public static void main(String[] args) {

		ArrayList<Node> nodes = new ArrayList<Node>();

		nodes.add(new Node(0, 0, 0, 0));

		// Example 1

		// Random r = new Random();

		/*
		 * for (int i = 1; i != 11; i++) nodes.add(new Node(i, r.nextInt(63),
		 * r.nextInt(63), r.nextInt(2) + 1));
		 */

		// Example 2

		/*
		 * nodes.add(new Node(1, 1, 1, 2)); nodes.add(new Node(2, 2, 2, 1));
		 * nodes.add(new Node(3, 3, 3, 3)); nodes.add(new Node(4, 4, 4, 4));
		 */

		// Example 3
		/*
		 * nodes.add(new Node(1, 1, 1, 5)); nodes.add(new Node(2, 4, 4, 1));
		 */

		// Example 4
		/*
		 * nodes.add(new Node(1, 2, 10, 1)); nodes.add(new Node(2, 2, 25, 2));
		 * nodes.add(new Node(3, 2, 40, 3)); nodes.add(new Node(4, 2, 45, 4));
		 */

		Graph g = new Graph(nodes);
		g.connectAllNodes();

		AStar a = new AStar(g);
		a.search();
		a.show();

	}

}