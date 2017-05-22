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

		for (int i = 0; i < this.nodes.size(); i++) {

			int j = i + 1;

			for (; j < this.nodes.size(); j++) {

				double distance = this.nodes.get(i).getDistance(this.nodes.get(j));
				Edge e = new Edge(this.nodes.get(i), this.nodes.get(j), distance);
				this.nodes.get(i).addEdge(e);
				this.nodes.get(j).addEdge(e);
				System.out.println(e.toString());
				this.edges.add(e);

			}

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
		Node n2 = new Node(2, 2, 2, 0);
		Node n3 = new Node(3, 21, 3, 10);
		Node n4 = new Node(4, 10, 10, 12);
		Node n5 = new Node(5, 40, 40, 15);
		Node n6 = new Node(6, 30, 20, 300);
		Node n7 = new Node(7, 25, 40, 100);
		Node n8 = new Node(8, 60, 40, 19);
		Node n9 = new Node(9, 3, 3, 500);
		Node n10 = new Node(10, 4, 4, 1);

		nodes.add(n1);
		nodes.add(n2);
		nodes.add(n3);
		nodes.add(n4);
		nodes.add(n5);
		nodes.add(n6);
		nodes.add(n7);
		nodes.add(n8);
		nodes.add(n9);
		nodes.add(n10);
		Graph g = new Graph(nodes);
		g.connectAllNodes();
		
		AStar a = new AStar(g);
		a.search();
		
		//n4.finalCheck();
		
		MainWindow m = new MainWindow(g);
		m.startFrame();
		
		System.out.println(n1.toString());
		System.out.println(n2.toString());
		System.out.println(n3.toString());
		System.out.println(n4.toString());
		System.out.println(n5.toString());

	}

}