package march2018;

import java.util.List;


/**所以思路应该改变为 并不是从上分裂到下边两个相邻的，二是每个数考虑从上边两个相邻的下来...no*/

/**使用贴士: Ctrl+Shift+Enter 加花括号...*/
public class Triangle {
    public static void main(String[] s) {
        Triangle triangle = new Triangle();

        //[
        //     [2],
        //    [3,4],
        //   [6,5,7],
        //  [4,1,8,3]
        //]

        //[[-1],[2,3],[1,-1,-3]]
    }


    /**Time Limit Exceeded*/
    int min = Integer.MAX_VALUE;
    public int minimumTotal1(List<List<Integer>> triangle) {
        if (triangle.size() <= 0)
            return 0;
        findMin(triangle, 1, 0, triangle.get(0).get(0));
        return min;
    }

    private void findMin(List<List<Integer>> triangle, int level, int index, int value) {
        if (level == triangle.size()) {
            min = Math.min(min, value);
            return;
        }

        /*if (value > min) //剪枝 -> 不可，元素有负数
            return;*/
        int i = triangle.get(level).get(index), i1 = triangle.get(level).get(index + 1);
        if (i < i1) {
            findMin(triangle, level + 1, index, value + i);
            findMin(triangle, level + 1, index + 1, value + i1);
        }else {
            findMin(triangle, level + 1, index + 1, value + i1);
            findMin(triangle, level + 1, index, value + i);
        }
    }

    //Just save the value in the triangle itself.
    //从下到上，DP Solution
    /**24.30%*/
    public int minimumTotal(List<List<Integer>> triangle) {
        for (int level = triangle.size() - 2; level >= 0; level--) {
            for (int i = 0; i <= level; i++) {
                List<Integer> nextLevel = triangle.get(level + 1);
                triangle.get(level)
                        .set(i, Math.min(nextLevel.get(i), nextLevel.get(i + 1))
                        + triangle.get(level).get(i));
            }
        }

        return triangle.get(0).get(0);
    }

    /**upgrade: 存到额外int[]中速度反而高些，因为少拆几个箱么*/
    public int minimumTotalPrime(List<List<Integer>> triangle) {
        if (triangle.size() == 0)
            return 0;
        if (triangle.size() == 1)
            return triangle.get(0).get(0);

        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        return minimumTotal(triangle, dp, 1);
    }

    public int minimumTotal(List<List<Integer>> triangle, int[] dp, int lvlidx) {
        /**
         * dp: dp[i]_lvlidx = the min path sum up to current level and up to
         * index i
         *
         * dp[0]_lvlidx = this_level_list[0] + dp[0]_(lvlidx-1);
         * dp[end]_lvlidx = this_level_list[end] + dp[end-1]_(lvlidx-1);
         *
         * dp[i]_lvlidx = this_level_list[i] + min{ dp[i-1]_(lvlidx-1),
         * dp[i]_(lvlidx-1) };
         */

        List<Integer> list = triangle.get(lvlidx);
        int pre = dp[0], temp;
        dp[0] += list.get(0);
        for (int i = 1; i < lvlidx; i++) {
            temp = dp[i];
            dp[i] = list.get(i) + Math.min(pre, dp[i]);
            pre = temp;
        }
        dp[lvlidx] = pre + list.get(lvlidx);

        if (lvlidx + 1 == triangle.size()) {
            int res = dp[0];
            for (int i = 1; i <= lvlidx; i++)
                res = Math.min(res, dp[i]);
            return res;
        }

        return minimumTotal(triangle, dp, lvlidx + 1);
    }
}
