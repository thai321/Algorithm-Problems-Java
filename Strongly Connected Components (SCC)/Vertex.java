import java.util.ArrayList;
import java.util.List;

public class Vertex {

  private int id; // each vertex has an ID, ex: vertex #1,2,3
  private String name;
  private List<Vertex> adjaceciesList;
  private boolean visited;
  private int componentId; // this is SCC id --> ex: vertex 1 has SCC #3

  public Vertex(int id, String name) {
    this.id = id;
    this.name = name;
    this.adjaceciesList = new ArrayList<>();
  }

  public void addNeighour(Vertex vertex) {
    this.adjaceciesList.add(vertex);
  }

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public int getComponentId() { return componentId; }
  public void setComponentId(int componentId) { this.componentId = componentId; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public List<Vertex> getAdjaceciesList() { return adjaceciesList; }
  public void setAdjaceciesList(List<Vertex> adjaceciesList) { this.adjaceciesList = adjaceciesList; }

  public boolean isVisited() { return visited; }
  public void setVisited(boolean visited) { this.visited = visited; }

  @Override
  public String toString(){ return this.name; }

}
