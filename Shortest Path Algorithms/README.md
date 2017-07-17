# Shortest Path Algorithms
- Shorest path problem: finding a path between two vertices in a graph such that the sum of the weights of its edges is minimized.
- Dijkstra algorithm
- Bellman-Ford algorithm
- A* search
- Floyd-Warshall alogrithm

## Dijkstra Algorithm
- It was constructed by computer scientist <b>Edsger Dijstra in 1956</b>
- <b>Dijstra can handle positive edge weights !!! // Bellman-Ford algorithm can have negative weights as well</b>
- Several variants: it can find the shortest path from A to B, but it is able to construct a shortest path tree as well -> defines the shortest paths from a source to all the other nodes.
- This is asymptotically the fastest known single-source shortest-path algorithm for arbitrary directed graphs with unbounded non-negative weights

### <u>Dijstra algorithm</u>
- Dijkstra's algorithm time complexity: <b>O(V*logV + E)</b>
- Dijkstra's algorithm is a greedy one: it tries to find the global optimum with the help of local minimum -> it turns out to be good !!!
- It is greedy -> on every iteration we want to find the minimum distance to the next vertex possible -> appropriate data structures: <b>heaps (binary or Fibonacci)</b> or in general a <b>priority queue</b>

### Psedudocode
```ruby
class Node
  name
  min_distance
  Node predicessor

def DijkstraAlgorithm(Graph, source)
  distance[source] = 0
  create certex queue Q
      # Initialization phase: distance form source is 0, because that is the starting point. All the other nodes distances are infinity because we do not know the distances in advance
  for v in Graph
    distance[v] = inf
    predecessor[v] = undefined # precious node in the shortest path
    add v to Q
  while Q not empty
    u = vertex in Q with min distance # this is why to use heaps !!!
    remove v from Q

    for each neighbor v of u
      tempDist = distance[u] + distBetween(u,v)
      if tempDist < distance[v]
        distance[v] = tempDist
        predecessor[v] = u

  return distance[] # contains the shortest distances from source to other nodes
```

![Dijsktra1a](docs/Dijsktra1a.png)
![Dijsktra1b](docs/Dijsktra1b.png)
![Dijsktra1c](docs/Dijsktra1c.png)
![Dijsktra1d](docs/Dijsktra1d.png)
![Dijsktra1e](docs/Dijsktra1e.png)
![Dijsktra1f](docs/Dijsktra1f.png)
![Dijsktra1g](docs/Dijsktra1g.png)
![Dijsktra1h](docs/Dijsktra1h.png)
![Dijsktra1i](docs/Dijsktra1i.png)
![Dijsktra1j](docs/Dijsktra1j.png)
![Dijsktra1k](docs/Dijsktra1k.png)
![Dijsktra1l](docs/Dijsktra1l.png)
![Dijsktra1m](docs/Dijsktra1m.png)
![Dijsktra1n](docs/Dijsktra1n.png)
![Dijsktra1o](docs/Dijsktra1o.png)
![Dijsktra1p](docs/Dijsktra1p.png)
![Dijsktra1q](docs/Dijsktra1q.png)
![Dijsktra1r](docs/Dijsktra1r.png)
![Dijsktra1s](docs/Dijsktra1s.png)
![Dijsktra1t](docs/Dijsktra1t.png)
![Dijsktra1u](docs/Dijsktra1u.png)
![Dijsktra1v](docs/Dijsktra1v.png)
![Dijsktra1w](docs/Dijsktra1w.png)
![Dijsktra1x](docs/Dijsktra1x.png)
![Dijsktra1y](docs/Dijsktra1y.png)
![Dijsktra1z](docs/Dijsktra1z.png)
![Dijsktra1za](docs/Dijsktra1za.png)
![Dijsktra1zb](docs/Dijsktra1zb.png)
![Dijsktra1zc](docs/Dijsktra1zc.png)
![Dijsktra1zd](docs/Dijsktra1zd.png)
![Dijsktra1zf](docs/Dijsktra1zf.png)
![Dijsktra1zg](docs/Dijsktra1zg.png)



## Dijsktra Algorithm in un-directed graph (adjacency matrix)
![Dijsktra2a](docs/Dijsktra2a.png)

| v  | A | B | C | D | E | F |
|---|---|---|---|---|---|---|
|   |   |   |   |   |   |   |

------
![Dijsktra2b](docs/Dijsktra2b.png)
| v | A | B   | C   | D   | E   | F   |
|---|---|-----|-----|-----|-----|-----|
| A | <font color="red">0 | inf | inf | inf | inf | inf |

------
| v | A | B   | C   | D   | E   | F   |
|---|---|-----|-----|-----|-----|-----|
| A | <font color="red">0 | inf | inf | inf | inf | inf |
| A |   | 7   | 5   | <font color="red">2   | inf | inf |

![Dijsktra2c](docs/Dijsktra2c.png)

------
| v | A | B   | C   | D   | E   | F   |
|---|---|-----|-----|-----|-----|-----|
| A | <font color="red">0 | inf | inf | inf | inf | inf |
| A |   | 7   | 5   | <font color="red">2   | inf | inf |
| D |   | 7   | 5   |   | inf | <font color="red">4 |

![Dijsktra2c](docs/Dijsktra2d.png)


------
| v | A | B   | C   | D   | E   | F   |
|---|---|-----|-----|-----|-----|-----|
| A | <font color="red">0 | inf | inf | inf | inf | inf |
| A |   | 7   | 5   | <font color="red">2   | inf | inf |
| D |   | 7   | 5   |   | inf | <font color="red">4 |
| F |   | 7   | <font color="red">5   |   | 10  | |

![Dijsktra2c](docs/Dijsktra2f.png)


------
| v | A | B   | C   | D   | E   | F   |
|---|---|-----|-----|-----|-----|-----|
| A | <font color="red">0 | inf | inf | inf | inf | inf |
| A |   | 7   | 5   | <font color="red">2   | inf | inf |
| D |   | 7   | 5   |   | inf | <font color="red">4 |
| F |   | 7   | <font color="red">5   |   | 10  | |
| C |   | <font color="red">7   |    |   | 9 | |

![Dijsktra2c](docs/Dijsktra2f2.png)


------
| v | A | B   | C   | D   | E   | F   |
|---|---|-----|-----|-----|-----|-----|
| A | <font color="red">0 | inf | inf | inf | inf | inf |
| A |   | 7   | 5   | <font color="red">2   | inf | inf |
| D |   | 7   | 5   |   | inf | <font color="red">4 |
| F |   | 7   | <font color="red">5   |   | 10  | |
| C |   | <font color="red">7   |    |   | 9 | |
| B |   |   |    |   | <font color="red">9 | |

![Dijsktra2c](docs/Dijsktra2g.png)


------
![Dijsktra2c](docs/Dijsktra2h.png)

| v | A | B   | C   | D   | E   | F   |
|---|---|-----|-----|-----|-----|-----|
| A | <font color="red">0 | inf | inf | inf | inf | inf |
| A |   | 7   | 5   | <font color="red">2   | inf | inf |
| D |   | 7   | 5   |   | inf | <font color="red">4 |
| F |   | 7   | <font color="red">5   |   | 10  | |
| C |   | <font color="red">7   |    |   | 9 | |
| B |   |   |    |   | <font color="red">9 | |

- Conclusion: <b>Red values</b> represent what are the shortest path vlaues from <b>A</b> to the <b>given node</b>
- If we want the path itself: we have to <b>"Back Track"</b>, have to store predecessors



## Bellman-Ford algorithm
- Invented in 1958 by Bellman and Ford independently
- slower than Dijstra's but more robust: it can handle negative edge
- Dijkstra algorithm choose the edge greedely, with the lowest cost:
  - Bellman-Ford relaxes all edges at the same time for <b>V-1</b> iteration
- Running time is <b>O(V*E)</b>
- Does <b>(V-1)</b> iterations <b>(+1)</b> to detect cycles: if cost decreases in the <b>V-th</b> iteration, than there is negative cycle, because all the paths are traversen up to the <b>(V-1)</b> iteration !!!

### What is the problem?
- <u>Negative cycle:</u>

![BellManFord1a](docs/BellManFord1a.png)

- What is the problem?
  - If we would like to find a path with the minimum cost we have
  - to go A -> B -> C -> A <b>to decrease the overall cost</b>
  - <b>And a next cycle: decrease the cost again</b>
  - <b>And again ...</b>
- Real life scenarios: no negative cycles at all ...
- but sometimes we transform a problem into a graph with positive/ negative edges weight
- And looking for some negative cycles !!!


### <u>Bellman-Ford:</u> pseudocode
```ruby
def BellmanFordAlgorithm(vertices, edges, source)
  distance[source] = 0

  for v in Graph
    distance[v] = inf
    predicessor[v] = undefined # precious node in the shortest path

=begin
For all edges, if the distance to the destination can be shortened
by taking the edge, the distance is updated to the new lower value
V-1 times -> we make relaxation
=end
  for i = 1 ... num_vertices - 1
    for each edge(u,v) with weight w in edges

      tempDist = distance[u] + w

      if tempDist < distance[v]
        distance[u] = tempDist
        predecessor[v] = u

=begin
Since the longest possible path without a cycle can have (V-1) edges,
the edges must be scanned (V-1) times to ensure the shortest path
has been found for all nodes
*** A Final scan of all the edges is performed and if any distance
is updated -> means there is a negative cycle !!!
=end

  for each edge(u,v) with weight w in edges
    if distance[u] + w < distance[v]
      error: " Negative cycle detected"
```

#### <b>1970: <u>Yen optimization</u></b>
- Yen algorithm: It is the Bellman-Ford algorithm with some optimization.
- We can terminate the algorithm if there is no change in the distances between two iterations !!!
- (We use the same technique in bubble sort)

- <b><u>Application:</u></b>
  - cycle detection can prove to be very important
  - Negative cycles as well -> we have to run the Bellman-Ford algorithm that can handle negative edge weights by default
  - On the FOREX market it can detect arbitrage situations !!!