import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

//BellmanFord algorithm: O(E*V)
public class BellmanFord {

  private List<Edge> edgeList;
  private List<Vertex> vertexList;

  public BellmanFord(List<Edge> edgeList, List<Vertex> vertexList) {
    this.edgeList = edgeList;
    this.vertexList = vertexList;
  }

  public void bellmanFord(Vertex sourceVertex) {
    sourceVertex.setDistance(0);

    // (V-1) iterations --> we relax all the edges
    for(int i = 0; i < vertexList.size() - 1; i++) {

      for(Edge edge : edgeList) {

        Vertex u = edge.getStartVertex();
        Vertex v = edge.getTargetVertex();

        if(u.getDistance() == Double.MAX_VALUE) {
          continue;
        }

        double newDistance = u.getDistance() + edge.getWeight();
        if(newDistance < v.getDistance()) {
          v.setDistance(newDistance);
          v.setPredecessor(u);
        }
      }
    }

    // A Final scan of all the edges is performed and if any distance
    //is updated -> means there is a negative cycle !!!
    for(Edge edge : edgeList) { // V-th iteration
      Vertex u = edge.getStartVertex();
      Vertex v = edge.getTargetVertex();

      if(u.getDistance() != Double.MAX_VALUE) {
        if(u.getDistance() + edge.getWeight() < v.getDistance()) { // Has NEGATIVE Cycle?
          System.out.println("There has been a NEGATIVE CYCLE detected ... ");
          return;
        }
      }
    }
  }

  public List<Vertex> shortestPathTo(Vertex targetVertex) {
    List<Vertex> shortestpathToTarget = new ArrayList<>();

    if(targetVertex.getDistance() == Double.MAX_VALUE) {
      return shortestpathToTarget;
    }


    System.out.println("There is a shortest path from source to target, with cost: "
     + targetVertex.getDistance());
    // BACKTRACK

    for(Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPredecessor()) {
      shortestpathToTarget.add(vertex);
    }

    Collections.reverse(shortestpathToTarget); // reverse the list to get the right path order
    return shortestpathToTarget;
  }

  public static void main(String[] args) {
    List<Vertex> vertexList = new ArrayList<>();
    List<Edge> edgeList = new ArrayList<>();
    List<Vertex> shortestpathToTarget = new ArrayList<>();

    Vertex v0 = new Vertex("A");
    Vertex v1 = new Vertex("B");
    Vertex v2 = new Vertex("C");

    Edge e0 = new Edge(1,v0,v1);
    Edge e1 = new Edge(2,v1,v2);
    Edge e2 = new Edge(-11,v2,v0);

    vertexList.add(v0);
    vertexList.add(v1);
    vertexList.add(v2);

    edgeList.add(e0);
    edgeList.add(e1);
    edgeList.add(e2);

    BellmanFord bellmanFord = new BellmanFord(edgeList, vertexList);
    bellmanFord.bellmanFord(v0); // start from source vertex v0
    shortestpathToTarget = bellmanFord.shortestPathTo(v2); // to C
    System.out.println(shortestpathToTarget);

  }
}
