package Project2;
import java.util.*;


class DijkstraB {
    public static void main (String[] args) {
        System.out.println("Part B: Dijkstra's Algo with adjacency list and min heap for priority queue");
        List<List<Vertex>> adjLst = new ArrayList<List<Vertex>>();
        initialiseAdjList(adjLst);
        printAdjList(adjLst);
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
            Vertex newV = new Vertex(nextVertex, nextWeight);
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
                String sentence = String.format("Vertex %d is connected to Vertex %d with a weight of %d", i, v.vertex, v.weight);
                System.out.println(sentence);
            }
        } 
    }
    
}

class Vertex implements Comparator<Vertex> {
    public int vertex;
    public int weight;
 
    public Vertex() {}
 
    public Vertex(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
 
    @Override public int compare(Vertex v1, Vertex v2) {
        return v1.weight > v2.weight ? 1 : -1;
    }
}

