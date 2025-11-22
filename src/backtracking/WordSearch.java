package backtracking;

/*
Given a 2-D grid of characters board and a string word,
return true if the word is present in the grid, otherwise return false.

For the word to be present it must be possible to form it with a path
in the board with horizontally or vertically neighboring cells.
The same cell may not be used more than once in a word.
*/

public class WordSearch {

    public boolean wordSearch(char board[][], String word) {
        char[] cArray = word.toCharArray();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == cArray[0] && search(board, cArray, 0, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean search(char[][] board, char[] cArray, int index, int row, int col) {
        if (index == cArray.length)
            return true;

        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length)
            return false;

        if (board[row][col] != cArray[index])
            return false;

        char originalChar = board[row][col];
        board[row][col] = '#'; // mark visited

        boolean found =
                search(board, cArray, index + 1, row + 1, col) ||
                        search(board, cArray, index + 1, row - 1, col) ||
                        search(board, cArray, index + 1, row, col + 1) ||
                        search(board, cArray, index + 1, row, col - 1);

        board[row][col] = originalChar; // unmark

        return found;
    }

    // 🧪 Public static main for testing
    public static void main(String[] args) {
        WordSearch ws = new WordSearch();

        char[][] board = {
                {'A', 'B', 'C', 'D'},
                {'S', 'A', 'A', 'T'},
                {'A', 'C', 'A', 'E'}
        };

        String[] words = {"CAT", "BAT", "ASA", "ACE", "DATA"};

        for (String word : words) {
            boolean result = ws.wordSearch(board, word);
            System.out.println("Word \"" + word + "\" exists in board? " + result);
        }
    }
}
