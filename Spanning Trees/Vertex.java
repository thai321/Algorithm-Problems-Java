import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex>{

  private String name;
  private Edge minEdge; // shortest edge to the actual MST from a non MST vertex
  private boolean visited;

  // to detect whether heap is in need of refresh because of better weighted edge
  private double distance = Double.POSITIVE_INFINITY;

  private List<Edge> adjaceciesList;
  private Node node; // for Kruskal algorithm

  public Vertex(String name) {
    this.name = name;
    this.adjaceciesList = new ArrayList<>();
  }

  public Edge getMinEdge() { return minEdge; }
  public void setMinEdge(Edge edge) { this.minEdge = edge; }

  public double getDistance() { return distance; }
  public void setDistance(double distance) { this.distance = distance; }

  public void addEdge(Edge edge) { adjaceciesList.add(edge); }

  public List<Edge> getAdjaceciesList() { return adjaceciesList; }
  public void setAdjaceciesList(List<Edge> adjaceciesList) { this.adjaceciesList = adjaceciesList; }

  public void setVisited(boolean visited) { this.visited = visited; }
  public boolean isVisited() { return this.visited; }

  public void setNode(Node node) { this.node = node; }
  public Node getNode() { return this.node; }


  @Override
  public String toString() { return this.name; }

  @Override
  public int compareTo(Vertex otherVertex) {
    return Double.compare(this.distance, otherVertex.getDistance());
  }
}
