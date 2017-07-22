import java.util.Random;

public class Game {

  private Board board;
  private Random random;

  public Game() {
    initializeGame();
    displayBoard();
    makeFirstMove();
    playGame();
    checkStatus();
  }

  private void playGame() {
    while(this.board.isRunning()) {
      System.out.println("User move: ");
      Cell userCell = new Cell(board.getScanner().nextInt(), board.getScanner().nextInt());

      this.board.move(userCell, Player.USER);
      displayBoard();

      if(!this.board.isRunning())
        break;

      this.board.callMinimax(0, Player.COMPUTER);

      for(Cell cell : this.board.getRootValues())
        System.out.println("Cell value: " + cell + ", minimaxValue = " + cell.getMinimaxValue());

      this.board.move(board.getBestMove(), Player.COMPUTER);
      displayBoard();
    }
  }

  private void makeFirstMove() {
    System.out.println("Who starts? 1 - Computer ; 2 - User");
    int choice = board.getScanner().nextInt();

    if(choice == 1) {
      int xRandom = random.nextInt(Constants.BOARD_SIZE);
      int yRandom = random.nextInt(Constants.BOARD_SIZE);
      Cell cell = new Cell(xRandom, yRandom);
      this.board.move(cell, Player.COMPUTER);
      displayBoard();
    }
  }

  private void displayBoard() {
    this.board.displayBoard();
  }

  private void checkStatus() {
    if(board.isWinning(Player.COMPUTER)) {
      System.out.println("Computer has won!");
    } else if(board.isWinning(Player.USER)) {
      System.out.println("User has won!");
    } else
      System.out.println("It's a draw!!!");
  }

  private void initializeGame() {
    this.board = new Board();
    this.board.setupBoard();
    this.random = new Random();
  }

  public static void main(String[] args) {
    new Game();
  }
}

/*
Optimize for the Computer Player, not Human Player

- - -
- - -
- - -
#########################
Who starts? 1 - Computer ; 2 - User
2
User move:
1
1

- - -
- O -
- - -
#########################
Cell value: (0,0), minimaxValue = 0
Cell value: (0,1), minimaxValue = -1
Cell value: (0,2), minimaxValue = 0
Cell value: (1,0), minimaxValue = -1
Cell value: (1,2), minimaxValue = -1
Cell value: (2,0), minimaxValue = 0
Cell value: (2,1), minimaxValue = -1
Cell value: (2,2), minimaxValue = 0

X - -
- O -
- - -
#########################
User move:
1
0

X - -
O O -
- - -
#########################
Cell value: (0,1), minimaxValue = -1
Cell value: (0,2), minimaxValue = -1
Cell value: (1,2), minimaxValue = 0
Cell value: (2,0), minimaxValue = -1
Cell value: (2,1), minimaxValue = -1
Cell value: (2,2), minimaxValue = -1

X - -
O O X
- - -
#########################
User move:
0 1

X O -
O O X
- - -
#########################
Cell value: (0,2), minimaxValue = -1
Cell value: (2,0), minimaxValue = -1
Cell value: (2,1), minimaxValue = 0
Cell value: (2,2), minimaxValue = -1

X O -
O O X
- X -
#########################
User move:
2
1

X O -
O O X
- O -
#########################
User has won!
*/
