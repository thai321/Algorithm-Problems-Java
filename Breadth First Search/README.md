# Breadth First Search (BFS)
- We have a graph and we want to visit every node -> we can do it with BFS
- We visit every vertex exactly once
- We visit the neighbours then the neighbours of these new vertices and so on
- Running time complexity: <b>O(V+E)</b>
- Memory complexity is not good: we have to store lots of references
- That's why DFS is ususally preferred
- BUT it constructs a shortest path: Dijkstra algorithm does a BFS if all the edge weights are equal to 1

### Iteration
- We have an empty queue at the beginning and we keep checking whether we have visited the given node or not
  - Keep iterating until queue is not empty
```ruby
def bfs(vertex)
  Queue queue
  vertex set visited true
  queue.enqueue(vertex)

  while queue not empty
    actual = quque.dequeue()

    for v in actual neighbors
      if v is not visited
        v set visited true
        queue.enqueue(v)
```

![BFS1](images/bfs1.dot.png)

> Queue:{A}

> Queue: {A}; eplore A --> Queue: {G F B}

> Queue: {G F B}; explore B --> Queue: {D C G F}

> Queue: {D C G F}; explore F --> Queue: {D C G} ; F is leaf

> Queue: {D C G}; explore G --> Queue: {H D C}

> Queue: {H D C}; explore C --> Queue: {H D}; C is leaf

> Queue: {H D}; explore D --> Queue: {E H};

> Queue: {E H}; explore H --> Queue: {E}; H is leaf

> Queue: {E}; explore E --> Queue: {}; H is leaf

> Queue: {} is empty -> <b>FINFISHED !!!</b>

> <b>Visited order: A B F G C D H E</b>

### <u>Application</u>
- In Artificial intelligence / machine learning it can prove to be very important: robots can sicover the surrounding mor easily with BFS than DFS
- It is also very important in maximum flow: Edmonds-Karp algorithm uses BFS for finding augmenting paths
- Cheyen's algorithm in garbage collection -> it help to maintain active references on the heap memory
- It uses BFS to detect all the references on the heap
- Serialization/ deserialization of a tree like structure (for example when order does matter) -> it allows the tree to be reconstructeed in an efficient manner !!!

![BFS2](images/bfs2.dot.png)
Visited Order: 1 2 4 3 5

```java
import java.util.ArrayList;
import java.util.List;

public class Vertex {
  private int data;
  private boolean visited;
  private List<Vertex> neighbourList;

  public Vertex(int data) {
    this.data = data;
    this.neighbourList = new ArrayList<>();
  }

  public int getData() { return data; }

  public void setData(int data) { this.data = data; }

  public boolean isVisited() { return visited; }

  public void setVisited(boolean visited) { this.visited = visited; }

  public List<Vertex> getNeighbourList() { return neighbourList; }

  public void setNeighbourList(List<Vertex> neighbourList) { this.neighbourList = neighbourList; }

  public void addNeighbourList(Vertex vertex) { this.neighbourList.add(vertex); }

  @Override
  public String toString() { return "" + this.data; }
}
```

```java
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

    f.bfs(vertex1); // visited order: 1 2 4 3 5
  }
}
```

## BFS - WebCrawler (core of search engines)
- Basically the whole internet can be represented by a directd graph / network
  - With vertices -> these are the domains /URLs/ websites
  - Edges -> these are the connections
- Wieth BFS, we are able to traverse the web -> this is called a web-crawler that can hop from URL to URL and can observe the features of the network
  - For example: The topology ~ degree distribution and so on !!!

```java
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.util.regex.*;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WebCrawler {
  private Queue<String> queue;
  private List<String> discoveredWebsitesList;

  public WebCrawler() {
    this.queue = new LinkedList<>();
    this.discoveredWebsitesList = new ArrayList<>();
  }

  public void discoveredWeb(String root) {
    this.queue.add(root);
    this.discoveredWebsitesList.add(root);

    while(!queue.isEmpty()) {
      String v = this.queue.remove();
      String rawHtml = readURL(v);

      String regexp = "http://(\\w+\\.)*(\\w+)";
      Pattern pattern = Pattern.compile(regexp);
      Matcher matcher = pattern.matcher(rawHtml);

      while(matcher.find()) {
        String actualUrl = matcher.group();

        if(!discoveredWebsitesList.contains(actualUrl)) {
          discoveredWebsitesList.add(actualUrl);
          System.out.println("Website has been found with URL: " + actualUrl);
          queue.add(actualUrl);
        }
      }
    }
  }

  private String readURL(String v) {
    String rawHtml = "";

    try {
      URL url = new URL(v);
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
      String inputLine = "";

      while((inputLine = in.readLine()) != null) {
        rawHtml += inputLine;
      }
      in.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
    return rawHtml;
  }

  public static void main(String[] args) {
    WebCrawler crawler = new WebCrawler();
    String rootUrl = "http://www.google.com";

    crawler.discoveredWeb(rootUrl);
  }
}
```


```java
/*
Result:
Website has been found with URL: http://schema.org
Website has been found with URL: http://maps.google.com
Website has been found with URL: http://www.youtube.com
Website has been found with URL: http://news.google.com
Website has been found with URL: http://www.w3.org
Website has been found with URL: http://lists.w3.org
Website has been found with URL: http://github.com
Website has been found with URL: http://blog.schema.org
Website has been found with URL: http://www.w3devcampus.com
Website has been found with URL: http://testthewebforward.org
Website has been found with URL: http://www.money2020.com
Website has been found with URL: http://coraliemercier.wordpress.com
Website has been found with URL: http://validator.w3.org
Website has been found with URL: http://jigsaw.w3.org
Website has been found with URL: http://vimeo.com
Website has been found with URL: http://www.sics.se
Website has been found with URL: http://twitter.com
Website has been found with URL: http://www.csail.mit.edu
Website has been found with URL: http://www.ercim.eu
Website has been found with URL: http://www.keio.ac.jp
Website has been found with URL: http://ev.buaa.edu.cn
Website has been found with URL: http://dev.w3.org
Website has been found with URL: http://tools.ietf.org
Website has been found with URL: http://www.ietf.org
Website has been found with URL: http://www.multilingualweb.eu
Website has been found with URL: http://tap.stanford.edu
Website has been found with URL: http://rdfa.info
Website has been found with URL: http://esw.w3.org
Website has been found with URL: http://www.webplatform.org
Website has been found with URL: http://www.ics.uci.edu
Website has been found with URL: http://lcweb.loc.gov
Website has been found with URL: http://pso.icann.org
Website has been found with URL: http://iswc07.org
Website has been found with URL: http://www.blogblog.com
Website has been found with URL: http://iot.schema.org
Website has been found with URL: http://idpf.org
Website has been found with URL: http://www.idpf.org
Website has been found with URL: http://reporterslab.org
Website has been found with URL: http://bioschemas.org
Website has been found with URL: http://lrmi.dublincore.net
Website has been found with URL: http://pending.schema.org
Website has been found with URL: http://health
Website has been found with URL: http://www.edmcouncil.org
Website has been found with URL: http://www.gs1.org
Website has been found with URL: http://gs1.org
Website has been found with URL: http://bib.schema.org
Website has been found with URL: http://auto.schema.org
Website has been found with URL: http://3.bp.blogspot.com
Website has been found with URL: http://bl.ocks.org
Website has been found with URL: http://d3js.org
Website has been found with URL: http://w3c.github.io
Website has been found with URL: http://www.lazaruscorporation.co.uk
Website has been found with URL: http://v1.91
Website has been found with URL: http://www.opendomain.org
Website has been found with URL: http://www.dailymail.co.u
*/
```
