package march2018;

/**164. 一个未排序的数组，找到它处于排序状态下的时候相邻数字的最大差值
 * 但要求 Try to solve it in linear time/space.
 * 解法也没有什么神妙之处，就是强行说基数排序只有O(n)的时间复杂度,最坏情况Integer.MAX_VALUE（2147483647）为O(10n)10位数循环十次，然后用基数排序一波，遍历找最大差值*/

public class MaximumGap {
    public static void main(String[] s) {
        int[] ao;
        System.out.println(ao = new int[5]);//[I@4554617c
        System.out.println(ao);//[I@4554617c

        //112
        System.out.println(new MaximumGap().maximumGap(new int[]{533, 79, 185, 360, 838, 302, 39, 699, 461, 485, 833,
                625, 443, 514, 20, 496, 190, 440, 651, 838, 96, 641, 841, 492, 784, 187, 922, 55, 412, 935}));
    }

    /**49.25%*/
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2)
            return 0;
        final int RATIO = 10; // 10进制
        int[] temp = new int[nums.length];
        int[] radix = new int[RATIO];
        int[] swap = null;
        int pos = 1, maxPosed = nums[0];
        for (int num : nums)
            maxPosed = Math.max(maxPosed, num);
        while (maxPosed / pos > 0){
            for (int i = 1; i < RATIO; i++) //清空上一次残留
                radix[i] = 0;
            for (int num : nums) radix[(num / pos) % 10]++; //放到属于自己的基数里
            for (int i = 1; i < RATIO; i++) //
                radix[i] += radix[i-1];
            for (int i = nums.length -1; i >= 0; i--)
                temp[--radix[(nums[i] / pos) % 10]] = nums[i];
            swap = temp;//temp存放这次基于每一位排序后的暂时有一点点序的数组
            temp = nums;//nums是下次排序时相当于空的要用来存放 基于下一位排序 更多一点点有序的数组
            nums = swap;//交换后nums是本次已排好序的，temp又用来下一次当空数组放 更有序的 结果
            pos *= RATIO;
        }


        int max = 0;
        for (int i = 1; i < nums.length; i++)
            max = Math.max(max, nums[i] - nums[i-1]);//sorted
        return max;
    }
}
