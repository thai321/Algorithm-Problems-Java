import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

// Only 1 DFS --> O(V + E)
public class TarjanAlgorithm {

  private Stack<Vertex> stack;
  private List<Vertex> vertices;
  private List<List<Vertex>> connectedComponentList;
  private int time = 0; // keep the order of node we've visited
  private int count = 0; // count how many SCC we have

  public TarjanAlgorithm(List<Vertex>  vertices) {
    this.stack = new Stack<>();
    this.vertices = vertices;
    this.connectedComponentList = new ArrayList<>();
  }

  public void runAlgorithm() {
    for(Vertex vertex : this.vertices)
      if(!vertex.isVisited())
        dfs(vertex);
  }

  private void dfs(Vertex vertex) {
    vertex.setLowLink(time++);
    System.out.println("Visiting vertex " + vertex + " with lowLink " + vertex.getLowLink());
    vertex.setVisited(true);
    this.stack.push(vertex);
    boolean isComponentRoot = true;

    for(Vertex v : vertex.getNeighbourList()) {

      if(!v.isVisited()) {
        System.out.println("Recursively visit vertex " + v);
        dfs(v);
      }

      // we have a back edge
      if(vertex.getLowLink() > v.getLowLink()) {
        System.out.println("\nBecause vertex "+vertex+" lowLink (value:"+vertex.getLowLink()+") > vertex " +v +" lowLink (value:"+v.getLowLink()+") --> we set vertex "+vertex+" lowLink = vertex "+v+" lowLink value which is "+v.getLowLink());

        vertex.setLowLink(v.getLowLink());
				System.out.println("So Vertex "+ vertex +" is not the root of a SCC\n");
				isComponentRoot = false;
      }
    }

    // only for the root SCC node
    if(isComponentRoot) {
      count++;

      System.out.println("Vertex " + vertex + " is the root of an SCC");
      List<Vertex> component = new ArrayList<>();

      while(true) {
        Vertex actualVertex = stack.pop();
        System.out.println("So vertex " + actualVertex + " is in SCC " + count);
        component.add(actualVertex);
        actualVertex.setLowLink(Integer.MAX_VALUE);

        // Run util it hits the root SCC node
        if(actualVertex.getName().equals(vertex.getName()))
          break;
      }

      System.out.println("\n---------------------\n");
      connectedComponentList.add(component);
    }
  }
  public void printComponents(){
    System.out.println(connectedComponentList);
  }


  public static void main(String[] args) {
    List<Vertex> vertices = new ArrayList<>();

    Vertex v1 = new Vertex("1"); vertices.add(v1);
    Vertex v2 = new Vertex("2"); vertices.add(v2);
    Vertex v3 = new Vertex("3"); vertices.add(v3);
    Vertex v4 = new Vertex("4"); vertices.add(v4);
    Vertex v5 = new Vertex("5"); vertices.add(v5);
    Vertex v6 = new Vertex("6"); vertices.add(v6);
    Vertex v7 = new Vertex("7"); vertices.add(v7);

    v1.addNeighour(v5);
    v2.addNeighour(v1);
    v3.addNeighour(v2);
    v3.addNeighour(v4);
    v4.addNeighour(v3);
    v5.addNeighour(v2);
    v6.addNeighour(v2);
    v6.addNeighour(v5);
    v6.addNeighour(v7);
    v7.addNeighour(v6);
    v7.addNeighour(v3);

    TarjanAlgorithm tarjanAlgorithm = new TarjanAlgorithm(vertices);
    tarjanAlgorithm.runAlgorithm();
    tarjanAlgorithm.printComponents();
  }
}

/*
Visiting vertex 1 with lowLink 0
Recursively visit vertex 5
Visiting vertex 5 with lowLink 1
Recursively visit vertex 2
Visiting vertex 2 with lowLink 2

Because vertex 2 lowLink (value:2) > vertex 1 lowLink (value:0) --> we set vertex 2 lowLink = vertex 1 lowLink value which is 0
So Vertex 2 is not the root of a SCC


Because vertex 5 lowLink (value:1) > vertex 2 lowLink (value:0) --> we set vertex 5 lowLink = vertex 2 lowLink value which is 0
So Vertex 5 is not the root of a SCC

Vertex 1 is the root of an SCC
So vertex 2 is in SCC 1
So vertex 5 is in SCC 1
So vertex 1 is in SCC 1

---------------------

Visiting vertex 3 with lowLink 3
Recursively visit vertex 4
Visiting vertex 4 with lowLink 4

Because vertex 4 lowLink (value:4) > vertex 3 lowLink (value:3) --> we set vertex 4 lowLink = vertex 3 lowLink value which is 3
So Vertex 4 is not the root of a SCC

Vertex 3 is the root of an SCC
So vertex 4 is in SCC 2
So vertex 3 is in SCC 2

---------------------

Visiting vertex 6 with lowLink 5
Recursively visit vertex 7
Visiting vertex 7 with lowLink 6

Because vertex 7 lowLink (value:6) > vertex 6 lowLink (value:5) --> we set vertex 7 lowLink = vertex 6 lowLink value which is 5
So Vertex 7 is not the root of a SCC

Vertex 6 is the root of an SCC
So vertex 7 is in SCC 3
So vertex 6 is in SCC 3

---------------------

[[2, 5, 1], [4, 3], [7, 6]]
*/
