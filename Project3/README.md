## Project 3: Dynamic Programming - Unlimited knapSack 

### Abstract:
This project addresses the classic knapsack problem, wherein a knapsack of capacity weight C is given, along with n types of objects.

Each object of the ith type possesses weight wi and profit pi, all of which are positive integers (i = 0, 1, …, n-1). The availability of an **unlimited** supply for each object type prompts the exploration of an optimal solution. 

The objective is to determine the maximum profit attainable by selecting objects to fill the knapsack within its specified capacity. The study introduces the notation P(C,i) to denote the maximum profit achievable by strategically packing objects i into the knapsack of capacity C. The following contains our findings.

### 1. Recursive definition of the function P(c,i)

```math
p(c,j) = \begin{cases}
  0, & \text{if } c = 0 \text{ or } j = 0, \\
   \max_{0 \leq i < n} \{ P(C - w_i) + p_i \}, & \text{if } c \geq 1 \text{ and } j \geq 1, \text{ otherwise.} \\
\end{cases}
```
Here:
- \(P(C,i)\) represents the maximum profit achievable for a knapsack of remaining capacity \(C,i\).
- \(w_i\) is the weight of the ith type of object.
- \(p_i\) is the profit of the ith type of object.
- \(n\) is the total number of types of objects available.

### 2. Subproblem graph for P(14)

For the given weights and profits given below:

|      | 0 |1 | 2 |
|------| --- | ---  | ---  | 
| Wᵢ   | 4 | 6 | 8 | 
| Pᵢ   | 7 | 6 | 9 |

The subproblem gragh P(14) is as follows. 

![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/e957b45e-8a08-49f5-a897-7f303b17c91e)


### 3. Implementation of knapSack problem

In solving the Knapsack Problem, a dynamic programming approach using the Bottom-Up technique is employed. This 1-D implementation, systematically explores all possible combinations of objects and capacities, incrementally calculating and updating the maximum achievable profit. 

```bash
// w: weight of object, 
// p: profit of object, 
// C: Capacity of Knapsack, 
// n: input size of objects

infinityKnapsack(w, p, C, n):
    // Initialize an array to store maximum profits for different capacities
    profit[C + 1] = 0

    // Iterate over capacities from 0 to C
    for capacity in 0 to C:
        // Iterate over available objects
        for object in 0 to n - 1:
            weight = w[object]
            
            // Check if the object can fit into the knapsack
            if capacity - weight >= 0:
                // Update the maximum profit for the current capacity
                if profit[capacity] < profit[capacity - weight] + p[object]:
                    profit[capacity] = profit[capacity - weight] + p[object]

    // The maximum profit is stored at the last index of the profit array
    maxProfit = profit[C]
   ```

### 4. Running result of P(14)

For the given weights and profits given below:

|      | 0 |1 | 2 |
|------| --- | ---  | ---  | 
| Wᵢ   | 4 | 6 | 8 | 
| Pᵢ   | 7 | 6 | 9 |

![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/e961b179-c155-4bf1-a431-4bd27de1ac01)


* Maximum profit : 21

For the given weights and profits given below:

|      | 0 |1 | 2 |
|------| --- | ---  | ---  | 
| Wᵢ   | 4 | 6 | 8 | 
| Pᵢ   | 7 | 6 | 9 |

![image](https://github.com/J0JIng/SC2001-Algorithm-Analysis-/assets/111691710/1a7e3594-dbff-4699-8044-aad947ebb991)

* Maximum profit : 16
