import java.math.BigInteger;

/**
 * Created by Administrator on 2018/1/20.
 * 55.
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * 只需找出一个方案
 */
public class JumpGame {
    public static void main(String[] s) {
        BigInteger a= BigInteger.ONE;
        int count = 0;
        for (int i = 5;i <= 2016; i++) {
            a = a.multiply(BigInteger.valueOf(i));
            System.out.println("for..."+a);
        }
        while (a.mod(BigInteger.valueOf(10)).equals(BigInteger.ZERO)) {
            System.out.println("while..."+a);
            count++;
            a = a.divide(BigInteger.valueOf(10));
        }
        System.out.println(count);//502
        //System.out.println(new JumpGame().canJump(new int[]{2, 3, 1, 1, 4}));
        //System.out.println(new JumpGame().canJump(new int[]{3, 2, 1, 0, 4}));
        //System.out.println(new JumpGame().canJump(new int[]{2, 0, 0}));
    }
    //[2,3,1,1,4]
    //[3,2,1,0,4]

    /**58.76%*/
    public boolean canJump(int[] nums) {
        if (nums.length == 1)
            return true;
        if (nums[0] == 0)
            return false;

        int len = nums.length, dis=1;
        for (int i = len - 2; i > 0; i--) {//last(len -1) element is useless
            if (nums[i] >= dis) // only need to guarantee the future one can go to nums[i]
                dis=1;
            else //need future one can go to nums[i+1]
                dis++;
        }

        return nums[0] >= dis;
    }
}
