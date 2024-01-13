package Algorithms.Graph;

/**
 * @author Bektas Talayoglu
 * Description : The Matrix class represents a square matrix used to store weights of edges in a graph.
 */
public class Matrix
{
	private Comparable[][] matrix;
	private int nrNodes;

	/**
	 * Constructs a Matrix with a specified number of nodes.
	 *
	 * @param nrNodes The number of nodes in the graph, determining the size of the matrix.
	 */
	public Matrix(int nrNodes)
	{
		// allocate an N-by-N matrix where N = nrNodes
		this.nrNodes = nrNodes;
		this.matrix = new Comparable[nrNodes][nrNodes];

		// all elements are initially set to 0
		for(int i = 0; i < nrNodes; i++){
			for(int j = 0; j < nrNodes; j++){
				matrix[i][j] = 0.0;
			}
		}
	}

	/**
	 * Sets the weight at the specified row and column in the matrix.
	 *
	 * @param row    The row index.
	 * @param col    The column index.
	 * @param weight The weight to be set at the given position.
	 */
	public void set(int row, int col, Comparable weight)
	{
		// store the weight at the given row and column.
		matrix[row][col] = weight;
	}


	/**
	 * Retrieves the weight at the specified row and column in the matrix.
	 *
	 * @param row The row index.
	 * @param col The column index.
	 * @return The weight at the given position in the matrix.
	 */
	public Comparable get(int row, int col)
	{
		// return the weight at the given row and column.
		return matrix[row][col];
	}

	/**
	 * Returns the size of the matrix.
	 *
	 * @return The number of nodes, representing the size of the matrix.
	 */
	public int size(){
		return matrix.length;
	}
}