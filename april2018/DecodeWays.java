package april2018;

import java.util.Arrays;

/**一段讯息由A~Z组成，现在把他们编码成'A' -> 1, 'B’ -> 2, ... 'Z' -> 26
 * 给出一段已经编码的数字，求这个数字有多少种还原方式
 * 如，12 '1' '2' = AB, '12' = L 故两种*/
/**再次，输入检查啊输入检查,
 * 情况：""(0), "1"(1), "0"(0), "00"(0), "01"(0???)*/
public class DecodeWays {
    //思路：考察一个数字能否和前边的数字结合，而前边的数字是否已经结合到它前边的数字上
    //dfs， 时间O(n2) 递归深度=空间O(n)
    /** 679ms*/
    private int sum = 0;
    public int numDecodings679(String s) {
        if (s.length() < 1)
            return 0;
        if (s.charAt(0) == '0') // illegal input
            return 0;
        if (s.length() == 1)
            return 1;

        char[] cs = s.toCharArray();

        boolean[] comed = new boolean[cs.length];
        combine(cs, comed, 1);
        return sum;
    }

    private void combine(char[] cs, boolean[] comed, int i) {
        if (i >= cs.length) { //end
            /*int pow = 1;
            for (boolean iscomed : comed)
                if (iscomed) pow *= 2;
            sum = Math.max(sum, pow);*/
            //System.out.println(Arrays.toString(comed));
            sum++;
            return;
        }

        if (cs[i] == '0')
            if(comed[i - 1] || cs[i-1] > '2') //un-valid pre sequence, stop //  illegal check?
                return;
            else { //must combine
                comed[i] = true;
                combine(cs, comed, i + 1);
                return;
            }

        if (cs[i - 1] == '1' || (cs[i] <= '6' && cs[i - 1] == 2)) {
            if (!comed[i - 1]) {
                comed[i] = true;
                combine(cs, comed, i + 1); //combine this num to pre, and go ahead
                comed[i] = false;
            }
        }

        combine(cs, comed, i + 1);//no combine, and go ahead
    }


    /** 2ms, O(n)time, O(1)space DP*/
    public int numDecodings2(String s) {
        if (s.length() == 0)
            return 0;
        if (s.length() == 1) {
            if (s.charAt(0) == '0')
                return 0;
            return 1;
        }
        char[] c = s.toCharArray();
        int n = c.length;
        int prevOne = 1;
        int prevTwo = 1;

        for (int i = 0; i < n; i++) {
            int t = 0;
            if (c[i] != '0') //可以作队首[!,_]
                t += prevOne;
            if (i > 0 && (c[i - 1] == '2' && c[i] <= '6' || c[i - 1] == '1')) { //可以作队尾[_,!]
                t += prevTwo;
            }

            prevTwo = prevOne; //下一个数的队尾方案数=上一个数的队首方案数 (上一个数作队尾时，下一个数就不能作了
            prevOne  = t; //下一个数的队首方案数=上一个数的dp
        }
        return prevOne; //末位数作队尾（独立） + 作队首 的方案数
    }

    /**1ms, DP Solution*/
    public int numDecodings(String s) {
        if(s == null || s.length() == 0)
            return 0;
        int len = s.length();
        int[] dp = new int[len+1];
        dp[0] = 1;
        char cur = '#', prev = '#';
        for(int i = 0; i < len; i++) {
            cur = s.charAt(i);
            if(cur == '0') {
                if(!(prev == '1' || prev == '2'))
                    return 0; //abnormal
                dp[i+1] = dp[i-1]; //i 是 0，一定作队尾
            } else {
                dp[i+1] = dp[i]; //i可独立
                if(prev == '1' || prev == '2' && cur >= '1' && cur <= '6')
                    dp[i+1] += dp[i-1]; //也可和前边合并，i+1作队首
            }
            prev = cur;
        }
        return dp[len];
    }

    /**DP Solution too*/
    public int numDecodings23(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            int first = Integer.valueOf(s.substring(i-1, i)); //s[i-1]
            int second = Integer.valueOf(s.substring(i-2, i));//s[i-2]*10 + s[i-1]
            if(first >= 1 && first <= 9) { //前边可以单个存在
                dp[i] += dp[i-1];
            }
            if(second >= 10 && second <= 26) { //前边可以凑两位数
                dp[i] += dp[i-2];
            }
        }
        return dp[n];
    }

    public static void main(String[] s) {
        DecodeWays ways = new DecodeWays();
        System.out.println(ways.numDecodings("12"));
        System.out.println(ways.numDecodings("10"));
        System.out.println(ways.numDecodings("2101162552052"));
    }
}
