import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class generateInput {
    public static final int MAX_EDGE_WEIGHT = 20;
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
        try {
            String filename = isSparse ? "test/sparseTest.txt" : "test/denseTest.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(String.valueOf(noOfVertices));
            writer.newLine();

            // generate sparse graph
            if (isSparse) { 
                int count = 0;
                int noOfEdges = noOfVertices - 1;
                
                Set<Integer> setOfConnectedVertices = new HashSet<>(); // used to check if vertex is connected - O(1)
                List<Integer> listOfVertices = new ArrayList<>(); // used for indexing and randomisation - O(1)

                int startingVertex = random.nextInt(noOfVertices);
                setOfConnectedVertices.add(startingVertex);
                listOfVertices.add(startingVertex);

                while (count < noOfEdges) {
                    int randomIndex = random.nextInt(listOfVertices.size());
                    int randomVertex1 = listOfVertices.get(randomIndex);
                    int randomVertex2 = random.nextInt(noOfVertices);
                    while (setOfConnectedVertices.contains(randomVertex2)) { // loop until a vertex that is not connected
                        randomVertex2 = random.nextInt(noOfVertices);
                    }

                    setOfConnectedVertices.add(randomVertex2); 
                    listOfVertices.add(randomVertex2);
                    
                    int randomWeight = 1 + random.nextInt(MAX_EDGE_WEIGHT);
                    int randomiseToAndFrom = random.nextInt(2); // output 0 or 1

                    String entry = String.format("%d %d %d", randomVertex1, randomVertex2, randomWeight);
                    if (randomiseToAndFrom == 1) { // randomisation of direction
                        entry = String.format("%d %d %d", randomVertex2, randomVertex1, randomWeight);
                    }
                    writer.write(entry);
                    writer.newLine(); 
                    count++;
                }
            } 
            
            // generate dense graph
            else { 
                for (int i = 0; i < noOfVertices; i++) { // noOfEdges = noOfVertices * (noOfVertices - 1);
                    for (int j = 0; j < noOfVertices; j++) {
                        if (i == j) {
                            continue;
                        }
                        int randomWeight = 1 + random.nextInt(MAX_EDGE_WEIGHT);
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
    
    */



}


