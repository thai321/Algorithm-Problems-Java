public class Vertex {

  private int id;
  private String name;
  private boolean visited;

  public Vertex(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId(){ return id; }
  public void setId(int id) { this.id = id; }

  public String getName(){ return name; }
  public void setName(String name) { this.name = name; }

  public boolean getVisited(){ return visited; }
  public void setVisited(boolean visited) { this.visited = visited; }

  public String toString() {
    return this.id + " " + this.name;
  }
}
