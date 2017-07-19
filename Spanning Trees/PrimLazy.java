import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimLazy {
  private List<Vertex> unVisitedVertices;
  private List<Edge> spanningTree;
  private PriorityQueue<Edge> edgeHeap;
  private double fullCost;

  public PrimLazy(List<Vertex> unVisitedVertices) {
    this.spanningTree = new ArrayList<>();
    this.unVisitedVertices = unVisitedVertices; // list of all vertices by default
    this.edgeHeap = new PriorityQueue<>();
  }

  // start at any random vertex
  public void primsAlgorithm(Vertex vertex) {
    this.unVisitedVertices.remove(vertex);

    while(!unVisitedVertices.isEmpty()) {

      for(Edge edge : vertex.getAdjaceciesList()) {
        if(this.unVisitedVertices.contains(edge.getTargetVertex())) {
          // This is called because it hasn't modified any content
          // in the heap, just only add and remove
          this.edgeHeap.add(edge);
        }
      }

      Edge minEdge = this.edgeHeap.remove();

      this.spanningTree.add(minEdge);
      this.fullCost += minEdge.getWeigth();

      // visiting this vertex's neighbours in the next iteration
      vertex = minEdge.getTargetVertex(); // for next iteration
      this.unVisitedVertices.remove(vertex);
    }
  }

  public void showMST() {
    System.out.println("The minimum spanningTree cost = " + this.fullCost);

    for(Edge edge : spanningTree)
      System.out.print(edge.getStartVertex() + "  " + edge.getTargetVertex() + " - " + edge.getWeigth() + ", ");
  }


  public static void main(String[] args) {
    List<Vertex> unVisitedVertices = new ArrayList<>();
    Vertex v1 = new Vertex("A"); unVisitedVertices.add(v1);
    Vertex v2 = new Vertex("B"); unVisitedVertices.add(v2);
    Vertex v3 = new Vertex("C"); unVisitedVertices.add(v3);
    Vertex v4 = new Vertex("D"); unVisitedVertices.add(v4);
    Vertex v5 = new Vertex("E"); unVisitedVertices.add(v5);
    Vertex v6 = new Vertex("F"); unVisitedVertices.add(v6);
    Vertex v7 = new Vertex("G"); unVisitedVertices.add(v7);

    Edge e1 = new Edge(v1,v2,2); v1.addEdge(e1); // A -- B : 2
    Edge e2 = new Edge(v1,v3,6); v1.addEdge(e2); // A -- C : 6
    Edge e3 = new Edge(v1,v5,5); v1.addEdge(e3); // A -- E : 5
    Edge e4 = new Edge(v1,v6,10); v1.addEdge(e4); // A -- F : 10
    Edge e5 = new Edge(v2,v5,3); v2.addEdge(e5); // B -- E : 3
    Edge e6 = new Edge(v2,v4,3); v2.addEdge(e6); // B -- D : 3
    Edge e7 = new Edge(v3,v4,1); v3.addEdge(e7); // C -- D : 1
    Edge e8 = new Edge(v5,v4,4); v5.addEdge(e8); // E -- D : 4
    Edge e9 = new Edge(v6,v7,3); v6.addEdge(e9); // F -- G: 3
    Edge e10 = new Edge(v6,v3,2); v6.addEdge(e10); // F -- C: 2
    Edge e11 = new Edge(v7,v4,5); v7.addEdge(e11); // G -- D: 5

  // Making Un-directed graph by adding both direction  A->B, B->A
    Edge e1a = new Edge(v2,v1,2); v2.addEdge(e1a); // A -- B : 2
    Edge e2a = new Edge(v3,v1,6); v3.addEdge(e2a); // A -- C : 6
    Edge e3a = new Edge(v5,v1,5); v5.addEdge(e3a); // A -- E : 5
    Edge e4a = new Edge(v6,v1,10); v6.addEdge(e4a); // A -- F : 10
    Edge e5a = new Edge(v5,v2,3); v5.addEdge(e5a); // B -- E : 3
    Edge e6a = new Edge(v4,v2,3); v4.addEdge(e6a); // B -- D : 3
    Edge e7a = new Edge(v4,v3,1); v4.addEdge(e7a); // C -- D : 1
    Edge e8a = new Edge(v4,v5,4); v4.addEdge(e8a); // E -- D : 4
    Edge e9a = new Edge(v7,v6,3); v7.addEdge(e9a); // F -- G: 3
    Edge e10a = new Edge(v3,v6,2); v3.addEdge(e10a); // F -- C: 2
    Edge e11a = new Edge(v4,v7,5); v4.addEdge(e11a); // G -- D: 5

    PrimLazy primLazy = new PrimLazy(unVisitedVertices);
    primLazy.primsAlgorithm(v4);// pick a random vertex
    primLazy.showMST();// pick a random vertex
  }
}
