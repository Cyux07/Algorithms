package april2018;

/**Design an algorithm to find the maximum profit.
 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * 可以买入买出多次，但是必须上次卖出之后下一次才可以买*/
//相当于 查找所有连续的增长序列
//Input: [7, 1, 5, 3, 6, 4]
//Output: 5 (for Problem I)

public class BestTimetoBuyandSellStockII {

    /**60.43%*/
    public int maxProfit(int[] prices) {
        if (prices.length <= 1)
            return 0;
        int sum = 0, start = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[i-1]) {
                sum += (prices[i-1] - prices[start]);
                start = i;
            }
        }
        sum += (prices[prices.length - 1] - prices[start]);

        return sum;
    }

    /**计算每个相邻位的差值，只要是正的就纳入结果*/
    public int maxProfitPrime(int[] prices) {
        int[] deltas = new int[prices.length];

        for (int i = 0; i < prices.length - 1; i++) {
            deltas[i] = prices[i+1] - prices[i];
        }

        int ret = 0;

        for (int v : deltas) {
            if (v > 0)
                ret += v;
        }

        return ret;
    }

    public static void main(String[] s) {
        System.out.print(new BestTimetoBuyandSellStockII().maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
