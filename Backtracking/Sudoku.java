public class Sudoku {
  private static final int BOARD_SIZE = 9;
  private static final int MIN_NUMBER = 1;
  private static final int MAX_NUMBER = 9;
  private static final int BOX_SIZE = 3;

  private int[][] sudokuTable;

  public Sudoku(int[][] sudokuTable) {
    this.sudokuTable = sudokuTable;
  }

  // solving the problem column by column basis
  public void solveProblem() {
    if(solve(0,0)) // starting at 0 0
      showResults();
    else
      System.out.println(("No solution."));
  }

  // solving the problem column by column basis
  private boolean solve(int rowIndex, int columnIndex) {

    // we already solve the whole row of this current index
    if(rowIndex == BOARD_SIZE) {
      columnIndex++;
      if (columnIndex == BOARD_SIZE)
        return true; // reach the end of solution
      else // now reset the rowIndex to 0 to solve the next column's row
        rowIndex = 0;
    }

    // if there is an inital value, then go down to rowIndex by 1
    if(sudokuTable[rowIndex][columnIndex] != 0)
      return solve(rowIndex + 1, columnIndex);


    for(int n = MIN_NUMBER; n <= MAX_NUMBER; n++) {
      if(valid(rowIndex, columnIndex, n)) {
        sudokuTable[rowIndex][columnIndex] = n;

        // go down the rowIndex by 1
        if(solve(rowIndex + 1, columnIndex))
          return true;

        //BACKTRACK !!! reset the value back to 0
        sudokuTable[rowIndex][columnIndex] = 0;
      }
    }
    return false;
  }

  private boolean valid(int x, int y, int n) {
    // check row and column
    for(int i = 0; i < BOARD_SIZE; i++) {
      if(sudokuTable[i][y] == n && i != x) return false; // check row
      if(sudokuTable[x][i] == n && i != y) return false; // check column
    }

    // Check to see if given number n is already in the box
    // 0: 0,1,2 --> offset = 0
    // 1: 3,4,5 --> offset = 3
    // 2: 6,7,8 --> offset = 6
    int xOffset = (x / 3) * BOX_SIZE;
    int yOffset = (y / 3) * BOX_SIZE;
    for(int i = 0; i < BOX_SIZE; i++)
      for(int j = 0; j < BOX_SIZE; j++)
        if(sudokuTable[xOffset + i][yOffset + j] == n) return false;

    return true;
  }

  private void showResults() {
    for(int i = 0; i < BOARD_SIZE; i++) {
      if(i % 3 == 0) System.out.println("");

      for(int j = 0; j < BOARD_SIZE; j++) {
        if(j % 3 == 0) System.out.print(" ");
        System.out.print(sudokuTable[i][j] + " ");
      }
      System.out.println(" ");
    }
  }

  public static void main(String[] args) {
    int sudokuTable[][] = {
      {3, 0, 6, 5, 0, 8, 4, 0, 0},
  		{5, 2, 0, 0, 0, 0, 0, 0, 0},
  		{0, 8, 7, 0, 0, 0, 0, 3, 1},
  		{0, 0, 3, 0, 1, 0, 0, 8, 0},
  		{9, 0, 0, 8, 6, 3, 0, 0, 5},
  		{0, 5, 0, 0, 9, 0, 6, 0, 0},
  		{1, 3, 0, 0, 0, 0, 2, 5, 0},
  		{0, 0, 0, 0, 0, 0, 0, 7, 4},
  		{0, 0, 5, 2, 0, 6, 3, 0, 0}
    };
    Sudoku sudoku = new Sudoku(sudokuTable);
    sudoku.solveProblem();
    /*
      3 1 6  5 7 8  4 9 2
      5 2 9  1 3 4  7 6 8
      4 8 7  6 2 9  5 3 1

      2 6 3  4 1 5  9 8 7
      9 7 4  8 6 3  1 2 5
      8 5 1  7 9 2  6 4 3

      1 3 8  9 4 7  2 5 6
      6 9 2  3 5 1  8 7 4
      7 4 5  2 8 6  3 1 9
    */
  }
}
