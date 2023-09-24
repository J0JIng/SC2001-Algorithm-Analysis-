package Project2;
import java.util.Arrays;
import java.util.PriorityQueue;

class DijkstraA {
    
	private static final int INF = Integer.MAX_VALUE;

    public static void dijkstra(int[][] graph, int source) {
        int n = graph.length;
        int[] d = new int[n];
        int[] pi = new int[n];
        int[] S = new int[n];

        Arrays.fill(d, INF);
        Arrays.fill(pi, -1);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        
        d[source] = 0;
        priorityQueue.offer(new Vertex(source, 0));

        while (!priorityQueue.isEmpty()) {
            Vertex u = priorityQueue.poll();

            if (S[u.index] == 1) {
                continue; // Skip if u is already in S
            }

            S[u.index] = 1; // Add u to S

            for (int v = 0; v < n; v++) {
                if (S[v] == 0 && graph[u.index][v] != 0) {
                    int alternate = d[u.index] + graph[u.index][v];
                    if (alternate < d[v]) {
                        d[v] = alternate;
                        pi[v] = u.index;
                        priorityQueue.offer(new Vertex(v, d[v]));
                    }
                }
            }
        }

        Print(d, pi);
    }

    private static class Vertex implements Comparable<Vertex> {
        int index;
        int dist;

        public Vertex(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    private static void Print(int[] dist, int[] pi) {
        
    	System.out.println("Vertex \t Distance from Source \t Predecessor");
        
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + "\t\t  " + dist[i] + "\t\t     " + pi[i]);
        }
    }
	
	public static void main (String[] args) {
        System.out.println("Part A: Dijkstra's Algo with adjacency matrix and array for priority queue");
        int[][] graph = {
                {0, 10, 0, 0, 5},
                {0, 0, 1, 0, 2},
                {0, 0, 0, 4, 0},
                {7, 0, 6, 0, 0},
                {0, 3, 9, 2, 0}
               };

            dijkstra(graph, 0);
    
    }   
}