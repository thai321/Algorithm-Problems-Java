public class CoinChange {

  // M = amount = totalValue (capacity of coins values $$$)
  //recursive coin change, EXONENTIAL RUNNING TIME O(2^N)
  public int naiveCoinChange(int totalValue, int[] coinValues, int index) { // start at the first coin

    if(totalValue < 0) return 0;// No , there is no possible change
    if(totalValue == 0) return 1; // Yes, there is a way of these coins to get to the totalValue

    if(index == coinValues.length) return 0; // consider every single coin in 1-D array

    int includeCoin = naiveCoinChange(totalValue - coinValues[index], coinValues, index);
    int excludeCoin = naiveCoinChange(totalValue, coinValues, index + 1);

    return includeCoin + excludeCoin;
  }

  // M = amount = totalValue (capacity of coins values $$$)
  public void dpCoinChange(int[] coins, int capacity) {
    int[][] dpTable = new int[coins.length + 1][capacity + 1];

    // intialize the first column to 1
    for(int coinIndex = 0; coinIndex <= coins.length; coinIndex++)
      dpTable[coinIndex][0] = 1;

    // intialize the first row to 0
    for(int capacityIndex = 1; capacityIndex <= capacity; capacityIndex++ )
      dpTable[0][capacityIndex] = 0;

    // O(v*M) , v: coins.length or number of coins, M: capacity
    for(int i = 1; i <= coins.length; i++) {
      for(int j = 1; j <= capacity; j++ ) {
        int numberOfWays = dpTable[i-1][j]; // if coints[i] > j take the previous value which is above row
        if (coins[i-1] <= j)
          numberOfWays += dpTable[i][j-coins[i-1]];

        dpTable[i][j] = numberOfWays;
      }
    }
    System.out.println("Solution: " + dpTable[coins.length][capacity]);
    printTable(dpTable, coins.length, capacity);

  }

  private void printTable(int[][] dpTable, int length, int width) {
    for(int i = 0; i <= length; i++) {
      for(int j = 0; j <= width; j++ ) {
        System.out.print(dpTable[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int[] coinsValues = {1,2,3};
    int totalValue = 5;

    CoinChange change = new CoinChange();
    // 5 possible solution
    System.out.println(change.naiveCoinChange(totalValue, coinsValues, 0));

    change.dpCoinChange(coinsValues, totalValue);
    /*
      Solution: 5 possible ways to use coins {1,2,3} to change upto totalValue of 5
      1 0 0 0 0 0
      1 1 1 1 1 1
      1 1 2 2 3 3
      1 1 2 3 4 5
    */
  }
}
