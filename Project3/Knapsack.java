package Project3;

class Knapsack {
    public static void main(String[] args) {
        partA();
        partB();
    }
    
    static void infinityKnapsack(int[] w, int[] p, int C, int n) {
        int[] profit = new int[C + 1];
        for (int capacity = 0; capacity <= C; capacity++) {
            for (int object = 0; object < n; object++) {
                int weight = w[object];
                if (capacity - weight >= 0) {
                    if (profit[capacity] < profit[capacity - weight] + p[object]) {
                        profit[capacity] = profit[capacity - weight] + p[object];
                    }
                }
            }
            printArrayWithCapacity(profit, capacity);
        }
        int maxProfit = profit[C];
        System.out.println(String.format("Max profit: %d", maxProfit));
    }

    static void partA() {
        System.out.println("----- Part A -----");
        int[] w = new int[] {4, 6, 8};
        int[] p = new int[] {7, 6, 9};
        int C = 14;
        int n = 3;
        infinityKnapsack(w, p, C, n); 
    }

    static void partB() {
        System.out.println("----- Part B -----");
        int[] w = new int[] {5, 6, 8};
        int[] p = new int[] {7, 6, 9};
        int C = 14;
        int n = 3;
        infinityKnapsack(w, p, C, n);
    }

    // helpers
    static void printArrayWithCapacity(int[] arr, int capacity) {
        System.out.print(String.format("P(%d): ", capacity)); 
        for (int i = 0; i <= capacity; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        } 
    }

    static void lectureExample1() {
        int[] w = new int[] {4, 6, 8, 6};
        int[] p = new int[] {7, 6, 9, 5};
        int C = 20;
        int n = 4;
        oneZeroKnapsack(w, p, C, n); 
    }
    
    static void lectureExample2() {
        int[] w = new int[] {1, 2, 3};
        int[] p = new int[] {1, 4, 6};
        int C = 3;
        int n = 3;
        oneZeroKnapsack(w, p, C, n); 
    }

    static int[][] oneZeroKnapsack(int[] w, int[] p, int C, int n) {
        int[][] profit = new int[C + 1][n + 1];

        for (int r = 1; r <= C; r++) {
            for (int c = 1; c <= n; c++) {
                profit[r][c] = profit[r][c - 1];
                if (w[c - 1] <= r) {
                    if (profit[r][c] < profit[r - w[c - 1]][c - 1] + p[c - 1]) {
                        profit[r][c] = profit[r - w[c - 1]][c - 1] + p[c - 1];
                    }
                }
            }
        }
        printMatrix(profit);

        return profit;
    }
}

