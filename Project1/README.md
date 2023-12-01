## Project 1: Integration of Mergesort & Insertion Sort

### Abstract:

This project introduces a hybrid sorting algorithm by combining Mergesort with Insertion Sort for enhanced efficiency. 

Utilising a threshold value, S, the algorithm seamlessly transitions to Insertion Sort for small-sized subarrays during recursive calls, addressing the inefficiency of the overhead of many recursive calls the Mergesort algorithm makes.

The combined hybrid approach aims to improve overall sorting performance. The following contains our findings.

### 1. Implementation of Hybrid Sort.

The pseudocode for Hybrid Sort is as follows.

```bash
// arr: array of input data, 
// left: left index of array, 
// right: right index of array, 
// S: size of subarray to call insertion sort

hybridSort(arr, left, right, S):
    if right - left <= 0:
        return
    
    mid = left + (right - left) / 2

    // Check first half of the array is less than or equal to S
    if mid - left + 1 <= S:
        call insertionSort(arr, left, mid)
    else:
        call hybridMergeSort(arr, left, mid, S)

    // Check second half of the array is less than or equal to S
    if right - mid <= S:
        call insertionSort(arr, mid + 1, right)
    else:
        call hybridMergeSort(arr, mid + 1, right, S)

    call merge(arr, left, mid, right)
   ```

### 2. Time Complexity

|              | Time Complexity     |      
| :---:        | :----:              |         
| Best Case    |    O(n+nlog(n/s))   |             
| Average Case |  O(nS+nlog(n/S))    |       
| Worst Case   |   O(nS+nlog(n/S))   |       

### 3. Number of key comparisons Vs. different input size

For a fixed value of S = 10, with varying input sizes from 1k - 10m.

![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/9ef0a598-829e-4563-a09a-22e3ffdc2ca0)

### 4. Number of key comparisons Vs. fixed input size

For a varying value of S = 2 to 100, With a fixed input size of 1k.

![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/d8048fb4-e244-48c0-9bfb-d6d3c129a0e5)

A step-like response is observed. This is due to how the data is split in the mergeSort algorithm. For instance, given an Input size of 20, it would be divided into a subarray of size 10-10, followed by a subarray of size 5-5 5-5. Regardless of whether the S value is 5,6,7,8 or 9, the insertion sort is still performed on the subarray of size 5.

### 5. Determining the optimal value for S

From our empirical results, our team found that a small value S = 3 is optimal. We obtained this result by comparing the S values against multiple datasets of varying sizes.

![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/072e63b4-85d1-4540-b42a-2946345a4d1a)

### 6. Comparing Hybrid Sort and the original Merge Sort algorithm

For a fixed value of s = 3 and Input size of 10m random integers. 

Key Comparision: 
![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/33f2c62b-61cd-44dd-906a-19b1c78cb04b)

CPU Time:
![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/9ccece2f-6bdf-456a-a982-4389564991d8)


|              |  Hybrid Sort (S = 3) | Merge Sort |   
| :---:        | :----:               | :----:     |      
| Key Comparisons |   159,160,415     | 169,161,166|   
| CPU Times (ms) |  1,368             | 1,490      |   


Observations:

* Hybrid Sort (S = 3) has about 10 million fewer comparisons than Merge Sort.
* The execution time of Hybrid Sort (S = 3) is about 8.9% faster than that of Merge Sort.

### Conclusion:

These results align with expectations, as the strategic switch to Insertion Sort for smaller subarrays in the Hybrid Sort algorithm contributes to improved efficiency, addressing the inefficiencies associated with recursive calls in Mergesort.

This underscores the practical benefits of adopting a hybrid approach to sorting algorithms in scenarios where subarray sizes vary.



