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
        System.out.println(String.format("Number of Comparisons (Count): %s", String.valueOf(count)));
    }

    static int count = 0;

    static void insertionSort(int[] unsortedArr, int left, int right) {
        for (int i = left; i <= right; i++) {
            for (int j = i; j > left; j--) {
                if (unsortedArr[j] < unsortedArr[j - 1]) {
                    int temp = unsortedArr[j];
                    unsortedArr[j] = unsortedArr[j - 1];
                    unsortedArr[j - 1] = temp;
                    count++;
                } else {
                    break;
                }
            }
        }
    }

    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            count++;
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void hybridMergeSort(int[] arr, int left, int right, int S) {
        if (left < right) {
            count++;
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
                count++;
                arr[k] = leftArr[l];
                l++;
            } else {
                count++;
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
