package algorithm;

import java.util.ArrayList;

import gui.MainWindow;

public class AStar {

	public Graph graph;

	public ArrayList<Node> nodes;

	ArrayList<Node> closedSet = new ArrayList<Node>();
	ArrayList<Node> openSet = new ArrayList<Node>();
	ArrayList<Node> toShow = new ArrayList<Node>();

	public AStar(Graph g) {
		this.graph = g;
		this.nodes = g.getNodes();
		this.toShow = this.nodes;
	}

	public void search() {

		// Start with the first node
		openSet.add(nodes.get(0));

		while (!openSet.isEmpty()) {

			Node lowestFNode = retrieveLowestListValue(openSet);

			System.out.println("CurrentLowestNode -> " + lowestFNode);

			openSet.remove(lowestFNode);

			for (Node sucessor : createSucessores(lowestFNode)) {

				if (sucessor.hospitals_used == 10) {
					while (sucessor.getParent() != null) {
						
						sucessor = sucessor.getParent();
						
						for (int i = 0; i < toShow.size(); i++)
							if (toShow.get(i).getId() == sucessor.getId()) {
								toShow.remove(i);
								toShow.add(sucessor);
								break;
							}
						
					}
					return;
				}

				if (!hasNode(closedSet, sucessor)) {
					sucessor.h = sucessor.heuristic(graph.getNodes());
					sucessor.f = sucessor.g + sucessor.h;

					System.out.println("    -> Sucessor:" + sucessor + " | g: " + sucessor.g + " | h: " + sucessor.h + " | Distance: " + lowestFNode.getDistance(sucessor));

					if (hasBetterF(openSet, sucessor))
						continue;

					if (hasBetterF(closedSet, sucessor))
						continue;

					openSet.add(sucessor);
				}

			}
			
			System.out.println("\n");

			closedSet.add(lowestFNode);
		}

	}

	public void show() {

		MainWindow window = new MainWindow(this.toShow);
		window.startFrame();
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
			if (node == n)
				return true;
		}

		return false;
	}

	public boolean hasBetterF(ArrayList<Node> set, Node n) {

		for (Node nset : set) {

			if (nset.equals(n) && nset.f < n.f) {
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
			_WithHospital.hospitals_used = n.hospitals_used + 1;
			_WithHospital.connectionsMade = n.connectionsMade;
			_WithHospital.connectionsMade.add(_WithHospital.getId() + "");
			_WithHospital.g = _WithHospital.getCost(n) + n.g;

			sucessores.add(_WithHospital);

			Node _WithoutHospital = new Node(e.getDestination());
			_WithoutHospital.setParent(n);
			_WithoutHospital.setHasHospital(false);
			_WithoutHospital.hospitals_used = n.hospitals_used;
			_WithoutHospital.connectionsMade = n.connectionsMade;
			_WithoutHospital.connectionsMade.add(_WithoutHospital.getId() + "");
			_WithoutHospital.g = _WithoutHospital.getCost(n) + n.g;

			sucessores.add(_WithoutHospital);
		}

		return sucessores;
	}
}
