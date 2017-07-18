import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;

// DAG shortest path method: O(E+V)
// Faster than BellmanFord and Dijkstra
// But NOT able to use if negative edge and there is a cycle in the graph
public class AcyclicShortestPath {

  public void shortestPath(List<Vertex> vertexList, Vertex sourceVertex, Vertex targetVertex) {

    sourceVertex.setDistance(0);
    TopologicalSort topologicalSort = new TopologicalSort();
    topologicalSort.makeTopologicalOrder(vertexList);

    Stack<Vertex> stack = topologicalSort.getTopologicalOrder();

    for(Vertex actualVetex : stack) {

      for(Edge edge : actualVetex.getAdjaceciesList()) {

        Vertex u = edge.getStartVertex();
        Vertex v = edge.getTargetVertex();

        double newDistance = u.getDistance() + edge.getWeight();

        if(newDistance < v.getDistance()) {
          v.setDistance(newDistance);
          v.setPredecessor(u);
        }
      }
    }

    if(targetVertex.getDistance() == Double.MAX_VALUE)
      System.out.println("No shortest path there ... ");
    else
      System.out.println("Target vertex shortest path: " + targetVertex.getDistance());
  }

  public void showShortestsPathTo(Vertex targetVertex) {
    List<Vertex> list = new ArrayList<>();

    Vertex actualVetex = targetVertex;
    list.add(actualVetex);

    while(actualVetex.getPredecessor() != null) {
      actualVetex = actualVetex.getPredecessor();
      list.add(actualVetex);
    }

    Collections.reverse(list);
    System.out.println(list);
  }

  public static void main(String[] args) {
    List<Vertex> vertexList = new ArrayList<>();

    Vertex v0 = new Vertex("A");
    Vertex v1 = new Vertex("B");
    Vertex v2 = new Vertex("C");
    Vertex v3 = new Vertex("D");
    Vertex v4 = new Vertex("E");
    vertexList.add(v0);
    vertexList.add(v1);
    vertexList.add(v2);
    vertexList.add(v3);
    vertexList.add(v4);

    v0.addNeighour(new Edge(2,v0,v2));
    v0.addNeighour(new Edge(10,v0,v3));

    v1.addNeighour(new Edge(1,v1,v3));

    v2.addNeighour(new Edge(3,v2,v1));
    v2.addNeighour(new Edge(3,v2,v4));

    v4.addNeighour(new Edge(3,v4,v1));
    v4.addNeighour(new Edge(6,v4,v3));

    AcyclicShortestPath acyclicShortestPath = new AcyclicShortestPath();
    acyclicShortestPath.shortestPath(vertexList, v0, v3); // from A to D
    acyclicShortestPath.showShortestsPathTo(v3);

    /*
      Target vertex shortest path: 6.0
      [A, C, B, D]
    */
  }
}
