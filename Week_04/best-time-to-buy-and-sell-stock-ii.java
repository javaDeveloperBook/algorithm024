/**
 * 122. 买卖股票的最佳时机 II
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * 普通解法：只要后一天价格高于前一天价格即买入并卖出，获取收益，累计收益即为最大
 */
class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        if(prices.length == 0) return max;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i-1]) max += prices[i] - prices[i-1];
        }
        return max;
    }
}

/**
 * 动态规划
 * 方程
 * 第i天没有股票：dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
 * 第i天有股票：  dp[i][1] = Math.max(dp[i-1][0] -prices[i], dp[i-1][1]);
 *
 */
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0) return 0;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][0] -prices[i], dp[i-1][1]);
        }
        return dp[n-1][0];
    }
}
