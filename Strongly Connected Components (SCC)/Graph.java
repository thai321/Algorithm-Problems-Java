import java.util.ArrayList;
import java.util.List;

public class Graph {

  private List<Vertex> vertices;
  private List<Edge> edges;

  public Graph() { }

  public Graph(List<Vertex> vertices, List<Edge> edges) {
    this.vertices = vertices;
    this.edges = edges;
  }

  public List<Vertex> getVertexList() { return vertices; }
  public void setVertexList(List<Vertex> vertices) { this.vertices = vertices; }

  public List<Edge> getEdgeList() { return edges; }
  public void setEdgeList(List<Edge> edges) { this.edges = edges; }

  public Graph getTransposeGraph() {
    Graph tranposed = new Graph();

    List<Vertex> tranposedVertexList = new ArrayList<>();

    for(Vertex vertex : this.vertices)
      tranposedVertexList.add(vertex);

    for(Edge edge : this.edges) {
      Vertex start = edge.getStartVertex();
      Vertex target = edge.getTargetVertex();
      Vertex startTranposed = tranposedVertexList.get(tranposedVertexList.indexOf(target));

      startTranposed.addNeighour(start);
    }

    tranposed.setVertexList(tranposedVertexList);
    return tranposed; // Tranposed Graph
  }
}
