package arrays;

/*
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.



Example 1:


Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
Example 2:


Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]


Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000
*/


/*
This Java code rotates a given **n x n matrix by 90 degrees clockwise in-place**,
using two main steps: **transpose** and **horizontal reversal**. The `transpose()` method flips the matrix over
its main diagonal by swapping elements at `(i, j)` with `(j, i)`,
 converting rows into columns. Then, the `rotateMatrix()`
 method reverses each row, effectively achieving a 90-degree clockwise rotation.
 For example, given the matrix:

```
1 2 3
4 5 6
7 8 9
```

After transposition, it becomes:

```
1 4 7
2 5 8
3 6 9
```

Then, reversing each row gives the final rotated matrix:

```
7 4 1
8 5 2
9 6 3
```

This approach is efficient and works in-place without using extra memory for another matrix.

 */
public class RotateImage {
    public void rotate(int[][] matrix) {
        transpose(matrix);
        rotateMatrix(matrix);
    }

    public void transpose(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < i; j++) {
                if (i == j)
                    continue;
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public void rotateMatrix(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++){
            int left = 0;
            int right = matrix[i].length-1;
            while(left <= right){
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                right--;
                left++;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        new RotateImage().rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");

            }
            System.out.println();
        }
    }
}

