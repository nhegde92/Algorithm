package binarysearch;


/*
You are given an m x n integer matrix matrix with the following two properties:

Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in matrix or false otherwise.

You must write a solution in O(log(m * n)) time complexity.

Example 1:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
Example 2:

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false


 */
/*
Time complexity log(m*n)

The idea is to convert this to a single array, flatten the list such that we can do the binary search.
The key idea is mid/col represents the row and mid%col represents the column.
The logic is that when you flatten there are only c elements in each column. When you divide
the index with total columns it will tell you the row and the total offset in that row is given by index%col;
 */
class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {

        //No element return false
        if (matrix.length == 0)
            return false;

        int rows = matrix.length;
        int col = matrix[0].length;
        int low = 0; // index of the first element
        int high = rows * col - 1; //index of the last element.
        while (low <= high) {
            int mid = low + (high - low) / 2; //find the middle element.

            //element found
            if (matrix[mid / col][mid % col] == target)
                return true;
            //middle elemnt is less then target, move right
            if (matrix[mid / col][mid % col] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }

        //element not found
        return false;
    }

    public static void main(String args[]){
        SearchA2DMatrix obj = new SearchA2DMatrix();
        System.out.println(obj.searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 11));
        System.out.println(obj.searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 21));

    }
}
