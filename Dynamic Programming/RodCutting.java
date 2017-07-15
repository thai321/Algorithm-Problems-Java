public class RodCutting {
  private int[][] dpTable;
  private int lengthOfRod;
  private int[] prices;

  public RodCutting(int lengthOfRod, int[] prices) {
    this.prices = prices;
    this.lengthOfRod = lengthOfRod;
    this.dpTable = new int[prices.length + 1][lengthOfRod + 1]; // default values to 0
  }

  public void solve() {
    //initialize: java Array default values to 0

    for(int i = 1; i < prices.length; i++) {
      for(int j = 1; j <= lengthOfRod; j++) {
        if(i <= j)
          dpTable[i][j] = Math.max(dpTable[i-1][j], prices[i] + dpTable[i][j-i]);
        else
          dpTable[i][j] = dpTable[i-1][j];
      }
    }
  }

  public void showResult() {
    System.out.println("Optimal profit: $ " + dpTable[prices.length - 1][lengthOfRod]);

    int n = prices.length - 1, length = lengthOfRod;
    // The End of result if dpTable[n][length] = 0 or n > 0
    while(n > 0) {
      if((dpTable[n][length] != dpTable[n-1][length])) {
        System.out.println("We make cut: " + n + "m");
        length -= n;
      }
      else
        n--;
    }
  }

  public static void main(String[] args) {
    int lengthOfRod = 5;
    int[] prices = {0,2,5,7,3};

    RodCutting cutting = new RodCutting(lengthOfRod, prices);
    cutting.solve();
    cutting.showResult();
    /*
      Optimal profit: $ 12
      We make cut: 2m
      We make cut: 2m
      We make cut: 1m
    */
  }
}
