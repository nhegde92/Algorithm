package graphs;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/*
You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Return the number of distinct islands.


Example 1:
Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
Output: 1

Example 2:
Input: grid = [[1,1,0,1,1],[1,0,0,0,0],[0,0,0,0,1],[1,1,0,1,1]]
Output: 3
 */

/*
The idea is similar to count number of Island. Inorder to count distinct island,
we get the base row and col, which is the first row and col in the dfs iteration.
always subtract the first row and col in the iteration, going forward for all the matches
this will always result in a unique set of row and col relative to the base position which is
essentially 0,0.

Time complexity: O(m × n)
Space complexity: O(m × n)
 */
class NumberOfDistinctIsland {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int numDistinctIslands(int[][] grid) {
        if (grid.length == 0)
            return 0;
        HashSet<List<Integer>> distinctSet = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    List<Integer> li = new ArrayList<>(); // Alternatively can use StringBuffer for faster complexity:
                    countIsland(i, j, li, grid, -1, -1);
                    distinctSet.add(li); // no need to sort the list because the order of operations are same.
                }
            }
        }

        return distinctSet.size();

    }

    public void countIsland(int row, int col, List<Integer> li, int[][] grid,
                            int br, int bc) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != 1)
            return;
        grid[row][col] = 2;
        int baseRow = (br == -1) ? row : br;
        int baseCol = (bc == -1) ? col : bc;
        int relativeRow = row - baseRow;
        int relativeCol = col - baseCol;
        li.add(relativeRow);
        li.add(relativeCol);
        //use baseRow & baseCol not row and col
        for (int[] dir : dirs) {
            countIsland(row + dir[0], col + dir[1], li, grid, baseRow, baseCol);
        }

    }

    public static void main(String args[]){
        NumberOfDistinctIsland obj = new NumberOfDistinctIsland();
        System.out.println(obj.numDistinctIslands(new int[][]{{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}}));
        System.out.println(obj.numDistinctIslands(new int[][]{{1,1,0,1,1},{1,0,0,0,0},{0,0,0,0,1}}));

    }
}