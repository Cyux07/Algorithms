package april2018;

/**
 * /*354. april2018.Russian Doll Envelopes My Submissions QuestionEditorial Solution
 Total Accepted : 2917 Total Submissions : 10577 Difficulty : Hard
 Total Accepted:29K    Total Submissions:89.1K (Time past...
 You have a number of envelopes with widths and heights given as
 a pair of integers(w, h).One envelope can fit into another if and
 only if both the width and height of one envelope is greater than the width and height of the other envelope.

 What is the maximum number of envelopes can you april2018.Russian doll ? (put one inside other)
 俄罗斯套娃信封，仅当信封a长宽都小于信封b时，a可以放到b中，最多可以组成多少层信封？

 Example :
 Given envelopes = [[5, 4], [6, 4], [6, 7], [2, 3]], the maximum number of
 envelopes you can april2018.Russian doll is 3 ([2, 3] = >[5, 4] = >[6, 7]).
 */
import java.util.Arrays;

public class Russian {
    //classic DP
    /**7.52% classic DP O(n2)*/
    public int maxEnvelopes1(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);
        int max = 0;
        int dp [] = new int [envelopes.length];
        for(int i = 0; i < envelopes.length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(envelopes[j][0] < envelopes[i][0] && envelopes[j][1] < envelopes[i][1])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    //O(NlogN) 贪婪
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2) //input check!
            return 0;

        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ?
                b[1] - a[1] : a[0] - b[0]); //按宽度升序，如果宽度相同则按长度降序
        int dp [] = new int [envelopes.length];
        int max = 0;
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, max, envelope[1]); //基于'信封长'查找最长增长子序列(宽度[0]已经排序为递增，只需要考虑长度[1]
            if(index < 0)  //Arrays.binarySearch找不到返回key应该被插入的位置(-insert-1)
                index = -(index + 1);
            dp[index] = envelope[1]; //insert，长度逆序以小替换大
            if(index == max) //append，多一层套娃
                max++;
        }
        return max;
    }
}
