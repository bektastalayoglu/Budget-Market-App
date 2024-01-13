package Algorithms.Graph;

/**
 * @author Bektas Talayoglu
 * Description : The MatrixGraph class represents a graph using an adjacency matrix.
 */
public class MatrixGraph {
    private Matrix data;

    /**
     * Constructs a MatrixGraph with a specified number of nodes.
     *
     * @param nrNodes The number of nodes in the graph.
     */
    public MatrixGraph(int nrNodes) {
        data = new Matrix(nrNodes);
    }

    /**
     * Adds an edge with a specified weight between two nodes.
     *
     * @param from The starting node.
     * @param to   The ending node.
     * @param w    The weight of the edge.
     */
    public void addEdge(int from, int to, double w) {
        data.set(from, to, w);
    }

    /**
     * Gets the weight of the edge between two nodes.
     *
     * @param from The starting node.
     * @param to   The ending node.
     * @return The weight of the edge between the specified nodes.
     */
    public double getEdge(int from, int to) {
        return (Double) data.get(from, to);
    }


    /**
     * Returns a string representation of the graph.
     *
     * @return A string representation of the MatrixGraph.
     */
    @Override
    public String toString() {
        String s = "Nodes: ";
        for (int i = 0; i < data.size(); i++) {
            s += i + " ";
        }
        s += "\nEdges:";

        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.size(); j++) {
                double weight = getEdge(i, j);
                if (weight != 0.0) {
                    s += "(" + i + " -> " + j + ", Weight: " + weight + ") ";
                }
            }
        }
        return s;
    }

}