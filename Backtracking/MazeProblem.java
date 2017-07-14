public class MazeProblem {
  private int[][] mazeTable;
  private int[][] solutionTable;
  private int mazeSize;

  public MazeProblem(int[][] mazeTable) {
    this.mazeTable = mazeTable;
    this.mazeSize = mazeTable.length;
    this.solutionTable = new int[mazeSize][mazeSize];
  }

  public void solve() {
    if(solveMaze(0,0)) // start at posision 0 0
      showResults();
    else
      System.out.println("No solution ... ");
  }

  private boolean solveMaze(int x, int y) {
    if( isFinished(x,y) )
      return true;

    if( isValid(x,y)) {
      solutionTable[x][y] = 1;

      if(solveMaze(x + 1, y)) // go right
        return true;
      if(solveMaze(x, y + 1)) // go up
        return true;

      // BACKTRACK !!!
      solutionTable[x][y] = 0; // set back to default of 0
    }

    return false;
  }

  private boolean isFinished(int x, int y) {
    // stand at next to the gate solution (cell solution)
    if ((x == mazeSize - 1) && (y == mazeSize - 1)) {
      solutionTable[x][y] = 1;
      return true;
    }
    return false;
  }

  private boolean isValid(int x, int y) {
    boolean xValid = (x >= 0 && x < mazeSize);
    boolean yValid = (y >= 0 && y < mazeSize);
    return xValid && yValid && (mazeTable[x][y] == 1); // open position
  }

  private void showResults() {
    for(int i = 0; i < mazeSize; i++) {
      for(int j = 0; j< mazeSize; j++) {
        if(solutionTable[i][j] == 1)
          System.out.print(" S ");
        else
          System.out.print((" - "));
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int mazeTable[][] = {
                          {1, 1, 1, 1, 1},
                          {1, 0, 0, 1, 0},
                          {0, 0, 0, 1, 0},
                          {1, 1, 1, 1, 1},
                          {1, 1, 1, 0, 1}
                        };
    MazeProblem f = new MazeProblem(mazeTable);
    f.solve();
    /*
      S  S  S  S  -
      -  -  -  S  -
      -  -  -  S  -
      -  -  -  S  S
      -  -  -  -  S
    */
  }
}
