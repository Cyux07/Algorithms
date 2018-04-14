package april2018;

/**1. 还是买卖不能交叉，2. 每次买了之后下一天不能再买*/
//prices = [1, 2, 3, 0, 2]
//maxProfit = 3
//transactions = [buy, sell, cooldown, buy, sell]

//[1,2,4] 3
//[1, 3, 4, 9, 0 ,2]

public class BestTimetoBuyandSellStockwithCooldown {
    public int maxProfit(int[] prices) {
        return maxSofar(prices, 0, 0);
    }
    /**recursive*/
    private int maxSofar(int[] prices, int begin, int cur) {
        if (begin >= prices.length - 1)
            return cur;

        int profit = 0, max = 0;
        while (begin < prices.length - 1 && prices[begin] < prices[begin + 1]) {
            profit += prices[begin + 1] - prices[begin];
            max = Math.max(maxSofar(prices, begin + 2, cur + profit), max);
            begin++;
        }

        return Math.max(max, maxSofar(prices, begin + 1, cur));
    }

    public int maxProfit2(int[] prices) {
        int[] profits = new int[prices.length - 1];
        for (int i = 0; i < prices.length - 1; i++) {
            profits[i] = prices[i + 1] - prices[i];
        }
        //TODO imp
        return 0;
    }

    //Basically for day i there are three types of action we can consider: sell, buy and cooldown.
    //
    //If we want to buy, then i-1 day must be cooldown, so after buy today our portfolio value should be cooldown-prices[i].
    // if this number is small than buy itself, then we don’t buy today.
    //
    //If we want to cooldown, then i-1 day must be cooldown or sell. So we take the max of these two.
    //
    //If we want to sell, then before day i, we must have position, so after sell our portfolio value should be day i-1’s buy+prices[i].
    // if this value is smaller than sell itself, then we don’t sell today.
    public int maxProfit3(int[] prices) { /**49.25%*/
        if (prices.length < 2) return 0;

        int buy = -prices[0], cooldown = 0, sell = 0;
        for (int i = 1; i < prices.length; i++) {
            int temp = buy;
            buy = Math.max(cooldown - prices[i], buy); //如果想buy，前一天肯定是处在cooldown状态
            cooldown = Math.max(cooldown, sell); //如果想cooldown，那么肯定前一天也是cooldown或者前一天才卖出
            sell = Math.max(temp + prices[i], sell);//顺次在后，语句在前
        }

        return Math.max(cooldown, sell);
    }

    public static void main(String[] s) {
        System.out.println(new BestTimetoBuyandSellStockwithCooldown().maxProfit(new int[]{1, 2, 3, 0, 2}));
        System.out.println(new BestTimetoBuyandSellStockwithCooldown().maxProfit(new int[]{1, 2, 4}));
    }
}
