import java.util.Arrays;

/**
 * Created by Administrator on 2018/2/4.
 * A magical string S consists of only '1' and '2' and obeys the following rules:
 * The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.
 * The first few elements of string S is the following: S = "1221121221221121122……"
 * If we group the consecutive '1's and '2's in S, it will be:

 1 22 11 2 1 22 1 22 11 2 11 22 ......

 and the occurrences of '1's or '2's in each group are:

 1 2 2 1 1 2 1 2 2 1 2 2 ......

 You can see that the occurrence sequence above is the S itself.
 Given an integer N as input, return the number of '1's in the first N number in the magical string S.

 Note: N will not exceed 100,000.
 */
public class MagicalString {
    
    public static void main(String[] s) {
        MagicalString ms = new MagicalString();
        System.out.println(ms.cur);

        ms = new MagicalString();
        System.out.println();
        System.out.print(ms.magicalString(6));
    }

    /**3.14%*/
    int cur = 2;
    public int magicalString1(int n) {
        if (n == 0)
            return 0;
        if (n <= 3)
            return 1;

        int[] ms = new int[n];
        int index = 0, count = 0;//index fast than foreach
        ms[0] = 1;ms[1] = 2;ms[2] = 2;
        for (int m : ms) {
            if (m == 1)
                count++;

            if (index >= n)
                continue;

            cur = cur == 1 ? 2 : 1;
            if (m == 1) {
                if (ms[index] == 0)
                    ms[index++] = cur;
                else
                    index++;
            }else {
                if (ms[index] == 0) {
                    ms[index++] = cur;
                    if (index < n)
                        ms[index++] = cur;
                }else {
                    index += 2;
                }
            }

        }

        System.out.println(Arrays.toString(ms));
        return count;
    }

    //int cur = 2;
    int[] ms = new int[10000];
    public int magicalString(int n) {
        if (n == 0)
            return 0;
        if (n <= 3)
            return 1;

        int index = 0, count = 0, m = 0;//index fast than slow
        ms[0] = 1;ms[1] = 2;ms[2] = 2;
        for (int slow = 0; slow < n; slow++) {
            if (ms[slow] == 1)
                count++;
            m = ms[slow];
            ms[slow] = 0;

            if (index >= n)
                continue;

            cur = cur == 1 ? 2 : 1;
            if (m == 1) {
                if (ms[index] == 0)
                    ms[index++] = cur;
                else
                    index++;
            }else {
                if (ms[index] == 0) {
                    ms[index++] = cur;
                    if (index < n)
                        ms[index++] = cur;
                }else {
                    index += 2;
                }
            }

        }

        System.out.println(Arrays.toString(ms));
        return count;
    }

    /**prime Solution*/
    public int magicalStringPrime(int n) {
        if(n == 0)
            return 0;
        if(n <= 3)
            return 1;

        int[] a = new int[n + 1];
        a[1] = 1;
        a[2] = 2;
        a[3] = 2;

        int result = 1;
        int num = 1;
        int fastIndex = 4;
        int slowIndex = 3;

        while(fastIndex <= n) {
            for(int i = 0; i < a[slowIndex] && fastIndex <= n; i++) {
                a[fastIndex] = num;
                if(num == 1)
                    result++;
                fastIndex++;
            }
            num = num == 2 ? 1 : 2;
            slowIndex++;
        }

        return result;
    }
}
