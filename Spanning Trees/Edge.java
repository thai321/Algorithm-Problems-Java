public class Edge implements Comparable<Edge>{

  private double weight;
  private Vertex startVertex;
  private Vertex targetVertex;

  public Edge(Vertex startVertex, Vertex targetVertex, double weight) {
    this.weight = weight;
    this.startVertex = startVertex;
    this.targetVertex = targetVertex;
  }

  public double getWeight() { return this.weight; }
  public void setWeight(double weight) { this.weight = weight; }

  public Vertex getStartVertex() { return this.startVertex; }
  public void setStartVertex(Vertex startVertex) { this.startVertex = startVertex; }

  public Vertex getTargetVertex() { return this.targetVertex; }
  public void setTargetVertex(Vertex weight) { this.targetVertex = targetVertex; }


  @Override
  public int compareTo(Edge otherEdge) {
    return Double.compare(this.weight, otherEdge.getWeight());
  }
}
