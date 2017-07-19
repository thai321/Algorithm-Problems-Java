import java.util.ArrayList;
import java.util.List;

public class DisjointSet {

  private int nodeCount = 0;
  private int setCount = 0; // track number of node(s)
  private List<Node> rootNodes; // list of sets

  public DisjointSet(List<Vertex> vertices) {
    this.rootNodes  = new ArrayList<Node>(vertices.size());
    makeSets(vertices);
  }

  public int find(Node n) {
    Node currentNode = n;

    // looking for the root node
    while(currentNode.getParent() != null) {
      currentNode = currentNode.getParent();
    }

    Node rootNode = currentNode;
    // make all the parent of node n point to the root node
    currentNode = n;
    while(currentNode != rootNode) {
      Node temp = currentNode.getParent();
      currentNode.setParent(rootNode);
      currentNode = temp;
    }

    return rootNode.getId(); // return the representative
  }

  public void union(Node node1, Node node2) {
    int index1 = find(node1);
    int index2 = find(node2);

    if(index1 == index2) // no need to merge them since they're in the same set
      return;

    Node root1 = this.rootNodes.get(index1);
    Node root2 = this.rootNodes.get(index2);

    if(root1.getRank() < root2.getRank()) {
      root1.setParent(root2);
    } else if(root1.getRank() > root2.getRank()) {
      root2.setParent(root1);
    } else {
      root2.setParent(root1);
      root1.setRank(root1.getRank() + 1);
    }

    // Since we join 2 sets, we need to decrease the number of set by 1
    this.setCount--;
  }

  private void makeSets(List<Vertex> vertices) {
    for(Vertex v : vertices)
      makeSet(v);
  }

  private void makeSet(Vertex vertex) {
    Node n = new Node(0, rootNodes.size(), null);
    vertex.setNode(n);
    this.rootNodes.add(n);
    this.setCount++;
    this.nodeCount++;
  }
}
