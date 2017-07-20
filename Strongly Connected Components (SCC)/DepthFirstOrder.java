import java.util.Stack;

// Do 1st DFS, load to the Stack
public class DepthFirstOrder {

  private Stack<Vertex> stack;

  public DepthFirstOrder(Graph graph) {
    stack = new Stack<>();

    for(Vertex vertex : graph.getVertexList())
      if(!vertex.isVisited())
        dfs(vertex);
  }

  private void dfs(Vertex vertex) {
    vertex.setVisited(true);

    for(Vertex v : vertex.getAdjaceciesList())
      if (!v.isVisited())
        dfs(v);
    stack.push(vertex);
  }

  public Stack<Vertex> getReversePost() { return this.stack; }
}
