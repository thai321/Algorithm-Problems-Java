import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;

// kinda like Dijkstra
public class AStarSearch {

  public void AStarSearch(Node sourceNode, Node goalNode) {

    Set<Node> exploredNodes = new HashSet<>();

    PriorityQueue<Node> unExploredNodesQueue = new PriorityQueue<>();
    sourceNode.setGScore(0); // initilize G-Score to be 0
    unExploredNodesQueue.add(sourceNode);
    boolean isFound = false;

    while(!unExploredNodesQueue.isEmpty() && !isFound) {

      Node currentNode = unExploredNodesQueue.poll();
      exploredNodes.add(currentNode);

      if(currentNode.getValue().equals(goalNode.getValue()))
        isFound = true;

      for(Edge e : currentNode.getAdjacenciesList()) {
        Node childNode = e.getTargetNode();
        double cost = e.getCost();
        double tempGScore = currentNode.getFScore() + cost;
        double tempFScore = tempGScore + heuristic(childNode, goalNode);

        // not better --> continue
        if(exploredNodes.contains(childNode) && (tempFScore >= childNode.getFScore())) {
          continue;
        } // better solution
        else if(!unExploredNodesQueue.contains(childNode) || (tempFScore < childNode.getFScore())) {
          childNode.setParentNode(currentNode);
          childNode.setGScore(tempGScore);
          childNode.setFScore(tempFScore);

          if(unExploredNodesQueue.contains(childNode))
            unExploredNodesQueue.remove(childNode);

          unExploredNodesQueue.add(childNode);
        }
      }
    }
  }
  // Manhattan distance
  private double heuristic(Node node1, Node node2) {
    return Math.abs(node1.getX() - node2.getX()) + Math.abs(node2.getY() - node1.getY());
  }

  public List<Node> getPath(Node targetNode) {
    List<Node> pathList = new ArrayList<>();

    for(Node node = targetNode; node != null; node = node.getParentNode())
      pathList.add(node);

    Collections.reverse(pathList);
    return pathList;
  }

  public static void main(String[] args) {
    Node node1 = new Node("A");
    Node node2 = new Node("B");
    Node node3 = new Node("C");
    Node node4 = new Node("D");

    node1.addNeighbour(new Edge(node2, 10));
    node1.addNeighbour(new Edge(node4, 100));
    node2.addNeighbour(new Edge(node3, 10));
    node3.addNeighbour(new Edge(node4, 10));

    AStarSearch aStar = new AStarSearch();
    aStar.AStarSearch(node1, node4);

    List<Node> path = aStar.getPath(node4);
    System.out.println(path);
  }
}
