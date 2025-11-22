package graphs;

/*
You are given an m x n matrix board containing letters 'X' and 'O', capture regions that are surrounded:

Connect: A cell is connected to adjacent cells horizontally or vertically.
Region: To form a region connect every 'O' cell.
Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and none of the region cells are on the edge of the board.
To capture a surrounded region, replace all 'O's with 'X's in-place within the original board. You do not need to return anything.



Example 1:

Input: board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]

Output: [["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]

Explanation:


In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.

Example 2:

Input: board = [["X"]]

Output: [["X"]]


*/

/* Here the idea is anything that anything connected to the wall cannot be conqured. Instead of preocessing every Os just process the ones at the corners. Then mark all the nodes which for a
boundary. The logic is every other O which is not in this boundary will become X

The core idea behind solving the Surrounded Regions problem efficiently is to recognize that any 'O' connected to the border cannot be surrounded,
and therefore should not be flipped. Instead of checking every 'O' individually, we only process 'O's on the border and mark all 'O's connected to them
as safe (typically using BFS or DFS), temporarily replacing them with a marker like 'T'. After marking, we scan the entire board: all remaining 'O's
 (which are not connected to the border and thus are surrounded) are flipped to 'X', while the 'T' cells are reverted back to 'O'. This approach
 is efficient because it limits processing to only the relevant regions, avoids unnecessary recursion, and works entirely in-place without extra memory
 for a visited array.
*/
public class SorroundRegions {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void solve(char[][] board) {
        if (board.length == 0)
            return;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i == 0 || j == 0 || i == board.length - 1 || j == board[i].length - 1) {
                    if (board[i][j] == 'O')
                        dfs(i, j, board);
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                if (board[i][j] == 'T')
                    board[i][j] = 'O';
            }
        }
    }

    private void dfs(int row, int col, char[][] board) {
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] == 'X' || board[row][col] == 'T')
            return;

        board[row][col] = 'T';
        for (int[] dir : dirs) {
            dfs(row + dir[0], col + dir[1], board);
        }
    }

    public static void main(String[] args) {
        SorroundRegions sr = new SorroundRegions();
        char[][] mat = {
                {'X', 'X', 'X', 'X' },
                {'X', 'O', 'O', 'X' },
                {'X', 'X', 'O', 'X' },
                {'X', 'O', 'X', 'X' }
        };
        sr.solve(mat);
        for (char[] row : mat) {
            for (char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
        System.out.println("-------");
        char[][] mat2 = {
                {'X', 'X', 'X', 'X' },
                {'X', 'O', 'O', 'X' },
                {'X', 'X', 'O', 'X' },
                {'X', 'X', 'X', 'X' }
        };
        sr.solve(mat2);
        for (char[] row : mat2) {
            for (char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }


}
