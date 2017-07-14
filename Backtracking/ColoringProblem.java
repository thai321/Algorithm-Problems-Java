public class ColoringProblem {
  private int numOfVertices;
  private int numOfColors;
  private int[] colors; // like a path of , index is vertex (0,1,2...) and value is color (0,1,2...)
  private int[][] adjacencyMatrix;

  public ColoringProblem(int[][] adjacencyMatrix, int numOfColors) {
    this.adjacencyMatrix = adjacencyMatrix;
    this.numOfColors = numOfColors;
    this.numOfVertices = adjacencyMatrix.length;
    this.colors = new int[numOfVertices];
  }

  public void solve() {
    if(solveProblem(0))
      showResults();
    else
      System.out.println("No solution ... ");
  }

  private boolean solveProblem(int nodeIndex) {
    if(nodeIndex == numOfVertices) // base case, found a solution
      return true;

    for(int colorIndex = 1; colorIndex <= numOfColors; colorIndex++) {
      if(isColorValid(nodeIndex, colorIndex)) {
        colors[nodeIndex] = colorIndex;

        if(solveProblem(nodeIndex + 1))
          return true;

        // BACKTRACK!!! Find a different color for this given node (nodeIndex)
      }
    }
    return false;
  }

  private boolean isColorValid(int nodeIndex, int currentColor) {
    for(int i = 0; i < numOfVertices; i++)
      if ((adjacencyMatrix[nodeIndex][i] == 1) && (colors[i] == currentColor))
        return false;
    return true;
  }

  private void showResults() {
    for(int i = 0; i < numOfVertices; i++)
      System.out.println("Node " + (i + 1) + " has color index: " + colors[i]);
  }

  public static void main(String[] args) {
    int[][] adjacencyMatrix = new int[][] {
      {0,1,0,1,0},
      {1,0,1,1,0},
      {0,1,0,1,0},
      {1,1,1,0,1},
      {0,0,0,1,0}
    };
    int numOfColors = 3;
    ColoringProblem color = new ColoringProblem(adjacencyMatrix, numOfColors);
    color.solve();

    /*
      numOfColors = 2 --> No solution
      numOfColors = 3 --> There is a solution
      Node 1 has color index: 1
      Node 2 has color index: 2
      Node 3 has color index: 1
      Node 4 has color index: 3
      Node 5 has color index: 1
    */
  }
}
