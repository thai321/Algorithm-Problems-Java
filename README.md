# Algorithm Problems Java
## Recursion
## Backtracking
## Selection
## Dynamic Programming

## Graph
- Graphs <b>G(V,E)</b> are mathematical structures to model pairwise relations between given objects
- A graph is made up of vertices/nodes and edges
- There are two types of graphs: <b>directed and undirected graphs</b>
- We know wht are graphs
  - First of all how to model them in programming languages?
    1. Adjacency matrix
      - We have an <A> matrix constructed out of the vertices of the graph:
        - the <b>A(i,j)</b> value in the matrix is <b>1</b> if there is a connection between node <b>i</b> and node <b>j</b>
        - Otherwise <b>A(i,j)</b> is <b>0</b>
    2. Edge list representation
      - We create a Vertix class
        - it stores the neighbors accordingly
          ```java
          class Vertex
            vertex Name:
            visited;
            Vertex[] neighbors;
          ```

## Breadth First Search

### Memory complexity: BFS vs DFS

- <b>In BFS</b>, at the leaves -> if we have N items stored in the balanced tree, then there will be <b>N/2</b> leave nodes.
  - So we have to store <b>O(N)</b> items if we want to traverse a tree that contains <b>N</b> items!!!
- <b>In DFS</b>, we ahve to backtrack (pop item from stack): so basically we just have to store as many items n the stack as the height of the tree -> which is <b>log N</b> !!!
  - so the memory complexity will be <b>O(log N)</b>
- That's why depth-first search is preferred most of the times. However, there may be some situations where VFS is better: AI, robot movements,..
