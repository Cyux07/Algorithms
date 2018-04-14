/**
 * Created by Administrator on 2018/2/7.
 * 45. 找出最小的跳跃次数
 */
public class JumpGameII {
    public static void main(String[] s) {
        System.out.println(new JumpGameII().jump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(new JumpGameII().jump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(new JumpGameII().jump(new int[]{2, 0, 0}));
        System.out.println(new JumpGameII().jump(new int[]{2, 1}));
    }
    //[2,3,1,1,4]
    //[3,2,1,0,4]
    //[2,1] 1

    /**Greedy Solution?
     * 43.46%*/
    public int jump(int[] nums) {
        if (nums.length <= 1)
            return 0;
        if (nums[0] == 0)//can not reach
            return -1;

        int jump = 0, step = nums[0], maxReach = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxReach = Math.max(maxReach, nums[i] + i);//绝对位置，下标
            //step--;
            if (step == i) { //上次能跳到的最远地方
                jump++;
                if(i == nums.length - 1) //最后一个阶梯无用
                    return jump;
                if (i >= maxReach) //exactly only ==
                    return -1;
                step = maxReach;
            }
        }
        return step >= nums.length ? jump+1 : jump;//jump out bound!
    }
}
