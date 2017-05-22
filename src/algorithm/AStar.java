package algorithm;

import java.util.ArrayList;
import java.util.HashMap;

public class AStar {

	public Graph graph;

	public ArrayList<Node> nodes;

	public int cost = -1137;

	public AStar(Graph g) {
		this.graph = g;
		this.nodes = g.getNodes();
	}

	public void search() {

		ArrayList<Node> closedSet = new ArrayList<Node>();

		ArrayList<Node> openSet = new ArrayList<Node>();

		HashMap<Node, Node> cameFrom = new HashMap<Node, Node>();
		HashMap<Node, Double> gScore = new HashMap<Node, Double>();
		HashMap<Node, Double> fScore = new HashMap<Node, Double>();

		for (Node n : this.nodes) {
			cameFrom.put(n, null);
			gScore.put(n, Double.MAX_VALUE);
			fScore.put(n, Double.MAX_VALUE);
		}

		gScore.put(nodes.get(0), (double) nodes.get(0).cost);
		fScore.put(nodes.get(0), nodes.get(0).heuristic());

		openSet.add(nodes.get(0));

		while (!openSet.isEmpty()) {

			Node current = retrieveLowestMapValue(fScore);

			openSet.remove(current);
			closedSet.add(current);

			for (Edge e : current.getEdges()) {

				System.out.println(e.getDestination());

				if (!hasNode(closedSet, e.getDestination())) {
					// Ver distancia possivelmente
					double gScore_t = gScore.get(current);

					if (!hasNode(openSet, e.getDestination())) {
						e.getDestination().addHospital();
						openSet.add(e.getDestination());
					} else if (gScore_t >= gScore.get(e.getDestination())) {
						continue;
					}

					cameFrom.put(e.getDestination(), current);

					gScore.put(e.getDestination(), gScore_t);

					fScore.put(e.getDestination(), gScore_t + e.getDestination().heuristic());

				}
			}
		}

		for (Node n : nodes) {
			System.out.println(n.toString());
		}

	}

	public Node retrieveLowestMapValue(HashMap<Node, Double> hashmap) {

		Node toReturn = null;
		double bestValueSoFar = Double.MAX_VALUE;

		for (Node n : hashmap.keySet()) {
			if (hashmap.get(n) < bestValueSoFar) {
				bestValueSoFar = hashmap.get(n);
				toReturn = n;
			}
		}

		return toReturn;

	}

	public boolean hasNode(ArrayList<Node> nodes, Node n) {
		
		for (Node node : nodes) {
			if(node.getId() == n.getId())
				return true;
		}
		
		return false;
	}

}
