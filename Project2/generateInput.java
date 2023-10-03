import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class generateInput {
    public static void main (String[] args) {
        Random random = new Random();
        boolean isSparse = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want sparse (s) or dense (d)?");
        String input = sc.nextLine();
        System.out.println("How many vertices do you want?");
        int noOfVertices = Integer.parseInt(sc.nextLine());
        sc.close();
        if (input.equals("s")) {
            isSparse = true;
        } else if (input.equals("d")) {
            isSparse = false; 
        } else {
            System.out.println("invalid input - isSparse set to false");
            isSparse = false; 
        }
        int maxPossibleWeight = 20;

        try {
            String filename = isSparse ? "test/sparseTest.txt" : "test/denseTest.txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(String.valueOf(noOfVertices));
            writer.newLine();
            if (isSparse) { // generate sparse graph
                int noOfEdges = noOfVertices - 1;
                HashSet<Integer> set = new HashSet<>();
                for (int i = 0; i < noOfEdges; i++) {
                    int randomVertex1 = random.nextInt(noOfVertices);
                    set.add(randomVertex1);
                    int randomVertex2 = random.nextInt(noOfVertices);
                    while (set.contains(randomVertex2)) {
                        randomVertex2 = random.nextInt(noOfVertices);
                    }
                    int randomWeight = 1 + random.nextInt(maxPossibleWeight);
                    String entry = String.format("%d %d %d", randomVertex1, randomVertex2, randomWeight);
                    writer.write(entry);
                    writer.newLine();
                } 
            } else { // generate dense graph
                for (int i = 0; i < noOfVertices; i++) { // noOfEdges = noOfVertices * (noOfVertices - 1);
                    for (int j = 0; j < noOfVertices; j++) {
                        if (i == j) {
                            continue;
                        }
                        int randomWeight = 1 + random.nextInt(maxPossibleWeight);
                        String entry = String.format("%d %d %d", i, j, randomWeight);
                        writer.write(entry);
                        writer.newLine(); 
                    }
                } 
            }
            writer.close();
            System.out.println("Results saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        } 
    } 
}



