package april2018;

/**两个水杯容量分别是x和y，问能否测出容量z的水，即如果z可测，那么最终结果时必须有一个或两个杯子里的水是z升
 * 另：输出得到容量z的过程（输出每一次倒水）
 * 青蛙跳地球 也是同类问题*/

/**z = m * x + n * y

 其中m，n为舀水和倒水的次数，正数表示往里舀水，负数表示往外倒水，
 那么题目中的例子可以写成: 4 = (-2) * 3 + 2 * 5，即3升的水罐往外倒了两次水，5升水罐往里舀了两次水。
 那么问题就变成了对于任意给定的x,y,z，存不存在m和n使得上面的等式成立。
 根据裴蜀定理(贝祖定理)，ax + by = d的解为 d = gcd(x, y)，那么我们只要z % d == 0，上面的等式就有解，
 所以问题就迎刃而解了，我们只要看z是不是x和y的最大公约数的倍数就行了，
 别忘了还有个限制条件x + y >= z，因为x和y不可能称出比它们之和还多的水，参见代码如下；*/

public class WaterJugProblem {
    public boolean canMeasureWater(int x, int y, int z) {
        return z == 0 || (x + y >= z && z % gcd (x, y) == 0);
    }

    //计算m和n的值：递归
    //设 a > b，a = bq + r
    //ax + by = 1
    //(bq + r)x + by = 1
    //rx + b(qx + y) = 1
    //dx + by' = 1
    //递归2：
    //a * x + b * y = b * x0 + (a % b) * y0
    //              = b * x0 + (a – a / b * b) * y0 //a整除b
    //              = a * y0 + ( x0 – a / b * y0 ) * b
    //所以 x = y0, y = x0 – a / b * y0;

    //欧几里得Euclid算法（辗转相处法）
    private int gcd(int x, int y) {
        return y == 0? x : gcd(y, x%y);
    }

    int x, y; //a*x + b*y = z = n*gcd(a, b)
    int exEuclid(int a, int b) {
        int m, n;
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }else {
            n = exEuclid(b, a%b);
            m = x;
            x = y;
            y = m - a / b * y;
            return n;
        }
    }
}
