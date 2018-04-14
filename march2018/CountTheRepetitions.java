package march2018;

/**Define S = [s,n] as the string S which consists of n connected strings s. For example, ["abc", 3] ="abcabcabc".

 On the other hand, we define that string s1 can be obtained from string s2
 if we can remove some characters from s2 such that it becomes s1.
 For example, “abc” can be obtained from “abdbec” based on our definition,
 but it can not be obtained from “acbbe”.

 You are given two non-empty strings s1 and s2 (each at most 100 characters long)
 and two integers 0 ≤ n1 ≤ 106 and 1 ≤ n2 ≤ 106. Now consider the strings S1 and S2,
 where S1=[s1,n1] and S2=[s2,n2]. Find the maximum integer M such that [S2,M] can be obtained from S1.*/

import java.util.ArrayList;

/**大概意思就是S1 = s1*n1次，S2 = s2 * n2次，现在要看S1里边可以重复出现s2多少次 */

//Example:
//
//Input:
//s1="acb", n1=4
//s2="ab", n2=2
//
//Return:
//2


    /*"phqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikef"
1000000
"fmznimkkasvwsrenzkycxfxtlsgypsfad"
333
answer: 333*/
public class CountTheRepetitions {
    public static void main(String[] s) {
        //System.out.println(new CountTheRepetitions().getMaxRepetitions("acb", 4, "ab", 2));
        //aabaabaab, aba
        //System.out.println(new CountTheRepetitions().getMaxRepetitions("aabaabaab", 4, "aba", 2));
        System.out.println(new CountTheRepetitions().getMaxRepetitions(
                "phqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozvsrtkjprepggxrpnrvystmwcysyycqpevikef",
                1000000,
                "fmznimkkasvwsrenzkycxfxtlsgypsfad",
                333));
    }

    /**brute force 35.77%
     * 要点是先转array，一直charAt性能耗死*/
    public int getMaxRepetitions1(String s1, int n1, String s2, int n2) {
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
        int slen = 0, scount = 0/*, count = 0*/;
        while (n1-- > 0) {
            for (char ac1 : c1) {
                if (ac1 == c2[slen])
                    slen++;
                if (slen == c2.length) {
                    scount++;
                    slen = 0;
                }
                /*if (scount == c2.length) {
                    count++;
                    scount = 0;
                }*/
            }
        }
        return (int) (scount * 1.0 / n2);
    }


    /**试试计算几轮匹配一次，然后直接除: WA 答案丢掉了每轮剩下的那部分*/
    public int getMaxRepetitions2(String s1, int n1, String s2, int n2) {
        int s1len = s1.length(), s2len = s2.length();
        int round = countRound(s1, s2, s1len, s2len);
        if (round == 1) {
            int time = countTime(s1, s2, s1len, s2len); //countRound(s2, s1, s2len, s1len);
            return (int) (n1 * time *  1.0 / n2);
        }

        return (int) (n1 * 1.0 / round / n2);
    }

    /** 计算s1需要走多少轮才能凑齐一个s2 */
    private int countRound(String s1, String s2, int s1len, int s2len) {
        int i = 0, j = 0;
        int round = 1;
        while (i <= s1len && j < s2len) {
            if (i == s1len) {
                round++;
                i = 0;
            }
            if (s1.charAt(i++) == s2.charAt(j))
                j++;
        }

        return round;
    }

    /**要是s1一轮就能走完s2，再看看s1一轮能走完多少个s2*/
    private int countTime(String s1, String s2, int s1len, int s2len) {
        int i = 0, j = 0;
        int round = 1;
        while (i < s1len && j <= s2len) {
            if (j == s2len) {
                round++;
                j = 0;
            }
            if (s1.charAt(i++) == s2.charAt(j))
                j++;
        }

        return round;
    }

    /** 考虑remain，把remain加到最前边
     * 用例3：expect333，out273 多轮匹配一个子串的情况不行。写得复杂过头了
     * */
    public int getMaxRepetitions3(String s1, int n1, String s2, int n2) {
        int s1len = s1.length(), s2len = s2.length();
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
        int index = 0, first = -1, mainCount = 0, secCount = 0, i1 = c1.length, i2 = 0;
        int nextTime = 1;
        boolean round = true;
        while (index != first) { //通过几轮remain index回到起始坐标X -> 让remain index循环出现即可
            System.out.println(index);
            if (nextTime-- == 0)
                first = index;

            while (i1 <= c1.length) {
                if (i1 == c1.length) {
                    if (!round) { //已经走了上一轮剩下的加完整本轮
                        if (secCount == 0) //一轮匹配不了一个子串
                            mainCount++;
                        else
                            break;
                    }
                    i1 = 0; //继续自己的本轮
                    round = false; //结束上一轮
                }
                if (c1[i1++] == c2[i2])
                    i2++;
                if (i2 == c2.length) {
                    secCount++; //子串匹配完一轮
                    index = i1;
                    i2 = 0;
                }
            }
            mainCount++; //主串匹配完一轮
            i1 = index; //从上一轮的地方开始
            i2 = 0;
            round = true; //恢复现场
        }

        return (int) (n1 * 1.0 * secCount / n2 / mainCount);
    }


    /**优化暴力：根据Pigeonhole principle, 把n个物品放到m个框里，n>m, 那么至少会有一个框装有多于一个物品。
     * 所以我们可以确定，在对s1扫描最多s2.length次后，就能找到一个相同的index*/
    public int getMaxRepetitions4(String s1, int n1, String s2, int n2) {
        if (n1 == 0)
            return 0;

        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
        int indexr[] = new int[c2.length + 1]; // index at start of each s1 block, 一轮主串s1开始匹配时s2的开始位置
        int countr[] = new int[c2.length + 1]; // count of repititions till the present s1 block, 到当前位置(包含重复的)为止前边走过的子串s2数量
        int index = 0, count = 0;
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < c1.length; j++) {
                if (c1[j] == c2[index])
                    ++index;
                if (index == c2.length) {
                    index = 0;
                    ++count;
                }
            }
            countr[i] = count;
            indexr[i] = index;
            for (int k = 0; k < i; k++) {
                if (indexr[k] == index) {
                    int prev_count = countr[k];
                    int pattern_count = (countr[i] - countr[k]) * (n1 - 1 - k) / (i - k);
                    int remain_count = countr[k + (n1 - 1 - k) % (i - k)] - countr[k];
                    return (prev_count + pattern_count + remain_count) / n2;
                }
            }
        }
        return countr[n1 - 1] / n2;
    }

    /**包含每轮重复思路的正解*/
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (!ableToObtain(s1, s2)) return 0; // check if [s1. ∞] obtains s2
        int cnt=0, k=-1;
        String s=s1;
        StringBuilder remainBuilder; // record `remain string`
        ArrayList<String> stringList=new ArrayList<>(); // record all the `remain string`
        ArrayList<Integer> countList=new ArrayList<>(); // record matching count from start to the current remain string
        stringList.add(""); // record empty string
        countList.add(0);
        for (int i=0;i<=n1;i++) {
            remainBuilder=new StringBuilder();
            cnt+=getRemain(s, s2, remainBuilder); // get the next remain string, returns the count of matching
            String remain=remainBuilder.toString();
            if ((k=stringList.indexOf(remain))!=-1) break; // if there is a loop, break
            stringList.add(remain); // record the remain string into arraylist
            countList.add(cnt);
            s=remain+s1; // append s1 to make a new string
        }
        // here, k is the beginning of the loop
        if (k==-1) return cnt/n2; // if there is no loop
        int countOfLoop=cnt-countList.get(k), loopLength=stringList.size()-k; // get matching count in the loop, and loop length
        cnt=countList.get(k);
        n1-=k;
        cnt+=countOfLoop*(n1/loopLength);
        n1%=loopLength;
        cnt+=countList.get(k+n1)-countList.get(k);
        return cnt/n2;
    }

    // check if [s1. ∞] obtains s2
    private boolean ableToObtain(String s1, String s2) {
        boolean[] cnt=new boolean[26];
        for (char c: s1.toCharArray()) cnt[c-'a']=true;
        for (char c: s2.toCharArray()) {
            if (!cnt[c-'a']) return false;
        }
        return true;
    }

    // get remain string after s1 obtains s2, return the matching count
    private int getRemain(String s1, String s2, StringBuilder remain) {
        int cnt=0, lastMatch=-1, p2=0;
        for (int p1=0;p1<s1.length();p1++) {
            if (s1.charAt(p1)==s2.charAt(p2)) {
                if (++p2==s2.length()) {
                    p2=0;
                    cnt++;
                    lastMatch=p1;
                }
            }
        }
        remain.append(s1.substring(lastMatch+1));
        return cnt;
    }
}
