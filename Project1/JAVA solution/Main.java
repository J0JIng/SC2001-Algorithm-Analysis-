import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main (String[] args) {
        int[] numbers = createArray();
        // insertionSort(numbers, 0, numbers.length - 1);
        // mergeSort(numbers, 0, numbers.length - 1);

        int S = 6;

        hybridMergeSort(numbers, 0, numbers.length - 1, S);

        isListSorted(numbers);
        System.out.println(String.format("Number of Comparisons (keyComparisons): %s", String.valueOf(keyComparisons)));
    }

    static int keyComparisons = 0;


    // Arr: array to be sorted, left: left index of array, right: right index of array
    static void insertionSort(int[] Arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            for (int j = i; j > left; j--) {
                if (Arr[j] < Arr[j - 1]) {
                    int temp = Arr[j];
                    Arr[j] = Arr[j - 1];
                    Arr[j - 1] = temp;
                    keyComparisons++;
                } else {
                    break;
                }
            }
        }
    }

    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            keyComparisons++;
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }


    /*
    static void hybridMergeSort(int[] arr, int left, int right, int S) {
        if (left < right) {
            keyComparisons++;
            int mid = left + (right - left) / 2;
            if (mid - left + 1 <= S) {
                insertionSort(arr, left, mid);
            } else {
                hybridMergeSort(arr, left, mid, S); 
            }
           
            if (right - mid <= S) {
                insertionSort(arr, mid + 1, right);
            } else {
                hybridMergeSort(arr, mid + 1, right, S);
            }

            merge(arr, left, mid, right);
        }
    }

    */

    /*  arr: array of input data, left: left index of array, right: right index of array, 
        S: size of subarray to call insertion sort
    */
    static void hybridMergeSort(int[] arr, int left, int right, int S) {
        
        if (right - left <= 0) return ; 
        int mid = left + (right - left) / 2;

        // Check first half of the array is less than or equal to S
        if (mid - left + 1 <= S) {
        // Insertion Sort -> when size of subarray is less than or equal to S
        insertionSort(arr, left, mid);} 
        else {
        // Merge Sort -> when size of subarray is more than S
        hybridMergeSort(arr, left, mid, S); 
        }
           
        // Check Second half of the array is less than or equal to S
        if (right - mid <= S) {
        // Insertion Sort -> when size of subarray is less than or equal to S
        insertionSort(arr, mid + 1, right);} 
        else {
        // Merge Sort -> when size of subarray is more than S
        hybridMergeSort(arr, mid + 1, right, S);
        }
        
        merge(arr, left, mid, right);
        
    }
/* 
    // arr: array that needs to be merged, left: index of first half, m: index of second half
    static void merge(int[] arr, int left, int mid, int right) {
        int leftLength = mid - left + 1;
        int rightLength = right - mid;

        // split the arr into two halves
        int[] leftArr = new int[leftLength];
        int[] rightArr = new int[rightLength];
        for (int i = 0; i < leftLength; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < rightLength; i++) {
            rightArr[i] = arr[mid + i + 1];
        }

        int l = 0;
        int r = 0;
        int k = left;

        // merge
        while(l < leftLength && r < rightLength) {
            if (leftArr[l] <= rightArr[r]) {
                keyComparisons++;
                arr[k] = leftArr[l];
                l++;
            } else {
                keyComparisons++;
                arr[k] = rightArr[r];
                r++;
            }
            k++;
        }

        // add the remaining elements into the arr
        while (l < leftLength) {
            arr[k] = leftArr[l];
            l++;
            k++;
        }
        while (r < rightLength) {
            arr[k] = rightArr[r];
            r++;
            k++;
        }
    }
       */ 
    
     
    // arr: array that needs to be merged, left: index of first half, m: index of second half
    static void merge(int[] arr, int left, int mid, int right) {
        int leftLength = mid - left + 1;
        int rightLength = right - mid;

        // split the arr into two halves
        int[] leftArr = new int[leftLength];
        int[] rightArr = new int[rightLength];
        for (int i = 0; i < leftLength; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < rightLength; i++) {
            rightArr[i] = arr[mid + i + 1];
        }

        int l = 0;
        int r = 0;
        int k = left;

        //  while both halves are not empty
        while(l < leftLength && r < rightLength) {
            keyComparisons++;
            // 1st element of 1st half is smaller
            if (leftArr[l] < rightArr[r]) 
            {
                arr[k] = leftArr[l];
                l++;
            }
            // 1st element of 2nd half is smaller
            else if (leftArr[l] > rightArr[r])
            {
                arr[k] = rightArr[r];
                r++;
            }
            // Both element equal 
            else 
            {
                arr[k++] = leftArr[l]; 
                arr[k] = rightArr[r];
                l++;
                r++;
            }
            k++;
        }

        // while left half is not empty
        while (l < leftLength) {
            arr[k] = leftArr[l];
            l++;
            k++;
        }

        // While right half is not empty
        while (r < rightLength) {
            arr[k] = rightArr[r];
            r++;
            k++;
        }
    }



    /* Helpers */

    static int[] createArray() {
        List<Integer> numbers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int input = sc.nextInt();
            numbers.add(input);
        }
        sc.close();
        int [] arr = convertArrayListToArray(numbers);
        return arr;
    }

    static int[] convertArrayListToArray(List<Integer> arrlist) {
        int len = arrlist.size();
        int[] result = new int[len];
        for (int i = 0; i < len; i++) {
            result[i] = arrlist.get(i);
        }
        return result;
    }

    static void isListSorted(int[] arr) {
        boolean sorted = true;
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            if (arr[i] - (arr[i + 1]) > 0) {
                sorted = false;
                break;
            }
        }
        if (sorted) {
            // System.out.println("List is sorted!");
        } else {
            System.out.println("ERROR: List is NOT sorted!");
        }
    }

    static void printList(int[] arr) {
        StringBuilder stringBuilder = new StringBuilder();
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            if (i > 0) {
                stringBuilder.append(",");
            }
            stringBuilder.append(arr[i]);
        }
        String result = stringBuilder.toString();
        System.out.println(result);
    }
}
