import java.util.Arrays;

/**
 * @author JackWu
 * @version 1.0
 */
public class SetZeroes {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr = {{1,1,1},{1,0,1},{1,1,1}};
        solution.setZeroes(arr);
        System.out.println(Arrays.toString(arr));
    }

    static class Solution {
        public void setZeroes(int[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

            boolean [][] flags = new boolean[matrix.length][matrix[0].length];

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == 0) {
                        dfs(matrix,flags,i, j);
                    }
                }
            }

            for (int i = 0; i < flags.length; i++) {
                for (int j = 0; j < flags[0].length; j++) {
                    if (flags[i][j]) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        private void dfs(int[][] matrix, boolean[][] flags, int i, int j) {
            if (matrix[i][j] == 0) flags[i][j] = true;
            for (int k = 0; k < i; k++) {
                flags[k][j] = true;
            }
            for (int k = i + 1; k < flags.length; k++) {
                flags[k][j] = true;
            }

            for (int k = 0; k < j; k++) {
                flags[i][k] = true;
            }
            for (int k = j + 1; k < flags[0].length; k++) {
                flags[i][k] = true;
            }
        }
    }
}
