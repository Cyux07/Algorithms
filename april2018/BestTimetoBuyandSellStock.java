package april2018;
/**只能一笔交易*/
public class BestTimetoBuyandSellStock {
    public int maxProfit(int[] prices) { //O(n)比较每个可能的点卖出时和当前min得到利润
        if (prices.length < 1)
            return 0;

        int profit = 0;
        int min = prices[0];
        for (int price : prices) {
            if (price < min)
                min = price;
            profit = Math.max(price - min, profit);
        }

        return profit;
    }
}
