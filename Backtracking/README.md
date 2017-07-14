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
  private int numOfVertices;
  private int[] hamiltonianPath;
  private int[][] adjacencyMatrix;

  public HamiltonianCycle(int[][] adjacencyMatrix) {
    this.adjacencyMatrix = adjacencyMatrix;
    this.hamiltonianPath = new int[adjacencyMatrix.length];
    this.numOfVertices = adjacencyMatrix.length;

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
    if(position == numOfVertices) {
      int last = hamiltonianPath[position - 1];
      int start = hamiltonianPath[0];
      //if the last vertex connected with start vertex, then it's hamiltonian cycle
      if(adjacencyMatrix[last][start] == 1) // if they connected
        return true;
    }

    // start from the second vertex(node) since first vertex is 0 (root)
    for(int vertexIndex = 1; vertexIndex < numOfVertices \ ; vertexIndex++) {
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

## Coloring problem
- <b>NP-complete problem</b> problem !!! ~ exponential running time
- <u>Problem:</u> coloring the vertices of a graph such that no two adjacent vertices share the same color
- This is called a vertex coloring
- Reached popularity with the general public in the form of the popular number puzzle Sudoku
- The smallest number of colors needed to color a graph <b>G</b> is called its <b>chromatic number</b>
- There may be more than one solution: for example we can color a graph with 4 vertices in 12 ways with 3 colors !!!

### Application:

#### <u>Bipartite graphs</u>
- Determining if a graph can be colored with 2 colors is equivanlent to determining whether or not the graph is bipartite, and thus computable in linear time using breadth-first search
- Bipartite graph: a graph whose vertices can be divided into two disjoint sets U and V (U and V are independent sets) such that every edge connects a vertex in U to one in V.

#### <u>Making schedules</u>
- We want to make an exam schedule for a university. We have different subjects and different students enrolled on every subject. Many subjects would have common students.
- <b> How do we schedule the exam so that no two exams with a common student are scheduled at the same time? How many minimum time slots are needed to scheule all exams?</b>
- This problem can be represented as a graph where every vertex is a subject and an edge between two vertices means there is a comon student.
  - So this is a graph coloring problem wherer minimum number of time slots is equal to the chromatic number of the graph.

#### <u>Radio frequency assignment</u>
- When frequencies are assigned to towers, frequencies assigned to all towers at the same location must be different because of the interference !!!
- <b>How to assign frequencies with this constraint? what is the minimum number of frequencies needed?</b>
- This problem is also an instance of graph coloring problem where every tower represents a vertex and an edge between two towers represents that they are in range of each other

#### <u>Register allocation</u>
- In compiler optimization, register allocation is the provess of assigning a large number of target program variables onto a small number of CPU registers

## <u>Map coloring</u>
- We want to construct a map of countries or states where adjacent countries or states can not be assigned the same color
- <b>This is "typical" coloring problem </b>
- Four colors are sufficient to color any map --> <b>"four color theorem"</b>

<b><u>Solution:</b></u>
- Greedy approach -> finds the solution but not the most optimal one
  - It may uses more colors than necessary !!!
- Powerll-Welsh alogirthm -> relies on sorting the nodes according to the degrees + we start assigning colors to the nodes with the most neighbors !!!
- BACKTRACKING
### BACKTRACKING solution:
- We assign colors one by one to different vertices starting from the first vertex (optional)
- Before assigning a color -> we check for safety by considering already assigned colors to the adjacent vertices
- If we find a color assignment which is feasible -> we mark th ecolor assignment as part of solution
- If we do not find a color due to clashes (same color) -> we backtrack!!!

```java
public class ColoringProblem {
  private int numOfVertices;
  private int numOfColors;
  private int[] colors; // like a path of , index is vertex (0,1,2...) and value is color (0,1,2...)
  private int[][] adjacencyMatrix;

  public ColoringProblem(int[][] adjacencyMatrix, int numOfColors) {
    this.adjacencyMatrix = adjacencyMatrix;
    this.numOfColors = numOfColors;
    this.numOfVertices = adjacencyMatrix.length;
    this.colors = new int[numOfVertices];
  }

  public void solve() {
    if(solveProblem(0))
      showResults();
    else
      System.out.println("No solution ... ");
  }

  private boolean solveProblem(int nodeIndex) {
    if(nodeIndex == numOfVertices) // base case, found a solution
      return true;

    for(int colorIndex = 1; colorIndex <= numOfColors; colorIndex++) {
      if(isColorValid(nodeIndex, colorIndex)) {
        colors[nodeIndex] = colorIndex;

        if(solveProblem(nodeIndex + 1))
          return true;

        // BACKTRACK!!! Find a different color for this given node (nodeIndex)
      }
    }
    return false;
  }

  private boolean isColorValid(int nodeIndex, int currentColor) {
    for(int i = 0; i < numOfVertices; i++)
      if ((adjacencyMatrix[nodeIndex][i] == 1) && (colors[i] == currentColor))
        return false;
    return true;
  }

  private void showResults() {
    for(int i = 0; i < numOfVertices; i++)
      System.out.println("Node " + (i + 1) + " has color index: " + colors[i]);
  }

  public static void main(String[] args) {
    int[][] adjacencyMatrix = new int[][] {
      {0,1,0,1,0},
      {1,0,1,1,0},
      {0,1,0,1,0},
      {1,1,1,0,1},
      {0,0,0,1,0}
    };
    int numOfColors = 3;
    ColoringProblem color = new ColoringProblem(adjacencyMatrix, numOfColors);
    color.solve();

    /*
      numOfColors = 2 --> No solution
      numOfColors = 3 --> There is a solution
      Node 1 has color index: 1
      Node 2 has color index: 2
      Node 3 has color index: 1
      Node 4 has color index: 3
      Node 5 has color index: 1
    */
  }
}
```


## Knight's tour problem
- A sequence of moves of a knight on a chessboard such that the knight visits every square <b>EXACTLY</b> once
- Colsed tour: when the knight end point is the same as the starting point
- The knight's tour problem is an instance of the more general Hamiltonian-path problem
- Closed knight tour ~ hamiltonian-cycle problem!!!
- <u>Solutions:</u> <b>brute-force approach + backtracking</b>

### <u>Schwenk Theorem</u>
- For an <b>m x n</b> chessboard the closed knight tour problem is always feasible, unless:
  - <b>m and n</b> are both odds
  - m = 1,2, or 4
  - m = 3 and n = 4,6, or 8

### <u>Backtracking</u>
- Start with an empty solution matrix/ <b>2D array</b>
- When adding a new item -> we check whether adding the current item violates the problem constraints or not
- <b>Yes:</b> we backtrack
- <b>No:</b> we add the item to the solution set and go to the next item
- If we have considered all the items we are ready!!!

```java
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
```

 ## Maze Problem
- It is an important problem in robotics: how to navigate a given robot
- For example: vacuum cleaner
- There may be several obstacles
- So again -> there are constraints // obstacles
- It is like a <b>depth-first search</b>

```java
public class MazeProblem {
  private int[][] mazeTable;
  private int[][] solutionTable;
  private int mazeSize;

  public MazeProblem(int[][] mazeTable) {
    this.mazeTable = mazeTable;
    this.mazeSize = mazeTable.length;
    this.solutionTable = new int[mazeSize][mazeSize];
  }

  public void solve() {
    if(solveMaze(0,0)) // start at posision 0 0
      showResults();
    else
      System.out.println("No solution ... ");
  }

  private boolean solveMaze(int x, int y) {
    if( isFinished(x,y) )
      return true;

    if( isValid(x,y)) {
      solutionTable[x][y] = 1;

      if(solveMaze(x + 1, y)) // go right
        return true;
      if(solveMaze(x, y + 1)) // go up
        return true;

      // BACKTRACK !!!
      solutionTable[x][y] = 0; // set back to default of 0
    }

    return false;
  }

  private boolean isFinished(int x, int y) {
    // stand at next to the gate solution (cell solution)
    if ((x == mazeSize - 1) && (y == mazeSize - 1)) {
      solutionTable[x][y] = 1;
      return true;
    }
    return false;
  }

  private boolean isValid(int x, int y) {
    boolean xValid = (x >= 0 && x < mazeSize);
    boolean yValid = (y >= 0 && y < mazeSize);
    return xValid && yValid && (mazeTable[x][y] == 1); // open position
  }

  private void showResults() {
    for(int i = 0; i < mazeSize; i++) {
      for(int j = 0; j< mazeSize; j++) {
        if(solutionTable[i][j] == 1)
          System.out.print(" S ");
        else
          System.out.print((" - "));
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    int mazeTable[][] = {
                          {1, 1, 1, 1, 1},
                          {1, 0, 0, 1, 0},
                          {0, 0, 0, 1, 0},
                          {1, 1, 1, 1, 1},
                          {1, 1, 1, 0, 1}
                        };
    MazeProblem f = new MazeProblem(mazeTable);
    f.solve();
    /*
      S  S  S  S  -
      -  -  -  S  -
      -  -  -  S  -
      -  -  -  S  S
      -  -  -  -  S
    */
  }
}
```
