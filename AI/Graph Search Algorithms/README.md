## BSF
## DFS

## Iterative deepening DFS
- Very important in AI and robotics
- BFS: very good for local searches BUT it is very very memory consuming
- DFS: can be implemented easily with recursion BUT it keeps going further and futher
- DFS is very memory freindly
- Question: can be construct a search algorithm that inherits the advantages of both DFS and BFS?
- Yes, it is the iterative deepening DFS algorithm!!!

------

- It visits the nodes in the search tree in the same order as DFS, but the cumulative order in which nodes are first visited is effectively breadth-first
- Keep going deeper and deeper in the given tree with DFS on each iteration
- The time complexity of IDDFS in well-balanced trees works out to be the same as DFS
- Disadvantage: we keep recomputing the same problem over and over again ... BUT not so dramatic **O(cN) ~ O(N)** if c is a constant !!!

```java
import java.util.Stack;

public class IDDFS {

  private Node targetNode;
  private boolean isTargetFound;

  public IDDFS(Node targetNode) {
    this.targetNode = targetNode;
  }

  public void runDeepeningSearch(Node startNode) {

    int depth = 0;

    while(!isTargetFound) {
      System.out.println();
      dfs(startNode,depth);
      depth++;
      System.out.println();
    }
  }

  public void dfs(Node startNode, int depth) {
    Stack<Node> stack = new Stack<>();
    startNode.setDepthLevel(0);
    stack.push(startNode);

    while(!stack.isEmpty()) {
      Node actualNode = stack.pop();
      System.out.print(actualNode + " ");

      if(actualNode.getName().equals(targetNode.getName())) {
        System.out.println("\nNode has been found .... ");
        this.isTargetFound = true;
        return;
      }

      // contine and finish the rest of the stack
      // to explore other upper nodes
      if(actualNode.getDepthLevel() >= depth) {
        continue;
      }

      for(Node node : actualNode.getAdjacenciesList()) {
        node.setDepthLevel(actualNode.getDepthLevel() + 1);
        stack.push(node);
      }
    }
  }

  public static void main(String[] args) {
    Node v1 = new Node("A");
    Node v2 = new Node("B");
    Node v3 = new Node("C");
    Node v4 = new Node("D");
    Node v5 = new Node("E");

    v1.addNeighbour(v2);
    v1.addNeighbour(v3);
    v3.addNeighbour(v4);
    v4.addNeighbour(v5);

    IDDFS iddfs =new IDDFS(v5); // target = E
    iddfs.runDeepeningSearch(v1); // start from node A, root
  }
}
/*
A

A C B

A C D B

A C D E
Node has been found ....
*/
```


------

## A* Search
- Very important in AI!!!
- Widely used in pathfinding and graph traversal
- Can solve pathfinding problems in games
- However, in practical travel-routing systems, it is generally outperformed by algorithms which can pre-process the graph to attain better performance (Dijkstra or BFS)
- It is like Dijkstra: A* achieves better time performance by using heuristics
- It uses a knowledge-plus-heuristic cost function of node x (usually denoted f(x)) to determine the order in which the search visits nodes in the tree. the cost function is a sum of two functions:
  - g(x) the known distance from the starting node to the current node x
  - h(x) "heuristic estimate" of the distance from x to the goal
- Note: if h(x)= 0 -> that is the common shortest path problem
- It is like the clssic Dijkstra method, but here we make decisions according to the **f(x)=g(x) + h(x)** function
- Why is it good?
- If there are obstacles in the way between us and the final destination, A* helps to find the best path possible
- Greedy best first search may lead us to dead ends instead !!!
- Manhattan distance: we usually use this kind of heuristic
  - Keep tracking what is the distance between us and the goal
  - NOT the Euclidean distance!!!
