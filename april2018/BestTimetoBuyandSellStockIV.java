package april2018;
/**最多可进行k次，和最多两次差不多吧*/

/**
 * dp[i, j] represents the max profit up until prices[j] using at most i transactions.
 * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
 *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
 * dp[0, j] = 0; 0 transactions makes 0 profit
 * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
 */
public class BestTimetoBuyandSellStockIV {
    public int maxProfit(int k, int[] prices) {
        if (k /2 > prices.length)
            return quickSolve(prices); //like BTtBSS II, unlimited stock
        int[][] profit = new int[k + 1][prices.length];

        for (int i = 1; i <= k; i++) {
            int tempMax = -prices[0];
            for (int j = 1; j < prices.length; j++) { //reverse like III
                profit[i][j] = Math.max(tempMax + prices[j], profit[i][j - 1]); //卖出j股，or 不进行这次交易
                tempMax = Math.max(tempMax, profit[i - 1][j - 1] - prices[j]); //前i股，并购入j股
            }
        }

        return profit[k][prices.length-1];
    }

    private int quickSolve(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                max += prices[i] - prices[i - 1];
        }

        return max;
    }
}
