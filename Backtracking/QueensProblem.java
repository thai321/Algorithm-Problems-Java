public class QueensProblem {
  private int[][] chessTable;
  private int numOfQueens;

  public QueensProblem(int numOfQueens) {
    this.chessTable = new int[numOfQueens][numOfQueens];
    this.numOfQueens = numOfQueens;
  }

  public void solve() {
    if(setQueens(0)) { // initial with 0 queen
      printQueens();
    } else
      System.out.println("There is no solution...");

  }

  private boolean setQueens(int colIndex) {
    // if the last recursive, colIndex = 3 is valid and call setQueens(4), then we found a solution
    if( colIndex == numOfQueens )
      return true;

    for(int rowIndex = 0; rowIndex < numOfQueens; rowIndex++) {
      if(isPlaceValid(rowIndex, colIndex)) {
        chessTable[rowIndex][colIndex] = 1;

        if(setQueens(colIndex + 1))
          return true;

        // BACKTRACKING !!! reset the value back to 0
        chessTable[rowIndex][colIndex] = 0;
        // set it back to 0, and place this queen in the next position on the rowIndex
      }
    }
    return false;
    // if all the posible position of rows for the current queen ( current colIndex) is invalid
  }

  private boolean isPlaceValid(int rowIndex, int colIndex) {
    // check all columns for the current row
    for(int i = 0; i < colIndex; i++)
      if(chessTable[rowIndex][i] == 1) return false;

    // check diagonal up left
    for(int i = rowIndex, j= colIndex; i >= 0 && j >= 0; i--, j-- )
      if(chessTable[i][j] == 1) return false;


    // check diagonal down left
    for(int i = rowIndex, j = colIndex; i < chessTable.length && j >= 0  ;i++, j--)
      if(chessTable[i][j] == 1) return false;

    return true; // valid move
  }

  private void printQueens() {
    for(int i = 0; i < chessTable.length; i++) {
      for(int j = 0; j < chessTable.length; j++) {
        if(chessTable[i][j] == 1) {
          System.out.print(" Q ");
        } else {
          System.out.print(" - ");
        }
      }
      System.out.println();
    }
  }


  public static void main(String[] args) {
    QueensProblem two = new QueensProblem(2);
    two.solve(); // no solution for 2x2 board

    QueensProblem four = new QueensProblem(4);
    four.solve(); // There is solution for 4x4 board
    /*
     -  -  Q  -
     Q  -  -  -
     -  -  -  Q
     -  Q  -  -
    */
  }
}
