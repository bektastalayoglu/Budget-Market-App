package Algorithms.Graph;

import Algorithms.List.LinkedList;
import Algorithms.Queue.Queue;
import Algorithms.Stack.Stack;
import Algorithms.Vector.Vector;

public class Graph {
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

    private Vector nodes;

    public Graph() {
        nodes = new Vector();
    }

    public void addNode(Comparable label) {
        nodes.addLast(new Node(label));
    }

    private Node findNode(Comparable nodeLabel) {
        for (int i = 0; i < nodes.size(); i++){
            Node n = (Node) nodes.find(nodes.get(i));
            if (n.getLabel().equals(nodeLabel)){
                return n;
            }
        }
        return null;
    }

/*    public Node findNode(Comparable nodeLabel) {
        Node res = null;
        for (int i = 0; i < nodes.size(); i++) {
            Node n = (Node) nodes.get(i);
            if (n.getLabel() == nodeLabel) {
                res = n;
                break;
            }
        }
        return res;
    }*/

    public Node getNode(Comparable nodeLabel) {
        for (int i = 0; i < nodes.size(); i++) {
            Node n = (Node) nodes.get(i);
            if (n.getLabel() == nodeLabel) {
                return (Node) nodes.get(i);
            }
        }
        return null;
    }

    public void addEdge(Comparable nodeLabel1,
                        Comparable nodeLabel2, int weight) {
        Node n1 = findNode(nodeLabel1);
        Node n2 = findNode(nodeLabel2);
        n1.addEdge(new Edge(n2, weight));
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < nodes.size(); i++) {
            Node currentNode = (Node) nodes.get(i);
            s += "Node: " + currentNode.getLabel() + "\n";

            Vector<Edge> edges = currentNode.edges;
            for (int j = 0; j < edges.size(); j++) {
                Edge currentEdge = edges.get(j);
                s += "  Edge to: " + currentEdge.toNode.getLabel() +
                        " with weight: " + currentEdge.getWeight() + "\n";
            }
        }
        return s;
    }

    public void resetVisitedStatus() {
        for (int i = 0; i < nodes.size(); i++) {
            Node n = (Node) nodes.get(i);
            n.setVisited(false);
        }
    }

    public LinkedList findPath(Comparable nodeLabel1, Comparable nodeLabel2) {
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

    public int size() {
        return nodes.size();
    }

    public Vector<String> getNodesWithinDistance(Comparable startNodeLabel, int distance) {
        Node startNode = findNode(startNodeLabel);

        if (startNode == null) {
            System.out.println("Start node not found.");
            return new Vector<>(); // Return an empty vector if the start node is not found
        }

        Vector<String> nodesWithinDistance = new Vector<>();
        Queue<Node> queue = new Queue<>();

        queue.push(startNode);
        startNode.setVisited(true);
        nodesWithinDistance.addLast(startNode.getLabel().toString());

        while (!queue.empty()) {
            Node currentNode = queue.pop();

            for (int i = 0; i < currentNode.edges.size(); i++) {
                Edge edge = (Edge) currentNode.edges.get(i);
                Node neighborNode = edge.toNode;

                if (!neighborNode.isVisited() && edge.getWeight() <= distance) {
                    queue.push(neighborNode);
                    neighborNode.setVisited(true);
                    nodesWithinDistance.addLast(neighborNode.getLabel().toString());
                }
            }

        }

        return nodesWithinDistance;
    }


}