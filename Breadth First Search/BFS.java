import java.util.LinkedList;
import java.util.Queue;

public class BFS {

  public void bfs(Vertex root) {
    Queue<Vertex> queue = new LinkedList<>();
    root.setVisited(true);
    queue.add(root);

    while( !queue.isEmpty() ) {


      Vertex actualVertex = queue.remove();
      System.out.println(actualVertex + " ");

      for(Vertex v : actualVertex.getNeighbourList())
        if (!v.isVisited()) {
          v.setVisited(true);
          queue.add(v);
        }
    }
  }

  public static void main(String[] args) {
    BFS f = new BFS();
    Vertex vertex1 = new Vertex(1);
    Vertex vertex2 = new Vertex(2);
    Vertex vertex3 = new Vertex(3);
    Vertex vertex4 = new Vertex(4);
    Vertex vertex5 = new Vertex(5);

    vertex1.addNeighbourList(vertex2);
    vertex1.addNeighbourList(vertex4);
    vertex4.addNeighbourList(vertex5);
    vertex2.addNeighbourList(vertex3);

    f.bfs(vertex1);
  }
}
