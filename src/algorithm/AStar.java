package algorithm;

import java.util.ArrayList;

public class AStar {

	public Graph graph;

	public ArrayList<Node> nodes;

	public AStar(Graph g) {
		this.graph = g;
		this.nodes = g.getNodes();
	}

	int hospitals_to_put = 3;

	public void search() {

		ArrayList<Node> closedSet = new ArrayList<Node>();
		ArrayList<Node> openSet = new ArrayList<Node>();

		// Start with the first node
		openSet.add(nodes.get(0));

		while (!openSet.isEmpty()) {

			Node lowestFNode = retrieveLowestListValue(openSet);

			if (closedSet.size() > 0 && closedSet.get(closedSet.size() - 1).getVisitedNodes().contains(lowestFNode))
				continue;

			if (closedSet.size() > 0)
				closedSet.get(closedSet.size() - 1).addVisitedNode(lowestFNode);
			
			if (lowestFNode.hospitals_used == 3)
				break;

			System.out.println("CurrentLowestNode -> " + lowestFNode);

			openSet.remove(lowestFNode);

			for (Node sucessor : createSucessores(lowestFNode)) {

				if (!hasNode(closedSet, sucessor)) {

					sucessor.setParent(lowestFNode);
					sucessor.g = lowestFNode.g + sucessor.getCost() + lowestFNode.getDistance(sucessor);
					sucessor.h = sucessor.heuristic(graph.getNodes()) / (sucessor.hospitals_used + 1);
					sucessor.f = sucessor.g + sucessor.h;

					System.out.println("    -> Sucessor:" + sucessor + " | g: " + sucessor.g + " | h: " + sucessor.h
							+ " | f: " + sucessor.f);

					if (hasBetterF(openSet, sucessor))
						continue;

					if (hasBetterF(closedSet, sucessor))
						continue;

					openSet.add(sucessor);
				}

			}

			closedSet.add(lowestFNode);
		}

	}

	public Node retrieveLowestListValue(ArrayList<Node> openSet) {

		Node toReturn = null;
		double bestValueSoFar = Double.MAX_VALUE;

		for (Node n : openSet) {
			if (n.f < bestValueSoFar) {
				bestValueSoFar = n.f;
				toReturn = n;
			}
		}

		return toReturn;

	}

	public boolean hasNode(ArrayList<Node> nodes, Node n) {

		for (Node node : nodes) {
			if (node.getId() == n.getId() && n.getParent() == node.getParent()
					&& n.hospitals_used == node.hospitals_used)
				return true;
		}

		return false;
	}

	public boolean hasBetterF(ArrayList<Node> set, Node n) {

		for (Node nset : set) {

			if (nset.getId() == n.getId() && nset.f > n.f) {
				return true;
			}
		}

		return false;
	}

	public ArrayList<Node> createSucessores(Node n) {

		ArrayList<Node> sucessores = new ArrayList<Node>();

		for (Edge e : n.getEdges()) {

			Node _WithHospital = new Node(e.getDestination());
			_WithHospital.setParent(n);
			_WithHospital.setHasHospital(true);
			_WithHospital.hospitals_used++;

			sucessores.add(_WithHospital);

			Node _WithoutHospital = new Node(e.getDestination());
			_WithoutHospital.setParent(n);
			_WithoutHospital.setHasHospital(false);

			sucessores.add(_WithoutHospital);

		}

		return sucessores;
	}
}
