package april2018;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * 给定数组arr(未排序),返回arr的最长递增子序列。
 * arr=[2,1,5,3,6,4,8,9,7],返回的最长递增子序列为[1,3,4,8,9]。
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], 故长度为 4.
 * 基本要求：O(n2)复杂度,
 * 进阶要求：O(nlogn)复杂度
 */
//LeetCode: 可能有多个这样的LIS组合,只要求返回长度,不记录元素
public class LongestIncreaseSubsequent {
    /**
     * 54.28%
     */
    //对于每个数，找前面可能凑成的最长串，O(n2)
    //max改成记录下标，再从max下标处遍历前面的数，按前面的数较小且dp值相差1，凑齐前面的数，O(n)
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1)
            return nums.length;

        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1);
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            int ind = i - 1;
            while (ind >= 0)
                if (nums[ind--] < nums[i])
                    dp[i] = Math.max(dp[i], dp[ind + 1] + 1);

            max = Math.max(dp[i], max);
        }

        return max;
    }

    //返回任一子串
    public int[] generateOfLIS(int[] nums) {
        if (nums.length <= 1)
            return nums;

        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1);
        int max = 0, index = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            } else {
                int ind = i - 2;
                while (ind >= 0)
                    if (nums[ind--] < nums[i])
                        dp[i] = Math.max(dp[i], dp[ind + 1] + 1);
            }
            if (dp[i] > max) {
                max = dp[i];
                index = i;
            }
        }

        int[] result = new int[max]; //max len
        int i = result.length;
        while (index >= 0) {
            result[--i] = dp[index];
            int temp = index;
            while (--temp >= 0) {
                if (dp[temp] == dp[index] - 1
                        && nums[temp] < nums[index])
                    break;
            }
        }

        return result;
    }

    /**86.50%*/
    //timeO(NlogN), spaceO(N)
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;

        int[] dp = new int[nums.length]; //为了生成子序列本体
        int[] ends = new int[nums.length]; //有效区0~right, 表示最小结尾数
        ends[0] = nums[0];
        dp[0] = 1;
        int right = 0; //0~right: valid bound of ends[]
        int l = 0, r = 0, m = 0;
        for (int i = 1; i < nums.length; i++) {
            l = 0;
            r = right;
            while (l <= r) { //二分查找结尾数中小于等于 nums[i] 的
                m = (l + r) / 2;
                if (nums[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l); //num 大于所有结尾数时更新范围=最大长度
            ends[l] = nums[i]; //替换/新增ends
            dp[i] = l + 1; //+1, 下标从0开始
        }

        return max(dp); //or the sequence, same as Solution 1
    }

    private int max(int[] dp) {
        int max = 0;
        for (int p : dp)
            max = Math.max(p, max);

        return max;
    }

    /**more effective one*/
    public int lengthOfLIS3(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = 0, j = size;
            while (i != j) { //二分
                int m = (i + j) / 2;
                if (tails[m] < x)
                    i = m + 1;
                else
                    j = m;
            }
            tails[i] = x;
            if (i == size) ++size;
        }
        return size;
    }

    public static void main(String[] s) {
        System.out.println(new LongestIncreaseSubsequent().lengthOfLIS2(new int[]{10, 9, 2, 5, 3, 7, 101, 18})); //4
        System.out.println(new LongestIncreaseSubsequent().lengthOfLIS2(new int[]{2, 1, 5, 3, 6, 4, 8, 9, 7}));//5
        System.out.println(new LongestIncreaseSubsequent().lengthOfLIS2(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));//6


    }
}
