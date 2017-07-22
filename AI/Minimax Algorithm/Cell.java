public class Cell {

  private int x; // row: 0,1,2
  private int y; // col: 0,1,2
  private int minimaxValue;

  public Cell(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setX(int x) { this.x = x; }
  public int getX() { return x; }

  public void setY(int y) { this.y = y; }
  public int getY() { return y; }

  public void setMinimaxValue(int minimaxValue) { this.minimaxValue = minimaxValue; }
  public int getMinimaxValue() { return minimaxValue; }

  @Override
  public String toString(){ return "(" + this.x + "," + this.y + ")"; }
}
