## Project 2: The Dijkstra’s Algorithm

![Dijkstra’s Algorithm](https://img.shields.io/badge/Dijkstra’s_Algorithm-B1DE67?style=for-the-badge&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)

### Abstract:
This project addresses how the choice of the input graph representation and the priority queue implementation will affect the time complexity of Dijkstra’s Algorithm. In this repository it contains the following files, 

1. [DijkstraA.java](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/blob/main/Project2/DijkstraA.java),
graph G is represented using an Adjacency Matrix with an Array-based Priority Queue.

2. [DijkstraB.java](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/blob/main/Project2/DijkstraB.java),
graph G is represented using an Adjacency List with a Min-Heap Priority Queue

3. [generateInput.java](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/blob/main/Project2/generateInput.java),
generation of input graph G

### 1. Sparse vs. Dense Graphs: Characteristics and Generation

In the generation of input graphs G = (V, E) where (V) ranges from 3 to 1000, we make the following assumptions and considerations:

| Graph Type   | Connected | Complete | Edges                         |
|--------------| ----------| ---------| ----------------------------- | 
| Sparse Graph | Yes        | No       | (V - 1)                      | 
| Dense Graph  | Yes        | Yes      | V(V – 1)/2                   |

<br />

Assumptions:
1. **Sparse Graph:**
   - **Connected:** Yes, ensuring all vertices are reachable.
   - **Complete:** No, allowing for fewer edges than the maximum possible.
   - **Edges:** (V - 1), the minimum number of edges in the graph.

2. **Dense Graph:**
   - **Connected:** Yes, ensuring all vertices are reachable.
   - **Complete:** Yes, with edges reaching the maximum possible.
   - **Edges:** V(V – 1)/2, forming a fully connected graph.

<br />

By defining these characteristics, we aim to explore the behaviour and performance of algorithms on graphs with varying densities.<br />
Once the graph is generated, it is saved in a `.txt` file. The `.txt` file would be read and converted to a adjacency matrix or adjacency list representation in the respective [DijkstraA.java](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/blob/main/Project2/DijkstraA.java) and [DijkstraB.java](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/blob/main/Project2/DijkstraB.java) files. 

To find more details of our graph generation, click [here](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/blob/main/Project2/generateInput.java). 

### 2. Implementation of Dijkstra’s Algorithm.

The pseudocode for Dijkstra’s Algorithm is as follows. 

```bash

// graph: input (sparse/dense) graph, 
// source: Source vertex

Dijkstra(graph, source):
    n = number of vertices in graph
    d = array of distances initialized to INF
    pi = array of predecessors initialized to -1
    S = array of visited vertices initialized to 0

    d[source] = 0
    priorityQueue = createPriorityQueue()

    for i = 0 to n-1:
        enqueue(priorityQueue, (d[i], i))

    while priorityQueue is not empty:
        (minDistance, u) = dequeueMin(priorityQueue)
        S[u] = 1

        for each neighbor v of u:
            if S[v] == 0 and d[u] + weight(u, v) < d[v]:
                decreaseKey(priorityQueue, v, d[u] + weight(u, v))
                d[v] = d[u] + weight(u, v)
                pi[v] = u
   ```

To find more details of how the Priority Queue is implemented in the respective file, click [DijkstraA.java](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/blob/main/Project2/DijkstraA.java) and [DijkstraB.java](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/blob/main/Project2/DijkstraB.java).

### 2. Graph G - Represented using an Adjacency Matrix with an Array-based Priority Queue

Respective Sparse & Dense Graph with DijkstraA Algorithm: 
![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/37674550-e80a-4d31-a9d6-f638c82fcfa9)

Comparing Sparse Vs. Dense Graph with DijkstraA Algorithm: 
![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/3813c1d0-172d-4ffa-bcd1-9e0c72a2eeaa)

### 3. Graph G - Represented as an Adjacency List with a Min-Heap for the Priority Queue

Respective Sparse & Dense Graph with DijkstraB Algorithm: 
![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/af40b2d7-e430-47f3-92b3-21a2dd8f85fe)

Comparing Sparse Vs. Dense Graph with DijkstraB Algorithm: 
![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/58b65107-ac4d-4b40-b640-cc8152bcac42)

### 4. Comparison between Implementations (2) and (3)

Sparse Graph:
![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/395d3090-c38b-4720-8c2d-05967eb1abdd)

Dense Graph: 
![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/c9e56a74-333f-43dd-9442-16923d221c23)

### Conclusion

From our empirical analysis, DijkstraB perform better on a sparse graph while DijkstraA perform better on a dense graph.
