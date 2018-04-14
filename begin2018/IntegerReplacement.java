/**
 * Created by Administrator on 2018/1/16.
 */
public class IntegerReplacement {

    public static void main(String[] s) {
        System.out.print(new IntegerReplacement().integerReplacement(
                Integer.MAX_VALUE));//need 32
        System.out.print(new IntegerReplacement().integerReplacement(
                8));
        System.out.print(new IntegerReplacement().integerReplacement(
                7));
    }

    /**only 4.86%*/
    int min;
    public int integerReplacement(int n) {
        min = n;

        if (n == Integer.MAX_VALUE) {//int out bound
            //ir(Integer.MAX_VALUE / 2 + 1, 2);//(+1),then/2
            //ir(Integer.MAX_VALUE / 2, 2);//-1, then/2
            return 32;
        }else {
            ir(n, 0);
        }

        return min;
    }

    void ir1(int n, int step) {
        if (n == 1) {
            if (step < min) min = step;
            return ;
        }

        if (n % 2 == 0) {
            ir(n / 2, step + 1);
        } else {
            ir(n+1, step + 1);
            ir(n-1, step + 1);
        }
    }

    /**5.84%*/
    void ir2(int n, int step) {
        if (n == 1) {
            if (step < min) min = step;
            return ;
        }

        while (n % 2 == 0) {
            n /= 2;
            step++;
        }
        if (n == 1) {
            if (step < min) min = step;
            return ;
        }
        ir(n+1, step + 1);
        ir(n-1, step + 1);
    }

    void ir(int n, int step) {
        if (n == 1) {
            if (step < min) min = step;
            return ;
        }

        while ((n & 1) == 1) {
            n >>>= 2;
            step++;
        }
        if (n == 1) {
            if (step < min) min = step;
            return ;
        }
        ir(n+1, step + 1);
        ir(n-1, step + 1);
    }

    /**prime Solution*/
    public int integerReplacement1(int n) {
        int count=0;
        if(n<=1)
            return count;

        while(n!=1){
            if(n%2==0){
                n>>>=1;
            }else{
                if(n==3||Integer.bitCount(n+1)>Integer.bitCount(n-1)){
                    n-=1;
                }else{
                    n+=1;
                }
            }
            count++;
        }

        return count;

    }

    /**most Solution The greedy heuristic of the above solution*/
    public int integerReplacement2(int n) {
        if(n==Integer.MAX_VALUE) return 32;
        int op=0;
        while(n>1){
            if(n%2==0) {
                n /= 2;
            } else {
                if((n+1)%4 == 0 && n!=3){
                    n++;
                } else {
                    n--;
                }
            }
            op++;
        }
        return op;
    }

    /**with explain
     * So in all the cases f(k-1) <= f(k)
     * else return n = .......01?*/
    public int integerReplacement3(int n) {
        if(n==Integer.MAX_VALUE) return 32;
        if (n <= 2) return n-1;
        if (n == 3) return 2;
        if (n % 2 == 0) return integerReplacement(n/2)+1;
        else return (n&2) == 0 ? integerReplacement(n-1)+1 : integerReplacement(n+1)+1;
    }
}
