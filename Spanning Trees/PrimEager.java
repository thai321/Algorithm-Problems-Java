import java.util.List;
import java.util.PriorityQueue;

public class PrimEager {

  private List<Vertex> vertices;
  private PriorityQueue<Vertex> heap;

  public PrimEager(Graph graph) {
    this.vertices = graph.getVertexList();
    this.heap = new PriorityQueue<>();
  }

  public void spanningTree() {

    for(Vertex vertex : vertices)
      if(!vertex.isVisited())
        makePrimsJarnik(vertex);
  }

  private void makePrimsJarnik(Vertex vertex) {

    vertex.setDistance(0);
    heap.add(vertex);

    while(!heap.isEmpty()) {
      Vertex v = heap.remove();
      scanNeighbours(v);
    }
  }

  private void scanNeighbours(Vertex vertex) {
    vertex.setVisited(true);

    // visit all the neighbours of vertex
    for(Edge edge : vertex.getAdjaceciesList()) {

      Vertex target = edge.getTargetVertex();
      if(!target.isVisited()) {

      // if true --> there is a cheaper path to target
        if(edge.getWeight() < target.getDistance()) {

          if(heap.contains(target)) // remove the old
            heap.remove(target);


          target.setDistance(edge.getWeight());
          target.setMinEdge(edge); // MST

          this.heap.add(target); // update the new
        }
      }
    }
  }

  public void showMST() {

    double sum = 0.0;
    for(Vertex vertex : vertices) {
      if(vertex.getMinEdge() != null) {
        Edge e = vertex.getMinEdge();
        System.out.println("Edge: " + e.getStartVertex() + " - " + e.getTargetVertex());
        sum += e.getWeight();
      }
    }
    System.out.println("Total Cost = " + sum);
  }

  public static void main(String[] args) {
    Graph graph = new Graph();

    Vertex v1 = new Vertex("A"); graph.addVertex(v1);
    Vertex v2 = new Vertex("B"); graph.addVertex(v2);
    Vertex v3 = new Vertex("C"); graph.addVertex(v3);
    Vertex v4 = new Vertex("D"); graph.addVertex(v4);
    Vertex v5 = new Vertex("E"); graph.addVertex(v5);
    Vertex v6 = new Vertex("F"); graph.addVertex(v6);
    Vertex v7 = new Vertex("G"); graph.addVertex(v7);

    // Making Un-directed graph by adding both direction  A->B, B->A
    Edge e1 = new Edge(v1,v2,2); graph.addEdge(e1); // A -- B : 2
    Edge e2 = new Edge(v1,v3,6); graph.addEdge(e2); // A -- C : 6
    Edge e3 = new Edge(v1,v5,5); graph.addEdge(e3); // A -- E : 5
    Edge e4 = new Edge(v1,v6,10); graph.addEdge(e4); // A -- F : 10
    Edge e5 = new Edge(v2,v5,3); graph.addEdge(e5); // B -- E : 3
    Edge e6 = new Edge(v2,v4,3); graph.addEdge(e6); // B -- D : 3
    Edge e7 = new Edge(v3,v4,1); graph.addEdge(e7); // C -- D : 1
    Edge e8 = new Edge(v5,v4,4); graph.addEdge(e8); // E -- D : 4
    Edge e9 = new Edge(v6,v7,3); graph.addEdge(e9); // F -- G: 3
    Edge e10 = new Edge(v6,v3,2); graph.addEdge(e10); // F -- C: 2
    Edge e11 = new Edge(v7,v4,5); graph.addEdge(e11); // G -- D: 5

    PrimEager primEager = new PrimEager(graph);
    primEager.spanningTree();
    primEager.showMST();
  }
}

/*
Edge: A - B
Edge: D - C
Edge: B - D
Edge: B - E
Edge: C - F
Edge: F - G
Total Cost = 14.0
*/
