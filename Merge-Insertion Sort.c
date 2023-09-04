#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void GenerateRandomArray(int *array, int size);
void InsertionSort(int *array, int n, int m);
void HybridSort(int *array, int n, int m, int S);
void MergeSort(int *array, int n, int m);
void Merge(int *array, int n, int m);
int compare(int a, int b);
void printArr(int *array, int size);

static int count = 0;

int main() {
    int size;
    int S;
    clock_t start, end;
    double cpu_time_used;

    printf("Size of dataset (max 10,000,000): ");
    scanf("%d", &size);

    if (size > 10000000) {
        printf("Error: Size exceeds the maximum limit.\n");
        return 1;
    }

    printf("Threshold Value: ");
    scanf("%d", &S);

    int *array = malloc(sizeof(int) * size);

    if (array == NULL) {
        printf("Error: Memory allocation failed.\n");
        return 1;
    }

    GenerateRandomArray(array, size);
    printArr(array, size);
    start = clock(); // Record the start time
    HybridSort(array, 0, size - 1, S);
    end = clock(); // Record the end time
    cpu_time_used = ((double)(end - start)) / CLOCKS_PER_SEC;
    printf("CPU Time Used: %f seconds\n", cpu_time_used);
    printf("Number of comparisons made: %d\n", count);
    printArr(array, size);

    free(array);
    return 0;
}

///////////////////////////////////////////////////////////////////////////

void GenerateRandomArray(int *array, int size) {
    //srand(time(NULL)); // Uncommented to seed the random number generator
    for (int i = 0; i < size; i++) {
        array[i] = rand() % size + 1;
    }
}

void printArr(int *array, int size) {
    for (int i = 0; i < size; i++) {
        printf("%d\n", array[i]);
    }
}

int compare(int a, int b) {
    count++;
    if (a < b) return -1;
    else if (a > b) return 1;
    return 0;
}

///////////////////////////////////////////////////////////////////////////

void HybridSort(int *array, int n, int m, int S) {
    int mid = (n + m) / 2;

    // algo A
    if (m - n <= 0) return;
    else if (m - n > S) {
        HybridSort(array, n, mid, S);
        HybridSort(array, mid + 1, m, S);
        Merge(array, n, m);
    }
    else
        InsertionSort(array, n, m);

    // Algo B
    // if (m - n <= 0) return;
    
    // if (mid - n + 1 <= S) {
    //     InsertionSort(arr, n, mid);
    // } else {
    //     HybridSort(arr, n, mid, S); 
    // }
    
    // if (m - mid <= S) {
    //     InsertionSort(arr, mid + 1, m);
    // } else {
    //     HybridSort(arr, mid + 1, m, S);
    // }
    // Merge(arr, n, mid, m);
}

void MergeSort(int *array, int n, int m) {
    int mid = (n + m) / 2;

    if (m - n <= 0) return;
    else {
        MergeSort(array, n, mid);
        MergeSort(array, mid + 1, m);
        Merge(array, n, m);
    }
}

void Merge(int *array, int n, int m) {
    int mid = (n + m) / 2;
    int a = n, b = mid + 1, tmp;

    if (m - n <= 0) return;

    while (a <= mid && b <= m) {
        int cmp = compare(array[a], array[b]);
        if (cmp > 0) {
            tmp = array[b++];
            for (int i = ++mid; i > a; i--)
                array[i] = array[i - 1];

            array[a++] = tmp;
        }
        else if (cmp < 0) {
            a++;
        }
        else {
            if (a == mid && b == m) break;
            tmp = array[b++];
            a++;
            for (int i = ++mid; i > a; i--)
                array[i] = array[i - 1];

            array[a++] = tmp;
        }
    }
}

void InsertionSort(int *array, int n, int m) {
    int temp;
    for (int i = n + 1; i <= m; i++) {
        for (int j = i; j > n; j--) {
            if (array[j] < array[j - 1]) {
                temp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = temp;
            }
            else break;
        }
    }
}
///////////////////////////////////////////////////////////////////////////