import java.util.Stack;

public class IDDFS {

  private Node targetNode;
  private boolean isTargetFound;

  public IDDFS(Node targetNode) {
    this.targetNode = targetNode;
  }

  public void runDeepeningSearch(Node startNode) {

    int depth = 0;

    while(!isTargetFound) {
      System.out.println();
      dfs(startNode,depth);
      depth++;
      System.out.println();
    }
  }

  public void dfs(Node startNode, int depth) {
    Stack<Node> stack = new Stack<>();
    startNode.setDepthLevel(0);
    stack.push(startNode);

    while(!stack.isEmpty()) {
      Node actualNode = stack.pop();
      System.out.print(actualNode + " ");

      if(actualNode.getName().equals(targetNode.getName())) {
        System.out.println("\nNode has been found .... ");
        this.isTargetFound = true;
        return;
      }

      // contine and finish the rest of the stack
      // to explore other upper nodes
      if(actualNode.getDepthLevel() >= depth) {
        continue;
      }

      for(Node node : actualNode.getAdjacenciesList()) {
        node.setDepthLevel(actualNode.getDepthLevel() + 1);
        stack.push(node);
      }
    }
  }

  public static void main(String[] args) {
    Node v1 = new Node("A");
    Node v2 = new Node("B");
    Node v3 = new Node("C");
    Node v4 = new Node("D");
    Node v5 = new Node("E");

    v1.addNeighbour(v2);
    v1.addNeighbour(v3);
    v3.addNeighbour(v4);
    v4.addNeighbour(v5);

    IDDFS iddfs =new IDDFS(v5); // target = E
    iddfs.runDeepeningSearch(v1); // start from node A, root
  }
}
/*
A

A C B

A C D B

A C D E
Node has been found ....
*/
