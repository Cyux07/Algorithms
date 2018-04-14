import java.util.Arrays;

/**
 * Created by Administrator on 2018/1/13.
 */
public class Problem50pow {
    public static void main(String[] s) {
        //outBound(2147483647);
        //outBound(-2147483648); 2147483648 is out of bound
        System.out.print(new Problem50pow().myPow(-2, 2));
        //1.0000, -2147483648
        //2.0000, -2147483648
        //34.00515, -3
        //2, 10
    }

    public static void outBound(int o) {
        System.out.print(o == 0);
    }


    //思路类比 不用乘除法的除法题 4.98%
    public double myPow(double x, int n) {
        if (n == 0 || x == 1) return 1;
        if (n == -2147483648) return x==-1? 1 : 0;
        if (n < 0) {
            n = -n; x = 1/x;
        }
        double result = 1, temp = x;
        int process = 0, pow = 1;

        while (process < n) {
            pow = 1;temp = x;
            while (pow <= n - process) {
                result *= temp;
                process += pow;
                temp *= x;//update step
                pow++;//update step
            }
            if (result <= 0.000001 && result >= -0.000001)
                return 0;
        }

        return result;
    }

    //最速解法 TODO understand
    public double myPow1(double x, int n) {
        if (x == 0) {
            return 0D;
        }

        double result = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                if (n > 0) {
                    result *= x;
                }
                else {
                    result /= x;
                }
            }
            x *= x;
            n /= 2;
        }

        return result;
    }

    //递归解法 约70%
    public double myPow2(double x, int n) {
        if (n<0)
            return 1 / Power(x, -n);
        return Power(x, n);
    }

    public double Power(double x, int n)
    {
        if (n == 0)
            return 1.0;
        double half = Power(x, n / 2);
        if (n % 2 == 0)
            return half * half;
        else
            return half * half*x;
    }
}
