package Algorithms.Graph;

import Algorithms.Dictionary.Dictionary;
import Algorithms.List.LinkedList;
import Algorithms.Stack.Stack;
import Algorithms.Vector.Vector;

public class GraphDictionary<E extends Comparable<E>> {
    public class Node implements Comparable {
        private Comparable info;
        private Vector edges;
        private boolean visited;

        public Node(Comparable label) {
            info = label;
            edges = new Vector();
            visited = false;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean value) {
            visited = value;
        }

        public void addEdge(Edge e) {
            edges.addLast(e);
        }

        public int compareTo(Object o) {
            // two nodes are equal if they have the same label
            Node n = (Node) o;
            return n.info.compareTo(info);
        }

        public Comparable getLabel() {
            return info;
        }

    }

    private class Edge implements Comparable {
        private Node toNode;
        private int weight; // New field for edge weight

        public Edge(Node to, int weight) {
            toNode = to;
            this.weight = weight;
        }

        // Getter for the weight
        public int getWeight() {
            return weight;
        }

        public int compareTo(Object o) {
            // two edges are equal if they point
            // to the same node.
            // this assumes that the edges are
            // starting from the same node !!!
            Edge n = (Edge) o;
            return n.toNode.compareTo(toNode);
        }
    }

    private Dictionary<E, Node> nodeMap;

    public GraphDictionary() {
        nodeMap = new Dictionary<>();
    }

    public void addNode(E label) {
        nodeMap.add(label, new Node(label));
    }

    private Node findNode(E nodeLabel) {
        return nodeMap.find(nodeLabel);
    }

    public void addEdge(E nodeLabel1,
                        E nodeLabel2, int weight) {
        Node n1 = findNode(nodeLabel1);
        Node n2 = findNode(nodeLabel2);
        n1.addEdge(new Edge(n2, weight));
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < nodeMap.size(); i++) {
            Node currentNode = nodeMap.values().get(i);
            s += "Node: " + currentNode.getLabel() + "\n";

            Vector edges = currentNode.edges;
            for (int j = 0; j < edges.size(); j++) {
                Edge currentEdge = (Edge) edges.get(j);
                s += "  Edge to: " + currentEdge.toNode.getLabel() +
                        " with weight: " + currentEdge.getWeight() + "\n";
            }
        }
        return s;
    }

    private void resetVisitedStatus() {
        for (int i = 0; i < nodeMap.size(); i++) {
            Node n = nodeMap.values().get(i);
            n.setVisited(false);
        }
    }

    public LinkedList findPath(E nodeLabel1, E nodeLabel2) {
        Node startState = findNode(nodeLabel1);
        Node endState = findNode(nodeLabel2);

        if (startState == null || endState == null) {
            return null; // Either start or end node not found
        }

        Stack<Node> toDoList = new Stack<>();
        LinkedList path = new LinkedList<>();

        resetVisitedStatus();
        toDoList.push(startState);

        while (!toDoList.empty()) {
            Node current = toDoList.pop();

            if (!current.isVisited()) {
                path.addFirst(current.getLabel()); // Store the label
                current.setVisited(true);

                if (current == endState) {
                    return path; // Path found
                } else {
                    for (int i = 0; i < current.edges.size(); i++) {
                        Edge e = (Edge) current.edges.get(i);
                        toDoList.push(e.toNode);
                    }
                }
            }
        }
        return null; // No path found
    }
}


