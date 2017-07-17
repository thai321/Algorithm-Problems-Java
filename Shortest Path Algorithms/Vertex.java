import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> { // we want to compare 2 vertex by their distances

  private String name;
  private List<Edge> adjaceciesList;

  private boolean visited;
  private Vertex predecessor;
  private double distance = Double.MAX_VALUE;

  public Vertex(String name) {
    this.name = name;
    this.adjaceciesList = new ArrayList<>();
  }

  public void addNeighour(Edge edge) {
    this.adjaceciesList.add(edge);
  }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public List<Edge> getAdjaceciesList() { return adjaceciesList; }
  public void setAdjaceciesList(List<Edge> adjaceciesList) { this.adjaceciesList = adjaceciesList; }


  public boolean isVisited() { return visited; }
  public void setVisited(boolean visited) { this.visited = visited; }


  public Vertex getPredecessor() { return predecessor; }
  public void setPredecessor(Vertex predecessor) { this.predecessor = predecessor; }


  public double getDistance() { return distance; }
  public void setDistance(double distance) { this.distance = distance; }


  @Override
  public String toString(){ return this.name; }

  @Override
  public int compareTo(Vertex otherVertex) {
    return Double.compare(this.distance, otherVertex.getDistance());
    // 0: same, -1: this.distance < otherVertex's distance, 1: this.distance > otherVertex's distance,
  }
}
