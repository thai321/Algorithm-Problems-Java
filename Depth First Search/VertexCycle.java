import java.util.ArrayList;
import java.util.List;

public class VertexCycle {
  private String name;
  private boolean visited;
  private boolean beingVisited;
  private List<VertexCycle> adjacenciesList;

  public VertexCycle(String name) {
    this.name = name;
    this.adjacenciesList = new ArrayList<>();
  }

  public void setBeingVisited(boolean value) { this.beingVisited = value; }

  public boolean isBeingVisited() { return this.beingVisited;}

  public void addNeighbour(VertexCycle vertex) { this.adjacenciesList.add(vertex); }

  public String getName() { return name; }

  public void setName(String name) { this.name = name; }

  public boolean isVisited() { return visited; }

  public void setVisited(boolean visited) { this.visited = visited; }

  public List<VertexCycle> getNeighbourList() { return this.adjacenciesList; }

  public void setAdjacenciesList(List<VertexCycle> adjacenciesList) { this.adjacenciesList = adjacenciesList; }


  @Override
  public String toString() { return this.name; }
}
