import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class generateInput {
    public static void main (String[] args) {
        // System.out.println("Enter value of n: ");
        // Scanner sc = new Scanner(System.in);
        // int n = sc.nextInt();
        // sc.close();
        Random random = new Random();        
        int n = 0;
        int[] arr = { 10, 100, 1000, 10000, 100000, 1000000, 2000000, 3000000, 4000000, 5000000, 6000000, 7000000, 8000000, 9000000, 10000000 };
        for (int a = 0; a < arr.length; a++) {
            n = arr[a];
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                // random number between 1 to n
                int randomNumber = random.nextInt(1000) + 1;
                result[i] = randomNumber;
            }
    
            String foldername = "testset6";
            String filename = String.format("test/%s/input%d.txt", foldername, n);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                for (int i = 0; i < result.length; i++) {
                    writer.write(String.valueOf(result[i]));
                    writer.newLine();
                }
                writer.close();
                System.out.println("Results saved to " + filename);
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }
    } 
}



