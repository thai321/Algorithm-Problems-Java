public class MazeSolver {

  private int[][] mazeMap;
  private int[][] solutionTable;
  private boolean[][] visited;
  private int startPositionCol;
  private int startPositionRow;
  private int goalRow;
  private int goalCol;

  public MazeSolver(int[][] mazeMap, int startPositionRow, int startPositionCol) {
    this.mazeMap = mazeMap;
    this.visited = new boolean[mazeMap.length][mazeMap.length];
    this.solutionTable = new int[mazeMap.length][mazeMap.length];
    this.startPositionRow = startPositionRow;
    this.startPositionCol = startPositionCol;
  }

  public void findWayOut() {

    System.out.println("Starting at " + startPositionRow + ", " + startPositionCol);

    if(dfs(startPositionRow, startPositionCol)) {
      System.out.println("Route found to exit !!! ");
      printSolution();
    } else {
      System.out.println("No solution found ...");
    }
  }

  private boolean dfs(int rowIndex, int colIndex) {

    System.out.println("Visiting i = " + rowIndex + ", j = " + colIndex);
    // Finsished
    if( this.mazeMap[rowIndex][colIndex] == 3) {
      System.out.println("Solution i = " + rowIndex + ", j = " + colIndex);
      this.goalRow = rowIndex;
      this.goalCol = colIndex;
      return true; // Found way out !!!
    }

    //
    this.visited[rowIndex][colIndex] = true;

    if(isValidMove(rowIndex + 1, colIndex))
      if (dfs(rowIndex + 1, colIndex)) { // go down
        solutionTable[rowIndex + 1][colIndex] = 1;
        return true;
      }

    if(isValidMove(rowIndex, colIndex + 1))
      if (dfs(rowIndex, colIndex + 1)) { // go right
        solutionTable[rowIndex][colIndex + 1] = 1;
        return true;
      }

    if(isValidMove(rowIndex, colIndex - 1))
      if (dfs(rowIndex, colIndex - 1)) { // go left
        solutionTable[rowIndex][colIndex - 1] = 1;
        return true;
      }

    if(isValidMove(rowIndex - 1, colIndex)) {
      if (dfs(rowIndex - 1, colIndex)) // go up
        solutionTable[rowIndex - 1][colIndex] = 1;
        return true;
      }

    return false;
  }

  // checking: x, y boundary, wall ( != 1), already visited
  private boolean isValidMove(int x, int y) {
    int endOfMap = this.mazeMap.length - 1;

    boolean xValid = (x >= 0 && x <= endOfMap);
    boolean yValid = (y >= 0 && y <= endOfMap);

    return xValid && yValid && this.mazeMap[x][y] != 1 && !visited[x][y];
  }

  private void printSolution() {
    // starting position
    solutionTable[startPositionRow][startPositionCol] = 2;
    solutionTable[goalRow][goalCol] = 3;

    for(int i = 0; i < this.solutionTable.length; i++) {
      for(int j = 0; j < this.solutionTable.length; j++) {
        if(solutionTable[i][j] == 1) { System.out.print(" P "); }
        else if(solutionTable[i][j] == 2) { System.out.print(" S "); }
        else if(solutionTable[i][j] == 3) { System.out.print(" G "); }
        else { System.out.print(" - ");}
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    FileReader file = new FileReader("map.txt", 5, 5);
    file.parseFile();

    int startRow = file.getStartPositionRow();
    int startCol = file.getStartPositionCol();

    MazeSolver mazeSolver = new MazeSolver(file.getMap(), startRow, startCol);
    mazeSolver.findWayOut();
  }
}
/*
Map:
1: wall, 2: start point, 3: exit (goal)
  1 1 1 1 3
  1 2 0 1 0
  1 1 0 1 0
  0 0 0 0 0
  0 1 1 1 0

Starting at 1, 1
Visiting i = 1, j = 1
Visiting i = 1, j = 2
Visiting i = 2, j = 2
Visiting i = 3, j = 2
Visiting i = 3, j = 3
Visiting i = 3, j = 4
Visiting i = 4, j = 4
Visiting i = 2, j = 4
Visiting i = 1, j = 4
Visiting i = 0, j = 4
Solution i = 0, j = 4
Route found to exit !!!
Solution path:
 -  -  -  -  G
 -  S  P  -  P
 -  -  P  -  P
 -  -  P  P  P
 -  -  -  -  -
*/
