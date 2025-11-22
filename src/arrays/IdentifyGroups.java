
/*


# Grid Group Detection - Coding Interview Question

## Problem Statement

You are given a 2D grid of integers and a threshold value `k`. Your task is to identify all
**consecutive groups** of identical numbers that appear either horizontally (in the same row) or
vertically (in the same column). A group is considered valid only if it contains at least `k` consecutive
identical elements.

### Requirements:
- Return a list of lists, where each inner list contains `[number, count]` representing the value and size of each valid group
- Each cell can only belong to one group (mark visited cells to avoid double counting)
- Groups must be strictly consecutive (no gaps allowed)
- Only consider horizontal (left-to-right) and vertical (top-to-bottom) groups

### Example:

**Input:**
```java
int[][] grid = {
    {1, 1, 1, 2},
    {2, 2, 2, 2},
    {3, 3, 4, 4},
    {4, 4, 4, 4}
};
int k = 3;
```

**Expected Output:**
```java
[[1, 3], [2, 4], [4, 4]]
```

**Explanation:**
- `[1, 3]`: Three consecutive 1's in the first row
- `[2, 4]`: Four consecutive 2's in the second row
- `[4, 4]`: Four consecutive 4's in the fourth row
- The two 3's in row 3 are ignored because their count (2) is less than k (3)
- The two 4's in row 3 are ignored because they're already part of the vertical group

### Test Cases:

**Test Case 1:**
```java
Input: grid = [[1,1,2,2,2]], k = 3
Output: [[2, 3]]
```

**Test Case 2:**
```java
Input: grid = {{5,5},{5,5},{1,1}}, k = 2
Output: [[5, 2], [5, 2], [1, 2]]
 */


/*

logic: for each i & j we scan left to right and top to bottom. If there is i&j
which is repeated more then k times we add it to the result.

In the next iteration we need to make sure we dont scan the same indexes again
For that we can use visited array or conditions  like
if (j == 0 || grid[i][j] != grid[i][j-1]) -> // Check horizontal group (only from leftmost position)
if (i == 0 || grid[i][j] != grid[i-1][j])->  // Check vertical group (only from topmost position)

example [2, 2, 2, 5]
If we don't check the condition and just scan horizontally from every cell:

From (i,0): We'd find group [2, 3]
From (i,1): We'd find group [2, 2] ← Duplicate/overlapping group!
From (i,2): We'd find group [2, 1] ← Another duplicate!
From (i,3): We'd find group [5, 1]

 */
package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IdentifyGroups {

    public List<List<Integer>> findGroups(int[][] grid, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (grid == null || grid.length == 0) return result;

        int rows = grid.length, cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Check horizontal group (only from leftmost position)
                if (j == 0 || grid[i][j] != grid[i][j-1]) {
                    int count = 0, col = j;
                    while (col < cols && grid[i][col] == grid[i][j]) {
                        count++;
                        col++;
                    }
                    if (count >= k) {
                        result.add(Arrays.asList(grid[i][j], count));
                    }
                }

                // Check vertical group (only from topmost position)
                if (i == 0 || grid[i][j] != grid[i-1][j]) {
                    int count = 0, row = i;
                    while (row < rows && grid[row][j] == grid[i][j]) {
                        count++;
                        row++;
                    }
                    if (count >= k) {
                        result.add(Arrays.asList(grid[i][j], count));
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        IdentifyGroups ig = new IdentifyGroups();
        int[][] grid = {
                {1, 1, 1, 2},
                {2, 2, 2, 2},
                {3, 3, 4, 4},
                {4, 4, 4, 4}
        };
        System.out.println(ig.findGroups(grid, 3));
        System.out.println(ig.findGroups(new int[][]{{5,5},{2,2},{1,3}}, 3));
        System.out.println(ig.findGroups(new int[][]{{5,5},{2,2},{1,3}}, 2));
        // Output: [[1, 3], [2, 4], [4, 4]]
    }
}