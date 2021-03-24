/**
 * 64. 最小路径和
 * https://leetcode-cn.com/problems/minimum-path-sum/
 *
 * 动态规划：
 *
 * 由于路径的方向只能是向下或向右,创建二维数组 dp，与原始网格的大小相同，dp[i][j] 表示从左上角出发到 (i,j)(i,j) 位置的最小路径和。显然，\textit{dp}[0][0]=\textit{grid}[0][0]dp[0][0]=grid[0][0]。对于 \textit{dp}dp 中的其余元素，通过以下状态转移方程计算元素值。
 *
 * 当 i>0i>0 且 j=0j=0 时，dp[i][0]=dp[i−1][0]+grid[i][0]。
 *
 * 当 i=0i=0 且 j>0j>0 时，dp[0][j]=dp[0][j−1]+grid[0][j]。
 *
 * 当 i>0i>0 且 j>0j>0 时，dp[i][j]=min(dp[i−1][j],dp[i][j−1])+grid[i][j]。
 *
 * 最后得到 dp[m−1][n−1] 的值即为从网格左上角到网格右下角的最小路径和。

 */
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];

        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }

        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j],dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[row - 1][col - 1];
    }
}