public class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // If the cell is empty
                if (board[i][j] == '.') {
                    // Try every number from 1 to 9
                    for (char num = '1'; num <= '9'; num++) {
                        if (isValid(board, i, j, num)) {
                            board[i][j] = num;  // Place the number
                            // Recursively call backtrack to fill the next cells
                            if (backtrack(board)) {
                                return true;  // If the next cells were filled correctly, return true
                            }
                            board[i][j] = '.';  // Backtrack, undo the current cell's number
                        }
                    }
                    return false;  // If no number from 1 to 9 is valid, return false to backtrack
                }
            }
        }
        return true;  // If no empty cell was found, the board is solved
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        // Check if the number is already in the current row
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check if the number is already in the current column
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check if the number is already in the 3x3 sub-grid
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;  // The number is valid
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example input
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };

        // Solve the Sudoku puzzle
        sol.solveSudoku(board);

        // Output the solved board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
