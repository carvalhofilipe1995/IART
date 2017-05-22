package algorithm;

import java.util.ArrayList;

public class AStar {

	public Graph graph;

	public ArrayList<Node> nodes;

	public int cost = -1137;

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

			System.out.println("CurrentLowestNode -> " + lowestFNode);

			openSet.remove(lowestFNode);
			
			// Added
			openSet.clear();
			//

			for (Edge e : lowestFNode.getEdges()) {

				Node sucessor = e.getDestination();

				// Added
				if (!closedSet.contains(sucessor)) {
				//
					sucessor.setParent(lowestFNode);
					sucessor.g = lowestFNode.g + e.getDistance() + e.getDestination().cost;
					sucessor.h = sucessor.heuristic();
					sucessor.f = sucessor.g + sucessor.h;

					System.out.println("    -> Sucessor:" + sucessor + " | g: " + sucessor.g + " | h: " + sucessor.h
							+ " | f: " + sucessor.f);

					if (hasBetterF(openSet, sucessor))
						continue;

					if (hasBetterF(closedSet, sucessor))
						continue;
					
					

					openSet.add(sucessor);

				} else// Added
					System.out.println("    -> Already visited: " + sucessor);
					//

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
			if (node.getId() == n.getId())
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
}
