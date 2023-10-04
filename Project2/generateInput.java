import java.util.HashSet;
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

                /* Helper */
                //int[] parent = new int[noOfVertices];
                //int[] sz = new int[noOfVertices];

                //for (int i = 0; i < noOfVertices; i++) {
                //    parent[i] = i;
                //    sz[i] = 1;
                // }

                for (int i = 0; i < noOfEdges; i++) {
                    int randomVertex1 = random.nextInt(noOfVertices);
                    int randomVertex2 = random.nextInt(noOfVertices);
                    set.add(randomVertex1);

                    // Ensure that randomVertex1 and randomVertex2 are in different components
                    while (set.contains(randomVertex2)) {
                        randomVertex2 = random.nextInt(noOfVertices);
                    }
                    //set.add(randomVertex2);

                    // Union the components using WQUPC
                    //Union(parent,sz,randomVertex1,randomVertex2);
                    
                    int randomWeight = 1 + random.nextInt(MAXEDGEWEIGHT);
                    String entry = String.format("%d %d %d", randomVertex1, randomVertex2, randomWeight);
                    writer.write(entry);
                    writer.newLine();
                }

                //if(ifConnected(parent)) System.out.println("Sparse Graph connected");
                //else                    System.out.println("Sparse Graph not connected");
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


