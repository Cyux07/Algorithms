import java.math.BigInteger;
import java.util.*;

/**
 * Created by Administrator on 2018/1/14.
 * Question 372.Super Pow
 * Your task is to calculate a^b mod 1337 where a is a positive integer
 * and b is an extremely large positive integer given in the form of an array.
 * 数学方法结题思路：remainder repeat pattern 余数重复模式？
 * 又见维基-模数求幂：https://en.wikipedia.org/wiki/Modular_exponentiation
 * c mod m = (a ⋅ b) mod m
 * c mod m = [(a mod m) ⋅ (b mod m)] mod m
 */
//1337 = LEET
public class SuperPow {
    int DIV = 1337;
    public static void main(String[] s) {
        System.out.print(new SuperPow().superPow(2, new int[]{1,0}));
        //2[1,0]
        //2147483647[2,0,0] answer:1198
        /*s
                */
    }

    /**超时*/
    public int superPow1(int a, int[] b) {
        if (a==0 || a==DIV || b==null || b.length == 0) return 0;
        if (a==1) return 1;

        long al = a, c = 1, e = 0;
        for (int i = b.length - 1; i >=0; i--, e=0) {
            while (e++ < b[i] * Math.pow(10, b.length -1 - i)) {
                if (al * c % DIV == al * c)
                    c *= al;
                else
                    c = al * c % DIV;
            }
        }

        return (int) c;
    }

    //含递归
    //传参问题，java消耗太大，python或c++可能好一点
    public int superPow(int a, int[] b) {
        if (a==0 || a==DIV || b==null || b.length == 0) return 0;
        if (a==1) return 1;

        Stack<Integer> bb = new Stack<>();
        for (int bi : b)
            bb.push(bi);
        if (bb.isEmpty()) return 1;
        int right_dig = bb.pop();
        //return pow(superPow(a, bb), 10) * pow(a, right_dig) % DIV;
        return 0;
    }

    int pow(int a, int k) {
        a %= DIV;
        long al = a, result = 1;
        int i = 0;
        while (i++<k)
            result = (result * al) % DIV;

        return (int) result;
    }
}
