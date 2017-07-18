import java.util.Collections;
import java.util.Stack;
import java.util.List;

public class TopologicalSort {

  private Stack<Vertex> stack;

  public TopologicalSort() {
    this.stack = new Stack<>();
  }

  public void makeTopologicalOrder(List<Vertex> vertexList) {

    for(Vertex vertex : vertexList)
      if(!vertex.isVisited())
        dfs(vertex);

  }

  private void dfs(Vertex vertex) {
    vertex.setVisited(true);

    for(Edge edge : vertex.getAdjaceciesList()) {
      Vertex v = edge.getTargetVertex();
      if(!v.isVisited()) {
        v.setVisited(true);
        dfs(v);
      }
    }

    this.stack.push(vertex);
  }

  public Stack<Vertex> getTopologicalOrder() {
    Collections.reverse(stack);
    return stack;
  }
}
