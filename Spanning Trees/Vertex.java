public class Vertex {

  private String name;
  private Node node;

  public Vertex(String name) {
    this.name = name;
  }

  public void setNode(Node node) { this.node = node; }
  public Node getNode() { return this.node; }


  @Override
  public String toString() { return this.name; }
}
