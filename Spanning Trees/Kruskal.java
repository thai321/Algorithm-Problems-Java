import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Kruskal {

  public void spanningTree(List<Vertex> vertices, List<Edge> edges) {
    DisjointSet disjointSet = new DisjointSet(vertices);

    List<Edge> mst = new ArrayList<Edge>();

    Collections.sort(edges); // sort the edges by its weight

    for(Edge edge : edges) {
      Vertex u = edge.getStartVertex();
      Vertex v = edge.getTargetVertex();

      // they're in different set, --> No Cycle, and can be added to the MST list
      if(disjointSet.find(u.getNode()) != disjointSet.find(v.getNode())) {
        mst.add(edge);
        disjointSet.union(u.getNode(), v.getNode()); // join the 2 node togethere
      }
    }

    double cost = 0;
    for(Edge edge : mst) {
      Vertex u = edge.getStartVertex();
      Vertex v = edge.getTargetVertex();
      System.out.print(u + " " + v + " -- ");
      cost += edge.getWeight();
    }
    System.out.println("Cost = " + cost);
  }

  public static void main(String[] args) {
    List<Vertex> vertices = new ArrayList<>();
		Vertex v1 = new Vertex("A"); vertices.add(v1);
		Vertex v2 = new Vertex("B"); vertices.add(v2);
		Vertex v3 = new Vertex("C"); vertices.add(v3);
		Vertex v4 = new Vertex("D"); vertices.add(v4);
		Vertex v5 = new Vertex("E"); vertices.add(v5);
		Vertex v6 = new Vertex("F"); vertices.add(v6);
		Vertex v7 = new Vertex("G"); vertices.add(v7);

		List<Edge> edges = new ArrayList<>();
    Edge e1 = new Edge(v1,v2,2); edges.add(e1); // A -- B : 2
    Edge e2 = new Edge(v1,v3,6); edges.add(e2); // A -- C : 6
    Edge e3 = new Edge(v1,v5,5); edges.add(e3); // A -- E : 5
    Edge e4 = new Edge(v1,v6,10); edges.add(e4);// A -- F : 10
    Edge e5 = new Edge(v2,v5,3); edges.add(e5); // B -- E : 3
    Edge e6 = new Edge(v2,v4,3); edges.add(e6); // B -- D : 3
    Edge e7 = new Edge(v3,v4,1); edges.add(e7); // C -- D : 1
    // Edge e8 = new Edge(v3,v5,2); edges.add(e8);
    Edge e8 = new Edge(v5,v4,4); edges.add(e8); // E -- D : 4
    Edge e9 = new Edge(v6,v7,5); edges.add(e9); // F -- G: 5
    Edge e10 = new Edge(v6,v3,2); edges.add(e10); // F -- C: 2
    Edge e11 = new Edge(v7,v4,5); edges.add(e11); // G -- D: 5

		Kruskal kruskal = new Kruskal();
		kruskal.spanningTree(vertices, edges);
  }
}
