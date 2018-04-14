import java.util.Scanner;

/**
 * Created by Administrator on 2016/06/19.
 * 回文字符串
 时间限制：3000 ms  |  内存限制：65535 KB
 难度：4
 描述
 所谓回文字符串，就是一个字符串，从左到右读和从右到左读是完全一样的，比如"aba"。当然，我们给你的问题不会再简单到判断一个字符串是不是回文字符串。现在要求你，给你一个字符串，可在任意位置添加字符，最少再添加几个字符，可以使这个字符串成为回文字符串。
 输入
 第一行给出整数N（0<N<100）
 接下来的N行，每行一个字符串，每个字符串长度不超过1000.
 输出
 每行输出所需添加的最少字符数
 样例输入
 1
 Ab3bd
 样例输出
 2

 思路(动态规划):
 考虑最长公共子序列问题如何分解成子问题，设A=“a0，a1，…，am-1”，B=“b0，b1，…，bm-1”，并Z=“z0，z1，…，zk-1”为它们的最长公共子序列。不难证明有以下性质：

 （1） 如果am-1=bn-1，则zk-1=am-1=bn-1，且“z0，z1，…，zk-2”是“a0，a1，…，am-2”和“b0，b1，…，bn-2”的一个最长公共子序列；

 （2） 如果am-1!=bn-1，则若zk-1!=am-1，蕴涵“z0，z1，…，zk-1”是“a0，a1，…，am-2”和“b0，b1，…，bn-1”的一个最长公共子序列；

 （3） 如果am-1!=bn-1，则若zk-1!=bn-1，蕴涵“z0，z1，…，zk-1”是“a0，a1，…，am-1”和“b0，b1，…，bn-2”的一个最长公共子序列。

 这样，在找A和B的公共子序列时，如有am-1=bn-1，则进一步解决一个子问题，找“a0，a1，…，am-2”和“b0，b1，…，bm-2”的一个最长公共子序列；
 如果am-1!=bn-1，则要解决两个子问题，找出“a0，a1，…，am-2”和“b0，b1，…，bn-1”的一个最长公共子序列和找出“a0，a1，…，am-1”和
 “b0，b1，…，bn-2”的一个最长公共子序列，再取两者中较长者作为A和B的最长公共子序列。
 */
public class Title37 {
    /*public static void main(String[] args) {
        //TODO wrong answer
        Scanner input = new Scanner(System.in);

        int loop = input.nextInt();
        while (loop-->0) {
            char[] cs = input.next().toCharArray();
            int i = 0, l = cs.length;
            int count = 0;

            while (i < l) {
                if (cs[l - 1] != cs[i]) {
                    count++;
                    i++;
                }else {
                    i++;l--;continue;
                }

                if (cs[l - 1] != cs[i]) {
                    count++;
                    l--;
                }else {
                    i++;l--;continue;
                }
            }

            System.out.println(count);
        }
    }*/

    public static void main(String[] args) {
        final int  maxn = 1005;
        Scanner input = new Scanner(System.in);

        int loop = input.nextInt();
        while (loop-->0) {
            String s = input.next();
            int len = s.length();
            char[] str1 = s.toCharArray();
            char[] str2 = new StringBuffer(s).reverse().toString().toCharArray();
            s = null;//ignore
            int[][] dp = new int[maxn][maxn];

            for (int i = 0; i < len; i++) {
                dp[i][0] = 0;
                dp[0][i] = 0;
            }
            for (int i = 1; i <= len; i++)
                for (int j = 1; j <= len; j++) {
                    if (str1[i - 1] == str2 [j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }

            System.out.println(len - dp[len][len]);
        }
    }
}
