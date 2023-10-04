package Project2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class DijkstraA {
    
	private static final int INF = Integer.MAX_VALUE;

    public static void main (String[] args) {

        System.out.println("Part A: Dijkstra's Algo with adjacency matrix and array for priority queue");
        Scanner sc = new Scanner(System.in);
        int numOfVertices = sc.nextInt();
        int[][] graph = new int[numOfVertices][numOfVertices];

        while (sc.hasNext()) {
            int parentVertex = sc.nextInt();
            int nextVertex = sc.nextInt();
            int nextWeight = sc.nextInt();
            graph[parentVertex][nextVertex] = nextWeight;
        }
        sc.close();

        dijkstra(graph, 0);
    }   

    public static void dijkstra(int[][] graph, int source) {

        int n = graph.length;
        int[] d = new int[n];
        int[] pi = new int[n];
        int[] S = new int[n];

        Arrays.fill(d, INF);
        Arrays.fill(pi, -1);
        
        d[source] = 0;
        ArrayList<int []> priorityQueue = new ArrayList<int []>();
        for(int i = 0 ; i < n ; i++){
            priorityQueue.add(new int[] {d[i],i});
        }

        while (priorityQueue.size()!=0) {
            int min = INF;
            int u = 0; 
            int queueIndex = 0;

            // get min 
            for(int j = 0 ; j<priorityQueue.size(); j++){
                if(priorityQueue.get(j)[0]<min){
                    min = priorityQueue.get(j)[0];
                    u = priorityQueue.get(j)[1];
                    queueIndex = j;
                }
            }
            
            priorityQueue.remove(queueIndex);
            S[u] = 1;
            
            // update d[] values for neighbours adjacent to u
            for (int v = 0; v < n; v++) {
                if(S[v] == 0 && graph[u][v] != 0 && d[u] + graph[u][v] < d[v]){
                    for(int i = 0 ; i<priorityQueue.size(); i++){
                        int[] inner = priorityQueue.get(i);
                        if(inner[1] == v){
                            priorityQueue.remove(i);
                            break;
                        }
                    }
                    d[v] = d[u] + graph[u][v];
                    pi[v] = u;
                    priorityQueue.add(new int[] {d[v],v});
                }
            }
        }
            Print(d, pi);
    }
    
    /*
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
    */

    private static void Print(int[] dist, int[] pi) {
    	System.out.println("Vertex \t Distance from Source \t Predecessor");
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + "\t\t  " + dist[i] + "\t\t     " + pi[i]);
        }
    }
}