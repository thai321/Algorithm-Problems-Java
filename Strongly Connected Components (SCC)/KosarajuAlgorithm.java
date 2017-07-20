import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// 2 DFS take +  tranpose the graph, which reverse all edges direction
// = O(2(V + E)) + O(E) = 2V + 3E = O(V + E)
public class KosarajuAlgorithm {

  // id[v] = id of the SCC conntaining that given vertex
  private int[] id;
  private int count; // number of SCCs
  private boolean[] visitedSet;

  public KosarajuAlgorithm(Graph graph) {

    // Do 1st DFS, load to the Stack
    DepthFirstOrder dfs = new DepthFirstOrder(graph.getTransposeGraph());

    Stack<Vertex> stack = dfs.getReversePost();

    visitedSet = new boolean[graph.getVertexList().size()];
    id = new int[graph.getVertexList().size()];

    // pop from stack, this is 1st current vertex of current SCC
    for(Vertex vertex: stack) {
      if(!visitedSet[vertex.getId()]) {
        dfs(vertex); // call DFS on this current vertex
        count++;
      }
    }
  }

  // assign SSC id to the vertices of current SCC
  private void dfs(Vertex vertex) {
    visitedSet[vertex.getId()] = true;
    id[vertex.getId()] = count;
    vertex.setComponentId(count);

    for(Vertex v : vertex.getAdjaceciesList())
      if(!visitedSet[v.getId()])
        dfs(v);
  }

  public int getCount() { return this.count; }


  public static void main(String[] args) {
    List<Vertex> vertexList = new ArrayList<>();

    vertexList.add(new Vertex(0, "a"));
    vertexList.add(new Vertex(1, "b"));
    vertexList.add(new Vertex(2, "c"));
    vertexList.add(new Vertex(3, "d"));
    vertexList.add(new Vertex(4, "e"));
    vertexList.add(new Vertex(5, "f"));
    vertexList.add(new Vertex(6, "g"));
    vertexList.add(new Vertex(7, "h"));

    // All edges's weight is 1
    List<Edge> edgeList = new ArrayList<Edge>();

    edgeList.add(new Edge(1, vertexList.get(0), vertexList.get(1)));

    edgeList.add(new Edge(1, vertexList.get(1), vertexList.get(2)));
    edgeList.add(new Edge(1, vertexList.get(1), vertexList.get(4)));
    edgeList.add(new Edge(1, vertexList.get(1), vertexList.get(5)));

    edgeList.add(new Edge(1, vertexList.get(2), vertexList.get(3)));
    edgeList.add(new Edge(1, vertexList.get(2), vertexList.get(6)));

    edgeList.add(new Edge(1, vertexList.get(3), vertexList.get(2)));
    edgeList.add(new Edge(1, vertexList.get(3), vertexList.get(7)));

    edgeList.add(new Edge(1, vertexList.get(4), vertexList.get(0)));
    edgeList.add(new Edge(1, vertexList.get(4), vertexList.get(5)));

    edgeList.add(new Edge(1, vertexList.get(5), vertexList.get(6)));

    edgeList.add(new Edge(1, vertexList.get(6), vertexList.get(5)));

    edgeList.add(new Edge(1, vertexList.get(7), vertexList.get(3)));
    edgeList.add(new Edge(1, vertexList.get(7), vertexList.get(6)));



    Graph graph = new Graph(vertexList,edgeList);

    KosarajuAlgorithm kosarajuAlgorithm = new KosarajuAlgorithm(graph);

    System.out.println(kosarajuAlgorithm.getCount());

    for(Vertex vertex : vertexList)
    	System.out.println(vertex+" - "+vertex.getComponentId());
  }
}
/*
3
a - 0
b - 0
c - 1
d - 1
e - 0
f - 2
g - 2
h - 1
*/
