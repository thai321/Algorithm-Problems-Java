public class SubsetSum {
  private boolean[][] dpTable;
  private int[] S;
  private int sum;

  public SubsetSum(int[] S, int sum) {
    this.S = S;
    this.sum = sum;
    this.dpTable = new boolean[S.length + 1][sum + 1];
  }

  public void solve() {
    // initialize
    for(int i = 0; i < S.length + 1; i++)
      dpTable[i][0] = true;
    // java default boolean array value to false
    // therefore the first row dpTable[0][i] is all false, except 1st value is true


    for(int i = 1; i < S.length + 1; i++)
      for(int j = 1; j < sum + 1; j++) {
        if( dpTable[i-1][j] || (j < S[i-1]))
          dpTable[i][j] = dpTable[i-1][j];
        else
          dpTable[i][j] = dpTable[i-1][j - S[i-1]];
      }
    System.out.println("Solution: " + dpTable[S.length][sum]);
  }

  public void showResult() {
    int colIndex = sum;
    int rowIndex = S.length;
    System.out.println("Sum = " + sum);
    // start witht the value of dpTable[S.length][sum] which is true
    while(colIndex > 0 || rowIndex > 0) {
      if(dpTable[rowIndex][colIndex] == dpTable[rowIndex-1][colIndex]) {
        rowIndex -= 1;
      } else {
        System.out.println("Take: " + S[rowIndex-1]);
        colIndex -= S[rowIndex-1];
        rowIndex -=1;
      }
    }
  }

  public static void main(String[] args) {
    int[] numbers = {5,2,3,1};
    int sum = 9;

    SubsetSum f = new SubsetSum(numbers, sum);
    f.solve();
    f.showResult();
    /*
      Solution: true
      Sum = 9
      Take: 1
      Take: 3
      Take: 5
    */
  }
}
