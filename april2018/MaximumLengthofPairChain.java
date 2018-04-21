package april2018;

import java.util.Arrays;
import java.util.Comparator;

/**646. 定义数对 (c, d) 仅当才b < c的时候才能接到 (a, b) 后边
 * 区间调度问题
 * 仅返回长度 */

public class MaximumLengthofPairChain {
    //slower solution
    public int findLongestChain01(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1])); //method reference
        //Arrays.sort(pairs, (a,b) -> a[1] - b[1]); //lambda
        int sum = 0, n = pairs.length;
        int i = 0;
        while (i < n) {
            int current_end = pairs[i][1];
            sum ++;
            while ( i < n && pairs[i][0] <= current_end) i++;
        }
        return sum;
    }

    /**79.78%*/
    //类似最长增长串的第一种解法，O(n2)
    public int findLongestChain(int[][] pairs) {
        if (pairs.length <= 1)
            return pairs.length;

        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        }); //anno-class

        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < pairs.length; i++) {
            int j = pairs.length - 1;
            while (j > i && pairs[j][0] > pairs[i][1]){
                dp[j] = Math.max(dp[j], dp[i] + 1);
                j--;
            }
        }

        int max = 0;
        for (int d : dp)
            max = Math.max(max, d);
        return max;
    }

    //O(nlogn)time O(1)space, Greedy贪婪算法
    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, (a,b) -> a[1] - b[1]); //按pair尾来排序
        int sum = 0, n = pairs.length, i = -1;
        while (++i < n) {
            sum++;
            int curEnd = pairs[i][1];
            while (i+1 < n && pairs[i+1][0] <= curEnd) i++; //找第一个 头valid的pair,那么它的尾一定是最小的
        }
        return sum;
    }
}
