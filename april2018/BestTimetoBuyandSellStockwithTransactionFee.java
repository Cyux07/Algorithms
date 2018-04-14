package april2018;

public class BestTimetoBuyandSellStockwithTransactionFee {
    /**5.07%, 空间O(kn) = O(n), 时间O(n)*/
    public int maxProfit1(int[] prices, int fee) {
        int len = prices.length;
        if (len <= 1)
            return 0;

        int[] buy = new int[len]; //buy, only after rest or sell out
        int[] hold = new int[len];//hold, only after buy in or keeping hold
        int[] sell = new int[len];//sell, only after hold or buy in
        int[] rest = new int[len];//rest, empty in hand, after sell, or keeping rest
        buy[0] = -prices[0];
        hold[0] = -prices[0];
        for (int i = 1; i < len; i++) {
            buy[i] = Math.max(rest[i - 1], sell[i - 1]) - prices[i];
            hold[i] = Math.max(buy[i - 1], hold[i - 1]);
            sell[i] = Math.max(hold[i - 1], buy[i - 1]) + prices[i] - fee;
            rest[i] = Math.max(sell[i - 1], rest[i - 1]);
        }
        return Math.max(sell[len - 1], rest[len - 1]);
    }

    /**9.05%, 时间O(n), 空间O(1), 不记录过程*/
    public int maxProfit2(int[] prices, int fee) {
        int len = prices.length;
        if (len <= 1)
            return 0;

        int buy = -prices[0]; //buy, only after rest or sell out
        int hold = -prices[0];//hold, only after buy in or keeping hold
        int sell = 0;//sell, only after hold or buy in
        int rest = 0;//rest, empty in hand, after sell, or keeping rest
        for (int i = 1; i < len; i++) {
            buy = Math.max(rest, sell) - prices[i];
            hold = Math.max(buy, hold);
            sell = Math.max(hold, buy) + prices[i] - fee;
            rest = Math.max(sell, rest);
        }
        return Math.max(sell, rest);
    }

    /**~70%*/
    public int maxProfit(int[] prices, int fee) { //prices valid check!
        int hold = -prices[0], sell = 0;
        for(int i = 1;i < prices.length;i++) {
            sell = Math.max(sell, hold + prices[i] - fee); //不抛出 or 抛出
            hold = Math.max(hold, sell - prices[i]); //保持手上有股 or 买入？
        }
        return Math.max(hold, sell);
    }
}
