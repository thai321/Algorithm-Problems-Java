import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Dijkstra {

  public void computePaths(Vertex sourceVertex) {
    sourceVertex.setDistance(0); // shortest distance from source to source(itself) is 0
    PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
    priorityQueue.add(sourceVertex);

    while(!priorityQueue.isEmpty()) {
      Vertex actualVetex = priorityQueue.poll();

      for(Edge edge : actualVetex.getAdjaceciesList()) {
        Vertex v = edge.getTargetVertex();

        double newDistance = actualVetex.getDistance() + edge.getWeight();

        if(newDistance < v.getDistance()){
          priorityQueue.remove(v);
          v.setDistance(newDistance);
          v.setPredecessor(actualVetex);
          priorityQueue.add(v);
        }
      }
    }
  }

  // get the shortest path from source vertex to targetVertex
  public List<Vertex> getShortestPathTo(Vertex targetVertex) {
    List<Vertex> shortestpathToTarget = new ArrayList<>();
    //BACKTRACK
    for(Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPredecessor()) {
      shortestpathToTarget.add(vertex);
    }
    Collections.reverse(shortestpathToTarget); // reverse the list to get the right path order
    return shortestpathToTarget;
  }

  public static void main(String[] args) {
    Vertex v0 = new Vertex("A");
    Vertex v1 = new Vertex("B");
    Vertex v2 = new Vertex("C");

    v0.addNeighour(new Edge(1,v0,v1));
    v0.addNeighour(new Edge(3,v0,v2));
    v1.addNeighour(new Edge(1,v1,v2));

    Dijkstra algorithm = new Dijkstra();
    algorithm.computePaths(v0);

    System.out.println(algorithm.getShortestPathTo(v2));
    //    1
    // A -- B
    //  `   |
    //  1`  |1
    //    ` |
    //      C
    // ---> shortest path order = [A, C]

    //    1
    // A -- B
    //  `   |
    //  3`  |1
    //    ` |
    //      C
    // ---> shortest path order = [A, B, C]
  }
}
