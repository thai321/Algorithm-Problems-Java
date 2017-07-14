public class KnightTour {

  private static final int BOARD_SIZE = 8;
  private static final int NUM_MOVES = 8;

  private int[][] solutionMatrix;
  private int[] xMoves = { 2, 1, -1, -2, -2, -1,  1,  2 }; // positive: right, negative: left
  private int[] yMoves = { 1, 2,  2,  1, -1, -2, -2, -1 }; // positive: up, negative: down

  public KnightTour() {
    this.solutionMatrix = new int[BOARD_SIZE][BOARD_SIZE];
    initializeBoard();
  }

  private void initializeBoard() {
    for(int i = 0; i < BOARD_SIZE; i++)
      for(int j = 0; j < BOARD_SIZE; j++)
        solutionMatrix[i][j] = -1; // empty cell
  }

  public void solveTour() {
    solutionMatrix[0][0] = 1; // starting point at [0][0] with first step of 1
    // solve the problem with the 2nd step given that first step position at [0][0]
    if(solve(2,0,0))
      printSolution();
    else
      System.out.println("No feasible solution ... ");
  }

  private boolean solve(int stepCount, int x, int y) {
    if(stepCount == BOARD_SIZE*BOARD_SIZE + 1)
      return true;

    for(int i = 0; i < NUM_MOVES; i++) { // a kight can only make 8 moves

      int nextX = x + xMoves[i];
      int nextY = y + yMoves[i];
      if(isStepValid(nextX, nextY)) {
        solutionMatrix[nextX][nextY] = stepCount;

        if(solve(stepCount + 1, nextX, nextY))
          return true;

        // BACKTRACK!!! reset the value back to default
        solutionMatrix[nextX][nextY] = -1;
      }
    }
    return false;
  }

  private boolean isStepValid(int currentX, int currentY) {
    boolean xValid = (currentX < BOARD_SIZE) && (currentX >= 0);
    boolean yValid = (currentY < BOARD_SIZE) && (currentY >= 0);
    return xValid && yValid && (solutionMatrix[currentX][currentY] == -1);
  } // valid x and y coordinate, and its position is empty -> spot is available

  private void printSolution() {
    for(int i = 0; i < BOARD_SIZE; i++) {
      for(int j = 0; j < BOARD_SIZE; j++) {
        int n = solutionMatrix[i][j];
        if (numDigits(n) == 2)
          System.out.print(solutionMatrix[i][j] + " ");
        else // single digit or 1 digit
          System.out.print(solutionMatrix[i][j] + "  ");
      }
      System.out.println();
    }
  }

  // calculate the numer of digits of a given number n
  private int numDigits(int n) {
    return (int)(Math.log10(n) + 1);
  }

  public static void main(String[] args) {
    KnightTour f = new KnightTour();
    f.solveTour();
    /*
      1  60 39 34 31 18 9  64
      38 35 32 61 10 63 30 17
      59 2  37 40 33 28 19 8
      36 49 42 27 62 11 16 29
      43 58 3  50 41 24 7  20
      48 51 46 55 26 21 12 15
      57 44 53 4  23 14 25 6
      52 47 56 45 54 5  22 13
    */
  }
}
