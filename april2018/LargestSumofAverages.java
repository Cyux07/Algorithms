package april2018;

// 将数组A分成最多k个相邻的组，我们的分数就是每个组的平均值的和 求导这个最大的分数
//A = [9,1,2,3,9]
//K = 3
//Output: 20
//Note:
//
//1 <= A.length <= 100.
//1 <= A[i] <= 10000.
//1 <= K <= A.length.
//Answers within 10^-6 of the correct answer will be accepted as correct. 暗示float？
public class LargestSumofAverages {
    public double largestSumOfAverages(int[] A, int K) {
        int N = A.length;
        double[] P = new double[N + 1]; //前n项和，存储以省时
        for (int i = 0; i < N; i++)
            P[i + 1] = P[i] + A[i];

        double[] dp = new double[N]; //dp[i]表示在A[i:]上分割最多k次的最高分
        for (int i = 0; i < N; i++)
            dp[i] = (P[N] - P[i]) / (N - i); //不作切割

        for (int k = 0; k< K-1; k++) //分成K段，找K-1个标，标的发现顺序和前后顺序不重要
            for (int i = 0; i < N; i++)
                for (int j = i + 1; j < N; j++) //前 + dp[后部分]
                    dp[i] = Math.max(dp[i], (P[j] - P[i]) / (j-i) + dp[j]);

        return dp[0]; //A[0:] = A[:]整体最优
    }
}
