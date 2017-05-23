package algorithm;

import java.util.ArrayList;

import gui.MainWindow;

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
		Node n1 = new Node(1, 1, 1, 1);
		Node n2 = new Node(2, 2, 2, 8);
		Node n3 = new Node(3, 3, 3, 12);
		Node n4 = new Node(4, 20, 20, 50);
		Node n5 = new Node(5, 30, 30, 100);
		
		
		nodes.add(n1);
		nodes.add(n2);
		nodes.add(n3);
		nodes.add(n4);
		nodes.add(n5);
		

		Graph g = new Graph(nodes);
		g.connectAllNodes();

		AStar a = new AStar(g);
		a.search();

		MainWindow m = new MainWindow(g);
		m.startFrame();

	}

}