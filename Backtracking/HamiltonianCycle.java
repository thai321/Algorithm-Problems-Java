public class HamiltonianCycle {
  private int numOfVertexes;
  private int[] hamiltonianPath;
  private int[][] adjacencyMatrix;

  public HamiltonianCycle(int[][] adjacencyMatrix) {
    this.adjacencyMatrix = adjacencyMatrix;
    this.hamiltonianPath = new int[adjacencyMatrix.length];
    this.numOfVertexes = adjacencyMatrix.length;

    this.hamiltonianPath[0] = 0; //root, start node
  }

  public void solve() {
    if(!findFeasibleSolution(1))
      System.out.println("No feasible solution ...");
    else
      showHamiltonianPath();
  }

  public boolean findFeasibleSolution(int position) {
    // base case
    if(position == numOfVertexes) {
      int last = hamiltonianPath[position - 1];
      int start = hamiltonianPath[0];
      //if the last vertex connected with start vertex, then it's hamiltonian cycle
      if(adjacencyMatrix[last][start] == 1) // if they connected
        return true;
    }

    // start from the second vertex(node) since first vertex is 0 (root)
    for(int vertexIndex = 1; vertexIndex < numOfVertexes ; vertexIndex++) {
      if(findFeasible(vertexIndex, position)) {
        hamiltonianPath[position] = vertexIndex;

        if(findFeasibleSolution(position + 1)) { // recursive next vertex
          return true;
        }
        // BACKTRACK !!!
      }
    }
    return false; // BACKTRACK
  }

  private boolean findFeasible(int vertexIndex, int actualPosition) {
    // first criterion: whether tow nodes are connected
    if(adjacencyMatrix[hamiltonianPath[actualPosition-1]][vertexIndex] == 0 )
      return false;

    // second criterion: whether we have visited it or not
    for(int i = 0; i < actualPosition ; i++)
      if(hamiltonianPath[i] == vertexIndex)
        return false;

    return true;
  }

  private void showHamiltonianPath() {
    System.out.println("Hamiltonian cycle: ");

    for(int i = 0;i < hamiltonianPath.length; i++)
      System.out.print(hamiltonianPath[i] + " ");
    System.out.print(hamiltonianPath[0]); // back to the first node(root) (cycle)
  }

  public static void main(String[] args) {
    int[][] adjacencyMatrix = {
    // A  B  C
      {0, 1, 0}, // A
      {1, 0, 1}, // B
      {0, 1, 0} //  C
    };
    HamiltonianCycle cycle = new HamiltonianCycle(adjacencyMatrix);
    cycle.solve(); // no solution

    int[][] adjacencyMatrix2 = {
      {0,1,1,1,0,0},
      {1,0,1,0,1,0},
      {1,1,1,1,0,1},
      {1,0,1,0,0,1},
      {0,1,0,0,0,1},
      {0,1,1,1,1,1}
    };
    HamiltonianCycle cycle2 = new HamiltonianCycle(adjacencyMatrix2);
    cycle2.solve(); // solution
    // Hamiltonian cycle:
    //  0 1 4 5 2 3 0
  }
}
