# Strongly Connected Components (SCC)
- Connected graph -> a graph is connected if all its vertices are connected
- A graph is **strongly connected** if we can get from any vertex to any other vertex (undirected graphs are stronly connected by definition)
- In directed graphs there are vertices that connot be reached from everywhere
- These clusters can be discovered with **DFS**
- We can find the strongly connected components of a graph in **O(V)** time
- If we shrink each component to a vertex these vertices form a **DAG**, this is the **"condensation"** of the given graph
- So a directed graph is a **DAG** only if there is no subgraph that is strongly connected

- Example:

![SCC1a](docs/SCC1a.png)

![SCC1b](docs/SCC1b.png)

![SCC1c](docs/SCC1c.png)


-----

- A directed cycle is trongly connected
- Every non-trivial strongly connected component contains at least one directed cycle
- **Kosaraju algorithm:** does two **DFSs**, one to get the topological ordering and then on the transposed graph to discover the strongly connected components
  - Transopose of a graph: we reverse each edges -> change he start and end vertex
- **Tarjan algorithm:** it uses only on **DFS** so it is much more popular in practice

#### Applications:
- **<u>Ecology</u>** : to determine the hierarchy of food need
- **<u>Software enginnering</u>**: if we have a huge software and want to create packages to each connected classes we can classify according to the strongly connected components
- **<u>Astrophysics</u>**: If we ahve an image of faint stars and wan tot detect each of them .. it is a good way of finding the clusters !!!
- **<u>Recommender systems: </u>** for example youtube videos
  - We can recommend anothe ritem from the same strongly connected components / cluster !!!

-----
## Kosaraju Algorithm
- We have to use a stack for DFS traversal because we have to track the finishing times
- We need to track whether we have already visited a given node or not
  1. Do a DFS on the original graph and keep pushing the nodes to the stack
  2. Transpose the graph -> reverse the edges
  3. Keep popping the noes from the stack, keep constructing the strongly connected components

- **<u>Step:</u>**
  - **Notes: Visited Set,  Not Visited Stack**

![SCC2a](docs/SCC2a.png)


- **Notes: Visited Set,  Not Visited Stack**

![SCC2b](docs/SCC2b.png)


- **Notes: Visited Set,  Not Visited Stack**

![SCC2c](docs/SCC2c.png)

- **Notes: Visited Set,  Not Visited Stack**

![SCC2d](docs/SCC2d.png)

- **Notes: Visited Set,  Not Visited Stack**

![SCC2e](docs/SCC2e.png)

- **Notes: Visited Set,  Not Visited Stack**

![SCC2f](docs/SCC2f.png)

- **Notes: Visited Set,  Not Visited Stack**

![SCC2g](docs/SCC2g.png)

- **Notes: Visited Set,  Not Visited Stack**

![SCC2h](docs/SCC2h.png)

- **Notes: Visited Set,  Not Visited Stack**

![SCC2i](docs/SCC2i.png)

- **Notes: Visited Set,  Not Visited Stack**

![SCC2j](docs/SCC2j.png)


- **Notes: Visited Set,  Not Visited Stack**

![SCC2k](docs/SCC2k.png)

- **Notes: Visited Set,  Not Visited Stack**

![SCC2l](docs/SCC2l.png)

![SCC2m](docs/SCC2m.png)

![SCC2n](docs/SCC2n.png)

![SCC2o](docs/SCC2o.png)

![SCC2p](docs/SCC2p.png)

![SCC2q](docs/SCC2q.png)

![SCC2r](docs/SCC2r.png)

-------
-------

![SCC2s](docs/SCC2s.png)

-------

![SCC2t](docs/SCC2t.png)

-------

![SCC2u](docs/SCC2u.png)

-------

![SCC2v](docs/SCC2v.png)

-------

![SCC2w](docs/SCC2w.png)

-------

![SCC2x](docs/SCC2x.png)

-------

![SCC2y](docs/SCC2y.png)

-------

### Kosaraju Algorithm Implemtation

```java
import java.util.ArrayList;
import java.util.List;

public class Vertex {

  private int id; // each vertex has an ID, ex: vertex #1,2,3
  private String name;
  private List<Vertex> adjaceciesList;
  private boolean visited;
  private int componentId; // this is SCC id --> ex: vertex 1 has SCC #3

  public Vertex(int id, String name) {
    this.id = id;
    this.name = name;
    this.adjaceciesList = new ArrayList<>();
  }

  public void addNeighour(Vertex vertex) {
    this.adjaceciesList.add(vertex);
  }

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public int getComponentId() { return componentId; }
  public void setComponentId(int componentId) { this.componentId = componentId; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public List<Vertex> getAdjaceciesList() { return adjaceciesList; }
  public void setAdjaceciesList(List<Vertex> adjaceciesList) { this.adjaceciesList = adjaceciesList; }

  public boolean isVisited() { return visited; }
  public void setVisited(boolean visited) { this.visited = visited; }

  @Override
  public String toString(){ return this.name; }

}
```

```java
public class Edge {
  private double weight;
  private Vertex startVertex;
  private Vertex targetVertex;

  public Edge(double weight, Vertex startVertex, Vertex targetVertex) {
    this.weight = weight;
    this.startVertex = startVertex;
    this.targetVertex = targetVertex;
  }

  public double getWeight() { return weight; }
  public void setWeight(double weight) { this.weight = weight; }

  public Vertex getStartVertex() { return startVertex; }
  public void setStartVertex(Vertex startVertex) { this.startVertex = startVertex; }

  public Vertex getTargetVertex() { return targetVertex; }
  public void setTargetVertex(Vertex targetVertex) { this.targetVertex = targetVertex; }
}
```

```java
import java.util.ArrayList;
import java.util.List;

public class Graph {

  private List<Vertex> vertices;
  private List<Edge> edges;

  public Graph() { }

  public Graph(List<Vertex> vertices, List<Edge> edges) {
    this.vertices = vertices;
    this.edges = edges;
  }

  public List<Vertex> getVertexList() { return vertices; }
  public void setVertexList(List<Vertex> vertices) { this.vertices = vertices; }

  public List<Edge> getEdgeList() { return edges; }
  public void setEdgeList(List<Edge> edges) { this.edges = edges; }

  public Graph getTransposeGraph() {
    Graph tranposed = new Graph();

    List<Vertex> tranposedVertexList = new ArrayList<>();

    for(Vertex vertex : this.vertices)
      tranposedVertexList.add(vertex);

    for(Edge edge : this.edges) {
      Vertex start = edge.getStartVertex();
      Vertex target = edge.getTargetVertex();
      Vertex startTranposed = tranposedVertexList.get(tranposedVertexList.indexOf(target));

      startTranposed.addNeighour(start);
    }

    tranposed.setVertexList(tranposedVertexList);
    return tranposed; // Tranposed Graph
  }
}
```

```java
import java.util.Stack;

// Do 1st DFS, load to the Stack
public class DepthFirstOrder {

  private Stack<Vertex> stack;

  public DepthFirstOrder(Graph graph) {
    stack = new Stack<>();

    for(Vertex vertex : graph.getVertexList())
      if(!vertex.isVisited())
        dfs(vertex);
  }

  private void dfs(Vertex vertex) {
    vertex.setVisited(true);

    for(Vertex v : vertex.getAdjaceciesList())
      if (!v.isVisited())
        dfs(v);
    stack.push(vertex);
  }

  public Stack<Vertex> getReversePost() { return this.stack; }
}
```

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 2 DFS take +  tranpose the graph, which reverse all edges direction
// = O(2(V + E)) + O(E) = 2V + 3E = O(V + E)
public class KosarajuAlgorithm {

  // id[v] = id of the SCC conntaining that given vertex
  private int[] id;
  private int count; // number of SCCs
  private boolean[] visitedSet;

  public KosarajuAlgorithm(Graph graph) {

    // Do 1st DFS, load to the Stack
    DepthFirstOrder dfs = new DepthFirstOrder(graph.getTransposeGraph());

    Stack<Vertex> stack = dfs.getReversePost();

    visitedSet = new boolean[graph.getVertexList().size()];
    id = new int[graph.getVertexList().size()];

    // pop from stack, this is 1st current vertex of current SCC
    for(Vertex vertex: stack) {
      if(!visitedSet[vertex.getId()]) {
        dfs(vertex); // call DFS on this current vertex
        count++;
      }
    }
  }

  // assign SSC id to the vertices of current SCC
  private void dfs(Vertex vertex) {
    visitedSet[vertex.getId()] = true;
    id[vertex.getId()] = count;
    vertex.setComponentId(count);

    for(Vertex v : vertex.getAdjaceciesList())
      if(!visitedSet[v.getId()])
        dfs(v);
  }

  public int getCount() { return this.count; }


  public static void main(String[] args) {
    List<Vertex> vertexList = new ArrayList<>();

    vertexList.add(new Vertex(0, "a"));
    vertexList.add(new Vertex(1, "b"));
    vertexList.add(new Vertex(2, "c"));
    vertexList.add(new Vertex(3, "d"));
    vertexList.add(new Vertex(4, "e"));
    vertexList.add(new Vertex(5, "f"));
    vertexList.add(new Vertex(6, "g"));
    vertexList.add(new Vertex(7, "h"));

    // All edges's weight is 1
    List<Edge> edgeList = new ArrayList<Edge>();

    edgeList.add(new Edge(1, vertexList.get(0), vertexList.get(1)));

    edgeList.add(new Edge(1, vertexList.get(1), vertexList.get(2)));
    edgeList.add(new Edge(1, vertexList.get(1), vertexList.get(4)));
    edgeList.add(new Edge(1, vertexList.get(1), vertexList.get(5)));

    edgeList.add(new Edge(1, vertexList.get(2), vertexList.get(3)));
    edgeList.add(new Edge(1, vertexList.get(2), vertexList.get(6)));

    edgeList.add(new Edge(1, vertexList.get(3), vertexList.get(2)));
    edgeList.add(new Edge(1, vertexList.get(3), vertexList.get(7)));

    edgeList.add(new Edge(1, vertexList.get(4), vertexList.get(0)));
    edgeList.add(new Edge(1, vertexList.get(4), vertexList.get(5)));

    edgeList.add(new Edge(1, vertexList.get(5), vertexList.get(6)));

    edgeList.add(new Edge(1, vertexList.get(6), vertexList.get(5)));

    edgeList.add(new Edge(1, vertexList.get(7), vertexList.get(3)));
    edgeList.add(new Edge(1, vertexList.get(7), vertexList.get(6)));



    Graph graph = new Graph(vertexList,edgeList);

    KosarajuAlgorithm kosarajuAlgorithm = new KosarajuAlgorithm(graph);

    System.out.println(kosarajuAlgorithm.getCount());

    for(Vertex vertex : vertexList)
    	System.out.println(vertex+" - "+vertex.getComponentId());
  }
}
/*
3
a - 0
b - 0
c - 1
d - 1
e - 0
f - 2
g - 2
h - 1
*/
```

![SCC3a](docs/SCC3a.png)



## Tarjan algorithm

![SCC4a](docs/SCC4a.png)

- It runs in linear time
- We use depth-first search again for traversing the graph
- **<u>Stack invariant:</u>**
  - When **DFS** recursively visits a node **v** and its descendants -> those nodes are not necessarily popped from the stack when the recursive call returns
  - A node remains on the stack after it has been visited -> if there exists a path in the graph from this node to some node earlier on the stack
- **So at the end of th call that visits v:**
  - We know whether **v** itself has a path to any node earlier on the stack
    - **YES:** **v** has a path to any node earlier on the stack
      - The call returns -> **v** stays on the stack !!!
    - **NO:** **v** does not have a path to any node earlier on the stack
      - The **v** node **must be the root of its strongly connected component !!!**
-------

- Every node has two properties: **index + lowlink**
  - **index:** we assign an integer to the nodes consecutively in the order they are visited by **DFS**
  - **lowlink:** the smallest index of any node known to be reachable from node **v**

    -----
    1. **v.lowlink = v.index**
        - There is no backward edge to any other node
          - The current node is the root of a SCC !!!
    2. **v.lowlink < v.index**
        - We havefound a backward edge

------

![SCC5a](docs/SCC5a.png)

![SCC5b](docs/SCC5b.png)

![SCC5c](docs/SCC5c.png)

![SCC5d](docs/SCC5d.png)

-----
#### Implementation:

```java
public class Vertex {

  private String name;
  private List<Vertex> neighbourList;
  private Vertex predecessor;
  private boolean visited;
  private int lowLink;

  public Vertex(String name) {
    this.name = name;
    this.neighbourList = new ArrayList<>();
  }

  public void addNeighour(Vertex vertex) {
    this.neighbourList.add(vertex);
  }

  public int getLowLink() { return lowLink; }
  public void setLowLink(int lowLink){ this.lowLink = lowLink; }

  public Vertex getPredecessor() { return predecessor; }
  public void setPredecessor(Vertex predecessor) { this.predecessor = predecessor; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public List<Vertex> getNeighbourList() { return neighbourList; }
  public void setNeighbourList(List<Vertex> neighbourList) { this.neighbourList = neighbourList; }

  public boolean isVisited() { return visited; }
  public void setVisited(boolean visited) { this.visited = visited; }

  @Override
  public String toString(){ return this.name; }
}
```

```java
import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

// Only 1 DFS --> O(V + E)
public class TarjanAlgorithm {

  private Stack<Vertex> stack;
  private List<Vertex> vertices;
  private List<List<Vertex>> connectedComponentList;
  private int time = 0; // keep the order of node we've visited
  private int count = 0; // count how many SCC we have

  public TarjanAlgorithm(List<Vertex>  vertices) {
    this.stack = new Stack<>();
    this.vertices = vertices;
    this.connectedComponentList = new ArrayList<>();
  }

  public void runAlgorithm() {
    for(Vertex vertex : this.vertices)
      if(!vertex.isVisited())
        dfs(vertex);
  }

  private void dfs(Vertex vertex) {
    vertex.setLowLink(time++);
    System.out.println("Visiting vertex " + vertex + " with lowLink " + vertex.getLowLink());
    vertex.setVisited(true);
    this.stack.push(vertex);
    boolean isComponentRoot = true;

    for(Vertex v : vertex.getNeighbourList()) {

      if(!v.isVisited()) {
        System.out.println("Recursively visit vertex " + v);
        dfs(v);
      }

      // we have a back edge
      if(vertex.getLowLink() > v.getLowLink()) {
        System.out.println("\nBecause vertex "+vertex+" lowLink (value:"+vertex.getLowLink()+") > vertex " +v +" lowLink (value:"+v.getLowLink()+") --> we set vertex "+vertex+" lowLink = vertex "+v+" lowLink value which is "+v.getLowLink());

        vertex.setLowLink(v.getLowLink());
				System.out.println("So Vertex "+ vertex +" is not the root of a SCC\n");
				isComponentRoot = false;
      }
    }

    if(isComponentRoot) {
      count++;

      System.out.println("Vertex " + vertex + " is the root of an SCC");
      List<Vertex> component = new ArrayList<>();

      while(true) {
        Vertex actualVertex = stack.pop();
        System.out.println("So vertex " + actualVertex + " is in SCC " + count);
        component.add(actualVertex);
        actualVertex.setLowLink(Integer.MAX_VALUE);

        if(actualVertex.getName().equals(vertex.getName()))
          break;
      }

      System.out.println("\n---------------------\n");
      connectedComponentList.add(component);
    }
  }
  public void printComponents(){
    System.out.println(connectedComponentList);
  }


  public static void main(String[] args) {
    List<Vertex> vertices = new ArrayList<>();

    Vertex v1 = new Vertex("1"); vertices.add(v1);
    Vertex v2 = new Vertex("2"); vertices.add(v2);
    Vertex v3 = new Vertex("3"); vertices.add(v3);
    Vertex v4 = new Vertex("4"); vertices.add(v4);
    Vertex v5 = new Vertex("5"); vertices.add(v5);
    Vertex v6 = new Vertex("6"); vertices.add(v6);
    Vertex v7 = new Vertex("7"); vertices.add(v7);

    v1.addNeighour(v5);
    v2.addNeighour(v1);
    v3.addNeighour(v2);
    v3.addNeighour(v4);
    v4.addNeighour(v3);
    v5.addNeighour(v2);
    v6.addNeighour(v2);
    v6.addNeighour(v5);
    v6.addNeighour(v7);
    v7.addNeighour(v6);
    v7.addNeighour(v3);

    TarjanAlgorithm tarjanAlgorithm = new TarjanAlgorithm(vertices);
    tarjanAlgorithm.runAlgorithm();
    tarjanAlgorithm.printComponents();
  }
}

/*
Visiting vertex 1 with lowLink 0
Recursively visit vertex 5
Visiting vertex 5 with lowLink 1
Recursively visit vertex 2
Visiting vertex 2 with lowLink 2

Because vertex 2 lowLink (value:2) > vertex 1 lowLink (value:0) --> we set vertex 2 lowLink = vertex 1 lowLink value which is 0
So Vertex 2 is not the root of a SCC


Because vertex 5 lowLink (value:1) > vertex 2 lowLink (value:0) --> we set vertex 5 lowLink = vertex 2 lowLink value which is 0
So Vertex 5 is not the root of a SCC

Vertex 1 is the root of an SCC
So vertex 2 is in SCC 1
So vertex 5 is in SCC 1
So vertex 1 is in SCC 1

---------------------

Visiting vertex 3 with lowLink 3
Recursively visit vertex 4
Visiting vertex 4 with lowLink 4

Because vertex 4 lowLink (value:4) > vertex 3 lowLink (value:3) --> we set vertex 4 lowLink = vertex 3 lowLink value which is 3
So Vertex 4 is not the root of a SCC

Vertex 3 is the root of an SCC
So vertex 4 is in SCC 2
So vertex 3 is in SCC 2

---------------------

Visiting vertex 6 with lowLink 5
Recursively visit vertex 7
Visiting vertex 7 with lowLink 6

Because vertex 7 lowLink (value:6) > vertex 6 lowLink (value:5) --> we set vertex 7 lowLink = vertex 6 lowLink value which is 5
So Vertex 7 is not the root of a SCC

Vertex 6 is the root of an SCC
So vertex 7 is in SCC 3
So vertex 6 is in SCC 3

---------------------

[[2, 5, 1], [4, 3], [7, 6]]
*/
```

![SCC6a](docs/SCC6a.png)
