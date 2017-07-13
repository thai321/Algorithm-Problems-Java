public class TowerOfHanoi {

  public void solveHanoi(int n , char rodFrom, char middleRod, char rodTo) {
    if (n == 1) { // base case is the number of plates we handle is equal to 1
      System.out.println("Plate 1 from " + rodFrom + " to " + rodTo);
      return;
    }

    // We to have shift n-1 plates from the 1st rod to middle rod
    solveHanoi(n - 1, rodFrom, rodTo, middleRod);

    // Move the current bigest (from stack) disk from 1st rod to 3rd rod
    System.out.println("Plate " + n + " from " + rodFrom + " to " + rodTo);

    // After move bigest disk to the 3rd rod
    // Move n - 1 plates from middle rod to 3rd rod
    solveHanoi(n - 1, middleRod, rodFrom, rodTo);
  }
  public static void main(String[] args) {
    TowerOfHanoi f = new TowerOfHanoi();
    f.solveHanoi(3, 'A', 'B', 'C');
  }
}
/*
(3, A, B, C) --> (2, A, C, B) -> (1, A, B, C) base case
Plate 1 from A to C  (1, A, B, C) --> base case

(2, A, C, B) pop from stack
Plate 2 from A to B  (2, A, C, B) ; from solveHanoi(2, rodFrom, rodTo, middleRod);

(2, A, C, B) -> (1, C, A, B) base case
Plate 1 from C to B  (1, C, A, B) ; from solveHanoi(1, middleRod, rodFrom, rodTo);

(3, A, B, C) largest disk, pop from stack
Plate 3 from A to C  (3, A, B, C) ; from solveHanoi(3, rodFrom, middleRod, rodTo);

(2, B, A, C) --> (1, B, C, A) base case
Plate 1 from B to A  (1, B, C, A) ; from solveHanoi(1, middleRod, rodFrom, rodTo);

(2, B, A, C) pop from stack
Plate 2 from B to C ; from solveHanoi(2, rodFrom, rodTo, middleRod);

(2, B, A, C) --> (1, A, B ,C) base case
Plate 1 from A to C ; from solveHanoi(1, middleRod, rodFrom, rodTo);
*/
