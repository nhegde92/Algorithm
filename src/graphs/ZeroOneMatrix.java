package graphs;
/*
Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two cells sharing a common edge is 1.

Example 1:
Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:

Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]

Constraints:
m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.

*/

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
Note in this problem it does not make sense to put 1. The reason being its tricky to stop as soon as one 0
is encountered. Also BFS is preferred again dist from nearest 0.
 */
public class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] mat) {
        int[][] newArray = new int[mat.length][mat[0].length];
        for (int i = 0; i < newArray.length; i++)
            Arrays.fill(newArray[i], -1);

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    newArray[i][j] = 0;
                    int pair[] = new int[2];
                    pair[0] = i;
                    pair[1] = j;
                    queue.add(pair);
                }
            }
        }
        int count = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int j = 0; j < dirs.length; j++) {
                    int newRow = dirs[j][0] + curr[0];
                    int newCol = dirs[j][1] + curr[1];

                    if (newRow < 0 || newCol < 0 || newRow >= mat.length || newCol >= mat[0].length
                            || newArray[newRow][newCol] != -1)
                        continue;

                    newArray[newRow][newCol] = count;
                    queue.add(new int[]{newRow, newCol});

                }
            }
        }

        return newArray;
    }

    public static void main(String args[]) {
        ZeroOneMatrix obj = new ZeroOneMatrix();
        int[][] mat = {
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };

        int[][] mat2 = {

                {0, 0, 0},
                {0, 1, 0},
                {1, 2, 1}

        };

        int[][] result = obj.updateMatrix(mat);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("------------");
        int[][] result2 = obj.updateMatrix(mat2);
        for (int[] row : result2) {
            System.out.println(Arrays.toString(row));
        }
    }
}
