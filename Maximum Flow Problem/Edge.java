public class Edge {

  private Vertex fromVertex;
  private Vertex targetVertex;
  private final double capacity;
  private double flow;

  public Edge(Vertex fromVertex, Vertex targetVertex, double capacity) {
    this.fromVertex = fromVertex;
    this.targetVertex = targetVertex;
    this.capacity = capacity;
    this.flow = 0.0;
  }

  public Edge(Edge edge) {
    this.fromVertex = edge.getFromVertex();
    this.targetVertex = edge.getTargetVertex();
    this.capacity = edge.getCapacity();
    this.flow = edge.getFlow();
  }

  public Vertex getOther(Vertex vertex) {
    return (vertex == fromVertex) ? targetVertex : fromVertex;
  }

  public double getResidualCapacity(Vertex vertex) {
    return (vertex == fromVertex) ? flow : (capacity - flow);
  }        // Backward edge                 // forward edge

  public void addResidualFlowto(Vertex vertex, double deltaFlow) {
    flow = (vertex == fromVertex) ? (flow - deltaFlow) : (flow + deltaFlow);
  }         // backward edge                              // forward edge

  public double getFlow() { return flow; }
  public void setFlow(double flow) { this.flow = flow; }

  public double getCapacity() { return capacity; }

  public Vertex getFromVertex() { return fromVertex; }
  public void setFromVertex(Vertex fromVertex) { this.fromVertex = fromVertex; }

  public Vertex getTargetVertex() { return targetVertex; }
  public void setTargetVertex(Vertex targetVertex) { this.targetVertex = targetVertex; }

  @Override
  public String toString() { return fromVertex + "-" + targetVertex + " " + flow + "/" + capacity; }
}
