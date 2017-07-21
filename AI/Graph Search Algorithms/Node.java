import java.util.ArrayList;
import java.util.List;

// For IDDFS.java
// public class Node {
//
//   private String name;
//   private int depthLevel = 0;
//   private List<Node> adjacenciesList;
//
//   public Node(String name) {
//     this.name = name;
//     this.adjacenciesList = new ArrayList<>();
//   }
//
//   public void addNeighbour(Node node) { this.adjacenciesList.add(node); }
//
//   public List<Node> getAdjacenciesList() { return adjacenciesList; }
//   public void setAdjacenciesList(List<Node> adjacenciesList) { this.adjacenciesList = adjacenciesList; }
//
//   public String getName() { return name; }
//   public void setName(String name) { this.name = name; }
//
//   public int getDepthLevel() { return depthLevel; }
//   public void setDepthLevel(int depthLevel) { this.depthLevel = depthLevel; }
//
//   @Override
//   public String toString() { return this.name; }
// }


// for AStarSearch.java
public class Node implements Comparable<Node>{

  private String value;
  private List<Edge> adjacenciesList;
  private Node parentNode;

  // G(n) is exact cost of the path from the starting point to any vertex n
  // h(n) represents the heuristic estimated cost from vertex n to the goal
  private double gScore; // g is guess, and h is heuristic
  private double fScore; // f = g + h , h = f - g
  private int x;
  private int y;



  public Node(String value) {
    this.value = value;
    this.adjacenciesList = new ArrayList<Edge>();
  }

  public void addNeighbour(Edge edge) { this.adjacenciesList.add(edge); }

  public String getValue() { return value; }
  public void setValue(String value) { this.value = value; }

  public double getFScore() { return fScore; }
  public void setFScore(double fScore) { this.fScore = fScore; }

  public double getGScore() { return gScore; }
  public void setGScore(double gScore) { this.gScore = gScore; }

  public Node getParentNode() { return parentNode; }
  public void setParentNode(Node parentNode) { this.parentNode = parentNode; }

  public int getX() { return x; }
  public void setX(int x) { this.x = x; }

  public int getY() { return y; }
  public void setY(int y) { this.y = y; }

  public List<Edge> getAdjacenciesList() { return adjacenciesList; }

  @Override
  public String toString() { return this.value; }

  @Override
  public int compareTo(Node otherNode) {
    return Double.compare(this.fScore, otherNode.getFScore());
  }
}
