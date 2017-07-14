# Backtracking
- <b>IT IS A FORM OF RECURSION !!!</b>
- General algorithm for finding all solutions to some computational problems -> <b>"constraint satisfaction problems"</b>
- We incrementally build candidates to the solutions
- If partial candidate A cannot be completed to a valid solution: we abandon A as a solution
- For example: eight-queens problem or sudoku
- Backtracking is often much faster thanbrute force enumeration of all complete candidates, because it can eliminate a large number of candidates with a single test
- Backtracking is an important tool for solving constraint satisfaction problems -> combinatorial optimization problems !!!

### <u>`The method`</u>
- The partial candidates are represented as the nodes of a tree structure
- <b>"Potential search tree"</b>
- Each partial candidate is the parent of the candidates that differ from it by a single extension step
- The leaves of the tree are the partial candidates that cannot be extended any further
- The backtracking algorithm traverses this search tree recursively, from the root down (like <b>DFS</b>)

- This is why backtracking is sometimes called <b>depth-first-search (DFS)</b>

> . .. ..  . .. . .. Root

>. .. . .. ..A ----------- B

> . . . .. C.. .. D  ----    E.. .. F

> . . . . Bad.. .Bad  --   Good..  Bad

<b>Steps:</b>
  1. For every node the algorithm checks whether the given node can be completed to a valid solution
  2. If it cannot -> the whole subtree is skipped!!!
  3. Recursively enumerates all subtree of the node

- We have several options: we can choose A or B at the beginning
- After every choice -> we have a new set of options


## N-queens problem
- The problem of placing N chess queens on an <b>N x N</b> chessboard so that no two queens threaten each other (they will not be able to attack each other)
- We have to consider: queens can move diagonal directions too ...
- The original problem was designed for 8 queens ... the general form is about <b>N</b> queens
- Gauss worked on this problem
- Dijkstra used this problem to illustrate the power of what he called structured programming
```java
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

    for(int rowIndex = 0; rowIndex < numOfQueens \ ; rowIndex++) {
      if(isPlaceValid(rowIndex, colIndex)) {
        chessTable[rowIndex][colIndex] = 1;

        if(setQueens(colIndex + 1))
          return true;

        // BACKTRACKING !!!
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
```

## Hamiltonian Cycle
- <b>G(V,E):</b>
  - V: vertices in the graph
  - E: edges in the graph
- Adjacency list:
  - <b>A(j,j)</b> = { <b>1</b> - if there is a connectionbetween i and j; <b>0</b> - if no connection }
- <b>A <u>hamiltonian path</u> in an undirected graph is a path that visits every node exactly once !!! </b>
- <b>Hamiltonian cycle: the first node and the last node of the path are the same vertices:
  - StartingPoint = EndPoint </b>
- Example:
>          A -- B
>          |    |
>          D -- C
- A valid hamiltonian path is: { a b c d a }
- There may be several hamiltonian paht in a given graph!!!

## Hailtonian Problem
- Determining whether such paths and cycles exist in graphs is the Hamiltonian path problem
- This is an <b>NP-complete</b> complete problem!!!
- <u>Dirac-principle</u>: a simple graph with <b>N</b> vertices is hamiltonian if every vertex has degree <b>N/2</b> or greater (degree is the number)
- Important fact: finding Hamiltonian path is <b>NP-complete</b>, but we can decide whether such path exists in linear time complexity with <b>topological ordering</b>

### <u>Solution:</u>
- Naive approach:
- Generate all possible configurations of the vertices and print a configuration that satisfies the given constraints
- Problem -> if the graph has N vertices, there are <b>N!</b> configurations, so the "solution space" is enormous
- Very very inefficient !!!
- What about contruct a tree?
  - We start at the root node, and want to get to one of the good leaves
  - If we get to a bad leaf: we just "backtrack" and keep moving on by revoking our most recent choice
  - The tree is an abstract model of the possible sequences of choices we could make. Here we do a depth-first search on the tree
  - <u>Problem</u>: hard to construct a tree if <b>N</b> is big!!!

<u>Solution using Backtracking:</u>
- We use recursion to solve the problem
- Create an empty path array and add vertex <b>0</b> to it as the starting vertex
- Add other vertices, starting from the vertex <b>1</b>
- Before adding a vertex, check whether it is adjacent to the previously added vertex + make sure it is not already added
- If we find such a vertex -> we add the vertex as part of the solution
- If we do not find a vertex -> we return false

```java
public class HamiltonianCycle {
  private int numOfVertexes;
  private int[] hamiltonianPath;
  private int[][] adjacencyMatrix;

  public HamiltonianCycle(int[][] adjacencyMatrix) {
    this.adjacencyMatrix = adjacencyMatrix;
    this.hamiltonianPath = new int[adjacencyMatrix.length];
    this.numOfVertexes = adjacencyMatrix.length;

    this.hamiltonianPath[0] = 0; //root, start node
  }

  public void solve() {
    if(!findFeasibleSolution(1))
      System.out.println("No feasible solution ...");
    else
      showHamiltonianPath();
  }

  public boolean findFeasibleSolution(int position) {
    // base case
    if(position == numOfVertexes) {
      int last = hamiltonianPath[position - 1];
      int start = hamiltonianPath[0];
      //if the last vertex connected with start vertex, then it's hamiltonian cycle
      if(adjacencyMatrix[last][start] == 1) // if they connected
        return true;
    }

    // start from the second vertex(node) since first vertex is 0 (root)
    for(int vertexIndex = 1; vertexIndex < numOfVertexes \ ; vertexIndex++) {
      if(findFeasible(vertexIndex, position)) {
        hamiltonianPath[position] = vertexIndex;

        if(findFeasibleSolution(position + 1)) { // recursive next vertex
          return true;
        }
        // BACKTRACK !!!
      }
    }
    return false; // BACKTRACK
  }

  private boolean findFeasible(int vertexIndex, int actualPosition) {
    // first criterion: whether tow nodes are connected
    if(adjacencyMatrix[hamiltonianPath[actualPosition-1]][vertexIndex] == 0 )
      return false;

    // second criterion: whether we have visited it or not
    for(int i = 0; i < actualPosition ; i++)
      if(hamiltonianPath[i] == vertexIndex)
        return false;

    return true;
  }

  private void showHamiltonianPath() {
    System.out.println("Hamiltonian cycle: ");

    for(int i = 0;i < hamiltonianPath.length; i++)
      System.out.print(hamiltonianPath[i] + " ");
    System.out.print(hamiltonianPath[0]); // back to the first node(root) (cycle)
  }

  public static void main(String[] args) {
    int[][] adjacencyMatrix = {
    // A  B  C
      {0, 1, 0}, // A
      {1, 0, 1}, // B
      {0, 1, 0} //  C
    };
    HamiltonianCycle cycle = new HamiltonianCycle(adjacencyMatrix);
    cycle.solve(); // no solution

    int[][] adjacencyMatrix2 = {
      {0,1,1,1,0,0},
      {1,0,1,0,1,0},
      {1,1,1,1,0,1},
      {1,0,1,0,0,1},
      {0,1,0,0,0,1},
      {0,1,1,1,1,1}
    };
    HamiltonianCycle cycle2 = new HamiltonianCycle(adjacencyMatrix2);
    cycle2.solve(); // solution
    // Hamiltonian cycle:
    //  0 1 4 5 2 3 0
  }
}
```
