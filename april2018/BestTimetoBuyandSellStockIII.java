package april2018;

/**参见前两题 + 最多只能进行两笔交易
 * 同样要求卖了才能再买，也就是两个区间不能重叠，找到两个区间端点差值，加起来最大
 * */
/**See also: 53 Maximum Subarray*/

public class BestTimetoBuyandSellStockIII {
    //O(n), O(1) DP, 42.40% 用if比 Math.max 快
    //反序：意味着第二次交易必须在第一次之后
    //正序：意味着两次交易必须在同一天，但本题中正序也是对的
    public int maxProfit(int[] prices) {
        int pay1 = Integer.MIN_VALUE, pay2 = Integer.MIN_VALUE;
        int earn1 = 0, earn2 = 0;

        for (int price : prices) { //一开始持有0元
            earn2 = Math.max(earn2, pay2 + price); //第二笔交易收益 较买入花费差越大越好(和上一轮的值比较
            pay2 = Math.max(pay2, earn1 - price); //第二笔交易买入花费 较之前卖出差越大越好
            earn1 = Math.max(earn1, pay1 + price); //第一笔
            pay1 = Math.max(pay1, -price); //第一笔
        }

        return earn2;
    }

    //TODO other Solution
}
