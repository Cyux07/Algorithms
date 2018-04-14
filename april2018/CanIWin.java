package april2018;

import java.util.HashMap;
import java.util.Map;

/**在数100游戏中，两个玩家轮流加，直到达到目标数(100), 每次只能从1~10中选数来加。
 * 那么先手玩家必然可以达到或者超过100来得到胜利
 * 那么，如果我们要求每次使用的数字不能重复呢？
 * 例如，两个玩家轮流从公共池里取1~15的数字，不重复，直到总计达到(超过100
 * 给出最大可选数(1~k), 和要求的总和，判断先手是不是一定能赢
 * 可选数不超过20，总和不超过300
 * Example
 *
 * Input:
 * maxChoosableInteger = 10
 * desiredTotal = 11
 *
 * Output:
 * false
 *
 * Explanation:
 * No matter which integer the first player choose, the first player will lose.
 * The first player can choose an integer from 1 up to 10.
 * If the first player choose 1, the second player can only choose integers from 2 up to 10.
 * The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
 * Same with other integers chosen by the first player, the second player will always win.*/

//For this problem, by applying the memo, we at most compute for every subproblem once,
// and there are O(2^n) subproblems, so the complexity is O(2^n) after memorization.
// (Without memo, time complexity should be like O(n!))

public class CanIWin {
    /**64.10%*/
    //备忘录<剩下可选，能否达到剩余目标> 剩下可选的所有数字通过位存在一个int里
    Map<Integer, Boolean> mem;
    boolean[] used;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if ((1 + maxChoosableInteger) * maxChoosableInteger / 2 //sum = n*(n+1)/2
                < desiredTotal)
            return false;
        if (desiredTotal <= 0) return true;

        mem = new HashMap<>();
        used = new boolean[maxChoosableInteger + 1]; //0x, 1 ~ maxchoose
        return helper(desiredTotal);
    }

    private boolean helper(int desiredTotal) {
        if (desiredTotal <= 0) return false; //recursive finish, 上一手已胜利

        int key = format(used); //可用数字集（bit
        if (!mem.containsKey(key)) {
            //try each unchoosen
            for (int i = 1; i < used.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    if (!helper(desiredTotal - i)) { //对手之后会输
                        mem.put(key, true); //这一步能成
                        used[i] = false;
                        return true; //find a win way
                    }
                    used[i] = false;//回复现场
                }
            }//for loop end
            mem.put(key, false);
        }
        return mem.get(key);
    }

    //transfer bool[] -> int
    private int format(boolean[] used) {
        int bits = 0;

        for (boolean u : used) {
            bits <<= 1;
            if (u) bits |= 1;
        }
        return bits;
    }
}
