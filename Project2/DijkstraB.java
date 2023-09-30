package Project2;
import java.util.*;


class DijkstraB {
    public static void main (String[] args) {
        System.out.println("Part B: Dijkstra's Algo with adjacency list and min heap for priority queue");
        List<List<Vertex>> adjLst = new ArrayList<List<Vertex>>();
        initialiseAdjList(adjLst);
        dijkstra(adjLst, 0);
    }

    public static void dijkstra(List<List<Vertex>> adjLst, int source) {
        int numOfVertices = adjLst.size();
        int[] S = new int[numOfVertices]; // represents set of vertices whose shortest paths from source node have been determined
        int[] d = new int[numOfVertices];  // to store the estimated lengths of shortest path form the source node to all vertices
        int[] pi = new int[numOfVertices]; // to store the predecessors for each vertex;
        Arrays.fill(d, Integer.MAX_VALUE);
        Arrays.fill(pi, -1);

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        
        d[source] = 0;
        S[source] = 1;
        List<Vertex> neighbourOfSource = adjLst.get(source);
        for (int i = 0; i < neighbourOfSource.size(); i++) {
            Vertex neighbour = neighbourOfSource.get(i);
            pq.add(neighbour);
        }

        while (!pq.isEmpty() || !isAllVerticesVisited(S)) {
            Vertex v = pq.poll();
            int currentVertexIndex = v.index;
            int currentVertexWeight = v.weight;
            int currentVertexParent = v.parent;

            if (S[currentVertexIndex] == 1) {
                continue;
            } else {
                S[currentVertexIndex] = 1;
            }

            List<Vertex> neighbours = adjLst.get(currentVertexIndex);
            for (int i = 0; i < neighbours.size(); i++) { // add all the neighbours in pq
                Vertex neighbour = neighbours.get(i);
                pq.add(neighbour);
            }
            
            int newWeight = d[currentVertexParent] + currentVertexWeight; 
            if (newWeight < d[currentVertexIndex]) {
                d[currentVertexIndex] = newWeight;
                pi[currentVertexIndex] = currentVertexParent;
            }
        }

        Print(d, pi);
    }

    static boolean isAllVerticesVisited(int[] S) {
        int len = S.length;
        for (int i = 0; i < len; i++) {
            if (S[i] == 0) {
                return false;
            }
        }
        return true;
    }

    static void initialiseAdjList(List<List<Vertex>> adjLst) {
        Scanner sc = new Scanner(System.in);
        int V  = sc.nextInt();
        for (int i = 0; i < V; i++) {
            List<Vertex> lst = new ArrayList<>();
            adjLst.add(lst);
        }
        while (sc.hasNext()) {
            int parentVertex = sc.nextInt();
            int nextVertex = sc.nextInt();
            int nextWeight = sc.nextInt();
            Vertex newV = new Vertex(nextVertex, nextWeight, parentVertex);
            adjLst.get(parentVertex).add(newV);
        }
        sc.close();
    }

    static void printAdjList(List<List<Vertex>> adjLst) {
        for (int i = 0; i < adjLst.size(); i++) {
            List<Vertex> lst = adjLst.get(i); 
            int lstLen = lst.size(); 
            for (int j = 0; j < lstLen; j++) {
                Vertex v = lst.get(j);
                String sentence = String.format("Vertex %d is connected to Vertex %d with a weight of %d", i, v.index, v.weight);
                System.out.println(sentence);
            }
        } 
    }

    static void Print(int[] dist, int[] pi) {
    	System.out.println("Vertex \t Distance from Source \t Predecessor");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + "\t\t  " + dist[i] + "\t\t     " + pi[i]);
        }
    }
    
}

class Vertex implements Comparable<Vertex> {
    public int index;
    public int weight;
    public int parent;
 
    public Vertex(int vertex, int weight) {
        this.index = vertex;
        this.weight = weight;
        this.parent = -1;
    }

    public Vertex(int vertex, int weight, int parent) {
        this.index = vertex;
        this.weight = weight;
        this.parent = parent;
    }
 
    @Override public int compareTo(Vertex other) {
        return Integer.compare(this.weight, other.weight);
    }
}

