import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFS {

  private Stack<Vertex> stack;

  public DFS() {
    this.stack = new Stack<>();
  }

  public void dfs(List<Vertex> vertexList) {

    // For loop here means if you have 2 or more un-connected graph
    // We still want to visit every single vertex
    for(Vertex v : vertexList) {
      if(!v.isVisited()) {
        v.setVisited(true);
        dfsWithStack(v);
      }
    }
  }

  // sure that we will visit every vertex in a single connected graph
  private void dfsWithStack(Vertex rootVertex) {
    this.stack.add(rootVertex);
    rootVertex.setVisited(true);

    while(!stack.isEmpty()) {
      Vertex actualVertex = stack.pop();
      System.out.println(actualVertex + " ");

      for(Vertex v : actualVertex.getNeighbourList()) {
        if (!v.isVisited()) {
          v.setVisited(true);
          this.stack.push(v);
        }
      }
    }
  }

  public static void main(String[] args) {

    Vertex v1 = new Vertex("1");
    Vertex v2 = new Vertex("2");
    Vertex v3 = new Vertex("3");
    Vertex v4 = new Vertex("4");
    Vertex v5 = new Vertex("5");

    List<Vertex> list = new ArrayList<>();

    v1.addNeighbourList(v2);
    v1.addNeighbourList(v3);
    v3.addNeighbourList(v4);
    v4.addNeighbourList(v5);

    list.add(v1);
    list.add(v2);
    list.add(v3);
    list.add(v4);
    list.add(v5);

    DFS f = new DFS();
    f.dfs(list); // visited order: 1 3 4 5 2
  }
}
