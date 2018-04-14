import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/28.
 * 233. 给一个数字n，计算从1到n的所有数字里，1出现的次数
 *
 * For example:
 Given n = 13,
 Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
public class NumberofDigitOne {
    public static void main(String[] s) {
        NumberofDigitOne ndo = new NumberofDigitOne();
        System.out.println(ndo.countDigitOne(99));//20
        System.out.println(ndo.countDigitOne(999));//300
        System.out.println(ndo.countDigitOne(199) - ndo.countDigitOne(99));//120
        System.out.println(ndo.countDigitOne(1999) - ndo.countDigitOne(999));//1300

    }

    /**824883294: 超时
     * 暴力+备忘录*/
    Map<Integer, Integer> mem = new HashMap<>();//<num, count> memory
    public int countDigitOneFailed(int n) {
        if (n < 1)
            return 0;
        if (n < 10)
            return 1;
        if (n < 11)
            return 2;
        if (n <= 11)
            return 4;
        if (n < 20)
            return n - 7;//3 + n - 11 + 1

        int count = 0, ci, num;
        while (n > 0) {
            count += mem.getOrDefault(n, countNum(n));
            n--;
        }

        return count;
    }
    //count 1 on a single number
    int countNum(int num) {
        //compute a number exp:10111010,5
        int count = 0, temp = num;
        try {
            while (temp > 0) {
                if (temp % 10 == 1)
                    count++;
                temp /= 10;
            }

            return count;
        }finally {
            //add memory exp:<1011101,5>, <101110, 4>, <10111, 4>, <1011, 3>...
            while (num > 0 && count > 0) {
                if (num % 10 == 1) {
                    num /= 10;
                    mem.put(num, --count);
                }else {
                    num /= 10;
                }
            }
        }
    }

    /**11.76% 但是是最高的一块里的 Java/Python one pass solution easy to understand
     * 4561000-4561999 (1000)
     4551000 to 4551999 (1000)
     4541000 to 4541999 (1000)
     4531000 to 4531999 (1000)
     …
     1000 to 1999 (1000)此时只考虑第四位
     * 每位分开计算*/
    public int countDigitOne(int n) {
        if (n < 1)
            return 0;

        int count = 0, x = 1, digit, high = n; //x是10倍率，即当前考察哪一位
        do {//exp. abc1234,x=1000
            digit = high % 10;//当前位 exp.1
            high /= 10;//当前以上的高位，步长x=10 exp.abc
            count += high * x; //整 exp.abc0000
            if (digit == 1) count += n % x + 1;//低位 exp. 1: 1234 + 1
            if (digit > 1) count += x;//?? abc1
            x *= 10;//考虑的低位位数
        }while (high > 0);

        return count;
    }


    /**AC short Java solution
     * https://leetcode.com/problems/number-of-digit-one/discuss/64390/AC-short-Java-solution*/
    public int countDigitOne2(int n) {
        int count = 0;

        for (long k = 1; k <= n; k *= 10) {
            long r = n / k, m = n % k;
            // sum up the count of ones on every place k
            count += (r + 8) / 10 * k + (r % 10 == 1 ? m + 1 : 0);
        }

        return count;
    }
}
