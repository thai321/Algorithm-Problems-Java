import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

public class FordFulkerson {

  // marked[v.getId()] = true s --> v in the residual graph
  private boolean[] marked;
  private Edge[] edgeTo; // edgeTo[v] = edges in the augmenting path
  private double valueMaxFlow;

  public FordFulkerson(FlowNetwork flowNetwork, Vertex s, Vertex t) {

    while(hasAugmentingPath(flowNetwork, s, t)) {
      double minValue = Double.POSITIVE_INFINITY;

      for(Vertex v = t; v != s; v = edgeTo[v.getId()].getOther(v)) {
        minValue = Math.min(minValue, edgeTo[v.getId()].getResidualCapacity(v) );
      }

      for(Vertex v = t; v != s; v = edgeTo[v.getId()].getOther(v)) {
        edgeTo[v.getId()].addResidualFlowto(v, minValue);
      }

      valueMaxFlow = valueMaxFlow + minValue;
    }
  }

  public boolean isInCut(int index) { return marked[index]; }

  public double getMaxFlow() { return this.valueMaxFlow; }

  // BFS
  private boolean hasAugmentingPath(FlowNetwork flowNetwork, Vertex s, Vertex t) {
    edgeTo = new Edge[flowNetwork.getNumOfVertices()];
    marked = new boolean[flowNetwork.getNumOfVertices()];

    Queue<Vertex> queue = new LinkedList<>();
    queue.add(s);
    marked[s.getId()] = true;

    while(!queue.isEmpty() && !marked[t.getId()]) {
      Vertex v = queue.remove();

      for(Edge e : flowNetwork.getAdjacenciesList(v)) {
        Vertex w = e.getOther(v);

        if(e.getResidualCapacity(w) > 0) {
          if(!marked[w.getId()]) {
            edgeTo[w.getId()] = e;
            marked[w.getId()] = true;
            queue.add(w);
          }
        }
      }
    }

    // true means there is an augmenting path from s to t
    return marked[t.getId()];
  }

  public static void main(String[] args) {

  	FlowNetwork flowNetwork = new FlowNetwork(4);

  	Vertex vertex0 = new Vertex(0, "s");
  	Vertex vertex1 = new Vertex(1, "A");
  	Vertex vertex2 = new Vertex(2, "B");
  	Vertex vertex3 = new Vertex(3, "t");

  	List<Vertex> vertexList = new ArrayList<>();
  	vertexList.add(vertex0);
  	vertexList.add(vertex1);
  	vertexList.add(vertex2);
  	vertexList.add(vertex3);

  	flowNetwork.addEdge(new Edge(vertex0, vertex1, 4));
  	flowNetwork.addEdge(new Edge(vertex0, vertex2, 5));

  	flowNetwork.addEdge(new Edge(vertex1, vertex3, 7));

  	flowNetwork.addEdge(new Edge(vertex2, vertex1, 4));
  	flowNetwork.addEdge(new Edge(vertex2, vertex3, 1));

  	FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, vertex0, vertex3);

  	System.out.println("Maximum flow is: " + fordFulkerson.getMaxFlow());

  	// print min-cut
  	System.out.println("Vertices in the min cut set: ");
  	for (int v = 0; v < vertexList.size(); v++) {
  		if (fordFulkerson.isInCut(v))
  			System.out.print(vertexList.get(v)+" - ");
  		;
  	}
  }

}
