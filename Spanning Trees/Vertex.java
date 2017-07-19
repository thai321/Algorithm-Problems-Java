import java.util.ArrayList;
import java.util.List;


public class Vertex {

  private String name;
  private boolean visited;
  private Vertex predecessor;
  private List<Edge> adjaceciesList;
  private Node node; // for Kruskal algorithm

  public Vertex(String name) {
    this.name = name;
    this.adjaceciesList = new ArrayList<>();
  }

  public void setNode(Node node) { this.node = node; }
  public Node getNode() { return this.node; }

  public void addEdge(Edge edge) { adjaceciesList.add(edge); }

  public List<Edge> getAdjaceciesList() { return adjaceciesList; }
  public void setAdjaceciesList(List<Edge> adjaceciesList) { this.adjaceciesList = adjaceciesList; }


  public void setVisited(boolean visited) { this.visited = visited; }
  public boolean isVisited() { return this.visited; }


  public Vertex getPredecessor() { return predecessor; }
  public void setPredecessor(Vertex predecessor) { this.predecessor = predecessor; }

  @Override
  public String toString() { return this.name; }
}
