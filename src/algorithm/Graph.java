package algorithm;

import java.util.ArrayList;

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

		for (int i = 0; i < 4; i++)
			nodes.add(new Node(i, i, i, i));
		
		nodes.add(new Node(4,30,30,1));

		Graph g = new Graph(nodes);
		g.connectAllNodes();

		AStar a = new AStar(g);
		a.search();
		a.show();

	}

}