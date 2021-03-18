/**
 * 36. 有效的数独
 * https://leetcode-cn.com/problems/valid-sudoku/
 */
class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 记录行
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] box = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int block = i / 3 * 3 + j / 3;
                    if (row[i][num] || col[j][num] || box[block][num]) {
                        return false;
                    }else {
                        row[i][num] = true;
                        col[j][num] = true;
                        box[block][num] = true;
                    }
                }
            }
        }
        return true;
    }
}