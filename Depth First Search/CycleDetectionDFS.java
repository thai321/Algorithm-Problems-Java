import java.util.ArrayList;
import java.util.List;

public class CycleDetectionDFS {

  // in case if there is more than 1 un-connected graphs
  public void detectCycle(List<VertexCycle> vertexList) {
    for(VertexCycle v : vertexList)
      if(!v.isVisited())
        dfs(v);
  }

  // if there is an connected graph, we just start at a root vertex
  private void dfs(VertexCycle vertex) {

    System.out.println("DFS on vetex " + vertex);
    vertex.setBeingVisited(true);

    // iterate all the neighbours(v's) of this vertex
    for(VertexCycle v : vertex.getNeighbourList() ) {

      System.out.println("Visiting the neighbours of vertex " + vertex);

      // if this vertex (being visited) is visiting a vertex v that is being visited --> Cycle
      if(v.isBeingVisited()) {
        System.out.println("There is a backward edge --> There is Cycle !!!");
        return ;
      }
      if(!v.isVisited()){
        System.out.println("Visiting vertex " + v + " recursively...");
        v.setVisited(true);
        dfs(v);
      }
    }

    System.out.println("Set vertex " + vertex + " setBeingVisited(false) and visited(true) ... \n");
    // reach the end (leaf) --> no longer in visiting process
    vertex.setBeingVisited(false);
    vertex.setVisited(true);
  }

  public static void main(String[] args) {
    VertexCycle v1 = new VertexCycle("1");
    VertexCycle v2 = new VertexCycle("2");
    VertexCycle v4 = new VertexCycle("4");
    VertexCycle v5 = new VertexCycle("5");
    VertexCycle v8 = new VertexCycle("8");
    VertexCycle v9 = new VertexCycle("9");

    //Cycle
    v1.addNeighbour(v2); v5.addNeighbour(v1);
    v2.addNeighbour(v4); v2.addNeighbour(v5);

    v4.addNeighbour(v8); v4.addNeighbour(v9);

    List<VertexCycle> vertices = new ArrayList<>();
    vertices.add(v1);
    vertices.add(v2);
    vertices.add(v4);
    vertices.add(v5);
    vertices.add(v8);
    vertices.add(v9);

    CycleDetectionDFS f = new CycleDetectionDFS();
    f.detectCycle(vertices);
  }
}
/*
DFS on vetex 1
Visiting the neighbours of vertex 1
Visiting vertex 2 recursively...
DFS on vetex 2
Visiting the neighbours of vertex 2
Visiting vertex 4 recursively...
DFS on vetex 4
Visiting the neighbours of vertex 4
Visiting vertex 8 recursively...
DFS on vetex 8
Set vertex 8 setBeingVisited(false) and visited(true) ...

Visiting the neighbours of vertex 4
Visiting vertex 9 recursively...
DFS on vetex 9
Set vertex 9 setBeingVisited(false) and visited(true) ...

Set vertex 4 setBeingVisited(false) and visited(true) ...

Visiting the neighbours of vertex 2
Visiting vertex 5 recursively...
DFS on vetex 5
Visiting the neighbours of vertex 5
There is a backward edge --> There is Cycle !!!
Set vertex 2 setBeingVisited(false) and visited(true) ...

Set vertex 1 setBeingVisited(false) and visited(true) ...
*/
