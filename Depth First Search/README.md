# Depth First Search (DFS)
- It was investigated as strategy for solving mazes by Tremaux in the 19th century
- It explores as far as possible along each branch before backtracking // BFS was a layer-by-layer algorithm
- Time complexity of travering a graph with <b>DFS: O(V+E)</b>
- Memory complexity: a bit better than that of <b>BFS</b> !!!
- Performance is the approximate the same for both <b>Recursion and Iteration</b>

### <b>Recursion</b>:
```ruby
def dfs(vertex)
  vertex set visited true
  print vertex

  for v in vertex neighbours
      if v is not visited
        dfs(v)
```

### <b>Iteration</b>:
```ruby
def dfs(vertex)
  Stack stack
  vertex set visited true
  stack.push(vertex)

  while stack not empty
    actual = stack.pop()

    for v in actual neighbours
      if v is not visited
        v set visited true
        stack.push(v)
```

![DFS1](images/dfs1.dot.png)
> Stack: {A}

> Stack: {A}; explore A --> Stack: {G F B}

> Stack: {G F B}; explore B --> Stack: {G F D C}

> Stack: {G F D C}; explore C --> Stack: {G F D}; C is leaf

> Stack: {G F D}; explore D --> Stack: {G F E};

> Stack: {G F E}; explore E --> Stack: {G F}; E is leaf

> Stack: {G F}; explore F --> Stack: {G}; F is leaf

> Stack: {G}; explore G --> Stack: {H};

> Stack: {H}; explore H --> Stack: {}; H is leaf

> Stack: {} is empty -> FINFISHED !!!

> Visited order: A B C D E F G H


- <u>Applications</u>
  - Topological ordering
  - Kosaraju algorithm for finding strongly connected components in a graph which can be proved to be very important in recommemndation systems (youtube)
  - Detecting cycles (checking whether a graph is a <b>Directed Acyclic Graph (DAG)</b> or not)
  - Generating amzes OR finding way out of a maze

![DFS2](images/dfs2.dot.png)
Visited Order: 1 3 4 5 2

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS {

  private Stack<Vertex> stack;

  public DFS() {
    this.stack = new Stack<>();
  }

  public void dfs(List<Vertex> vertexList) {
    // For loop here means if you have 2 or more un-connected graph
    // We still want to visit every single vertex
    for(Vertex v : vertexList) {
      if(!v.isVisited()) {
        v.setVisited(true);
        // dfsWithStack(v);
        dfsRecursive(v);
      }
    }
  }

  private void dfsRecursive(Vertex v) {
    System.out.print(v + " ");

    for(Vertex vertex : v.getNeighbourList()) {
      if(!vertex.isVisited()) {
        vertex.setVisited(true);
        dfsRecursive(vertex);
      }
    }
  }

  // sure that we will visit every vertex in a single connected graph
  private void dfsWithStack(Vertex rootVertex) {
    this.stack.add(rootVertex);
    rootVertex.setVisited(true);

    while(!stack.isEmpty()) {
      Vertex actualVertex = stack.pop();
      System.out.println(actualVertex + " ");

      for(Vertex v : actualVertex.getNeighbourList()) {
        if (!v.isVisited()) {
          v.setVisited(true);
          this.stack.push(v);
        }
      }
    }
  }

  public static void main(String[] args) {

    Vertex v1 = new Vertex("1");
    Vertex v2 = new Vertex("2");
    Vertex v3 = new Vertex("3");
    Vertex v4 = new Vertex("4");
    Vertex v5 = new Vertex("5");

    List<Vertex> list = new ArrayList<>();

    v1.addNeighbourList(v2);
    v1.addNeighbourList(v3);
    v3.addNeighbourList(v4);
    v4.addNeighbourList(v5);

    list.add(v1);
    list.add(v2);
    list.add(v3);
    list.add(v4);
    list.add(v5);

    DFS f = new DFS();
    f.dfs(list);
    // With stack; visited order: 1 3 4 5 2 visit right and then left
    // With Recursion; visited order: 1 2 3 4 5 visit left and then right
  }
}
```
