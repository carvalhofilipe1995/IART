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

	public void search() {

		// Visited Nodes
		ArrayList<Node> closedSet = new ArrayList<Node>();

		// Start node
		// Node openSet = nodes.get(0);

		// For each node, which node it can most efficiently be reached from.
		// If a node can be reached from many nodes, cameFrom will eventually
		// contain the
		// most efficient previous step.
		// Node cameFrom;

		double function = 0.0;

		int i = 0;

		while (nodes.size() != closedSet.size()) {

			Node currentNode = nodes.get(i);

			ArrayList<Double> functions_values = new ArrayList<>();

			cost += currentNode.cost;

			if (currentNode.isAnyNear()) {

				for (int j = 0; j < currentNode.getEdges().size(); j++) {

					function = cost + currentNode.getEdges().get(j).getDestination().cost
							+ currentNode.getEdges().get(j).getDestination().heuristic();

					functions_values.add(function);
				}

				int position = 0;
				double maxValue = 0.0;

				for (int k = 0; k < functions_values.size(); k++)
					if (functions_values.get(k) > maxValue)
						position = k;

				currentNode.getEdges().get(position).getDestination().removeHospital();

				cost += currentNode.getEdges().get(position).getDestination().cost;
			} 

			closedSet.add(currentNode);
			i++;

		}

	}

}
