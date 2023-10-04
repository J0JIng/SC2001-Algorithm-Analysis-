import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class generateInput {
    public static final int MAXEDGEWEIGHT = 20;
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Do you want sparse (s) or dense (d)?");
        String input = sc.nextLine();
        System.out.println("How many vertices do you want?");
        int noOfVertices = Integer.parseInt(sc.nextLine());
        sc.close();

        boolean isSparse = input.equalsIgnoreCase("s");
        generateGraph(isSparse, noOfVertices);
    }

    public static void generateGraph(boolean isSparse,int noOfVertices){
        Random random = new Random();
        //vector<Integer>
        try {
            String filename = isSparse ? "test/sparseTest.txt" : "test/denseTest.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(String.valueOf(noOfVertices));
            writer.newLine();

            // generate sparse graph
            if (isSparse) { 

                int noOfEdges = noOfVertices - 1;
                HashSet<Integer> set = new HashSet<>();

                // Generate a connected graph
                int edge_count = 0;
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = 0; i < noOfVertices; i++) {
                    list.add(i);
                }

                int startnode = list.remove(0);
                int nextnode;
                while (!list.isEmpty()) {
                    nextnode = list.remove(new Random().nextInt(list.size()));
                    int randomWeight = 1 + random.nextInt(MAXEDGEWEIGHT);
                    String entry = String.format("%d %d %d", startnode, nextnode, randomWeight);
                    writer.write(entry);
                    writer.newLine();
                    startnode = nextnode;
                    edge_count++;
                }

                // Generate the remaining edges while ensuring connectivity
                while (edge_count != noOfEdges) {
                    int a = new Random().nextInt(noOfVertices);
                    int b;

                    // Ensure that node 'b' is not the same as 'a' and they are not already connected
                    do {
                        b = new Random().nextInt(noOfVertices);
                    } while (a == b);

                    int randomWeight = 1 + random.nextInt(MAXEDGEWEIGHT);
                    String entry = String.format("%d %d %d", a, b, randomWeight);
                    writer.write(entry);
                    writer.newLine();
                    edge_count++;
                }
            } 
            
            // generate dense graph
            else { 
                for (int i = 0; i < noOfVertices; i++) { // noOfEdges = noOfVertices * (noOfVertices - 1);
                    for (int j = 0; j < noOfVertices; j++) {
                        if (i == j) {
                            continue;
                        }
                        int randomWeight = 1 + random.nextInt(MAXEDGEWEIGHT);
                        String entry = String.format("%d %d %d", i, j, randomWeight);
                        writer.write(entry);
                        writer.newLine(); 
                    }
                } 
            }

            writer.close();
            System.out.println("Results saved to " + filename);
        } 
        
        catch (IOException e){
            System.err.println("Error writing to file: " + e.getMessage());
        } 

    }

    
    /* Helper Function */
    /* ONLY FOR UNDIRECTED GRAPH
    // Union-Find: Find operation with path compression
    private static int find(int[] parent, int i) {
        while(i!=parent[i]){
            parent[i] = parent[parent[i]];
            i=parent[i];
        } 
        return i;
    }

    private static void Union(int[] parent, int[]sz , int p , int q) {
        int i = find(parent,p);
        int j = find(parent,q);
        if(i==j) return;
        if(sz[i]<sz[j]) {parent[i] = j ; sz[j] += sz[i];}
        else            {parent[j] = i ; sz[i] += sz[j];}
    }

    private static boolean ifConnected(int[] parent) {
        int root = find(parent,0);
        for(int vertex:parent)
        {   
            // not the same root
            if(root!=find(parent,vertex)) return false;
        }
        return true;
    }
    */



}


