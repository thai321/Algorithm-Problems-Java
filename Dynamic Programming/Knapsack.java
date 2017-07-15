public class Knapsack {
  private int numOfitems;
  private int capacityOfKnapsack; // max weight
  private int[][] knapsackTable;
  private int totalBenefit;
  private int[] weights; // list of weights of items
  private int[] values; // list of values of items

  public Knapsack(int numOfitems, int capacityOfKnapsack, int[] weights, int[] values) {
    this.numOfitems = numOfitems;
    this.capacityOfKnapsack = capacityOfKnapsack;
    this.weights = weights;
    this.values = values;
    this.knapsackTable = new int[numOfitems + 1][capacityOfKnapsack + 1];
    // add 1 because we need an extra row for 0 item
    // and extra column for 0 weight
  }

  public void solve() {
    // time complexity: O(N*W), N is number of items
    // ignore the first row and first column because it's all 0
    for(int i = 1; i < numOfitems + 1; i++) {
      for(int w = 1; w< capacityOfKnapsack + 1; w++) {
        int notTakingItem = knapsackTable[i-1][w]; // not taking item i
        int takingItem = 0; // default is 0

        // make sure that w-weight[i] >= 0
        if (weights[i] <= w) {
          takingItem = values[i] + knapsackTable[i-1][w-weights[i]];
        }
        knapsackTable[i][w] = Math.max(notTakingItem, takingItem);
      }
    }
    totalBenefit = knapsackTable[numOfitems][capacityOfKnapsack];
  }

  public void showResult() {
    System.out.println("Total benefit: " + totalBenefit);

    for(int n = numOfitems, w = capacityOfKnapsack; n > 0; n--) {
      int currentValue = knapsackTable[n][w];
      int previousValue = knapsackTable[n-1][w]; // 1 row above
      // if the currentValue is equal to the previousValue
      // means we don't take this n-th item. --> next
      if (currentValue != 0 && currentValue != previousValue) {
        System.out.println("We take item: # " + n);
        w = w - weights[n];
      }
    }
  }

  public static void main(String[] args) {
    int numOfitems = 3;
    int capacityOfKnapsack = 5; // max weight

    int[] weights = new int[numOfitems + 1]; // weight of items
    weights[1] = 4;
    weights[2] = 2;
    weights[3] = 3;

    int[] values = new int[numOfitems + 1]; // profit of items
    values[1] = 10;
    values[2] = 4;
    values[3] = 7;

    Knapsack knapsack = new Knapsack(numOfitems, capacityOfKnapsack, weights, values);
    knapsack.solve();
    knapsack.showResult();
    /*
      Total benefit: 11
      We take item: # 3
      We take item: # 2
    */
  }
}
